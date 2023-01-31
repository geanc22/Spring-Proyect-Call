import { IPayment } from '@Core/models/ipayment.metadata';
import { AppsIlimitadasService } from '@Modules/apps-ilimitadas/services/apps-ilimitadas.service';
import { PaymentsService } from '@Modules/payments/services/payments.service';
import { PlanesInternetService } from '@Modules/planes-internet/services/planes-internet.service';
import { RecargasService } from '@Modules/recargas/services/recargas.service';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AÑOS } from 'app/data/const/años.const';
import { MESES } from 'app/data/const/meses.const';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-payments',
  templateUrl: './payments.component.html',
  styleUrls: ['./payments.component.css'],
})
export class PaymentsComponent implements OnInit, OnDestroy {
  infoPayment: any;
  product: any;
  meses: any[] = [];
  anios: number[] = [];
  formVenta!: FormGroup;

  constructor(
    private _recargasService: RecargasService,
    private _appsIlimitadasService: AppsIlimitadasService,
    private _planesInternetSerive: PlanesInternetService,
    private fb: FormBuilder,
    private _paymentsService: PaymentsService,
    private router: Router
  ) {
    this.anios = AÑOS;
    this.meses = MESES;
  }

  ngOnInit(): void {
    this.infoPayment = JSON.parse(localStorage.getItem('client')!);
    this.cargarProducto();
    this.initForm();
  }

  initForm() {
    this.formVenta = this.fb.group({
      id_producto: [this.infoPayment.idProduct, [Validators.required]],
      type_product: [this.infoPayment.type, [Validators.required]],
      amount: [this.infoPayment.price, [Validators.required]],
      client_name: [
        '',
        [
          Validators.required,
          Validators.minLength(4),
          Validators.pattern('^[a-zA-Z*\\s]+[a-zA-Z]+$'),
        ],
      ],
      client_apellido_paterno: [
        '',
        [
          Validators.required,
          Validators.minLength(4),
          Validators.pattern('^[a-zA-Z]+[a-zA-Z]+$'),
        ],
      ],

      client_apellido_materno: [
        '',
        [
          Validators.required,
          Validators.minLength(4),
          Validators.pattern('^[a-zA-Z]+[a-zA-Z]+$'),
        ],
      ],

      client_email: [this.infoPayment.email, [Validators.required]],
      client_telf: [this.infoPayment.telf, [Validators.required]],
      card_number: [
        '',
        [Validators.required, Validators.pattern('^5[1-5][0-9]{14}$')],
      ],
      card_exp_month: ['', [Validators.required]],
      card_exp_year: ['', [Validators.required]],
      card_cvv: ['', [Validators.required, Validators.pattern('^[0-9]{3,4}$')]],
      terms: [false, [Validators.requiredTrue]],
    });
  }

  cargarProducto() {
    if (this.infoPayment.type == 'apps-ilimitadas') {
      this._appsIlimitadasService
        .getProducto(this.infoPayment.idProduct)
        .subscribe((response) => (this.product = response));
    } else if (this.infoPayment.type == 'plan-internet') {
      this._planesInternetSerive
        .getProducto(this.infoPayment.idProduct)
        .subscribe((response) => (this.product = response));
    } else if (this.infoPayment.type == 'recargas') {
      this._recargasService
        .getProducto(this.infoPayment.idProduct)
        .subscribe((response) => (this.product = response));
    }
  }

  send() {
    if (this.formVenta.valid) {
      let payment: IPayment = this.obtenerDatos(this.formVenta);
      this._paymentsService.savePayment(payment).subscribe(
        (payment) => {
          Swal.fire(
            'Pago realizado',
            `${payment.client.name}! se realizo la compra con éxito. (${payment.client.telf})`,
            'success'
          ).then(result=>{
            if (result.isConfirmed) {
              this.router.navigate(['/']);
            }
          });
        },
        (err) => {
          Swal.fire(
            'Error al realziar el pago',
            `Lo sentimos estamos teniendo difucultades tecnicas`,
            'error'
          );
        }
      );
    } else {
      Swal.fire('Validacion', 'Los datos no son correctos', 'error');
    }
  }
  obtenerDatos(form: FormGroup): IPayment {
    let payment: IPayment = {
      idProducto: form.value.id_producto,
      typeProduct: form.value.type_product,
      amount: form.value.amount,
      client: {
        name: form.value.client_name,
        lastName1: form.value.client_apellido_paterno,
        lastName2: form.value.client_apellido_materno,
        email: form.value.client_email,
        telf: '' + form.value.client_telf,
        card: {
          number: '' + form.value.card_number,
          expMonth: form.value.card_exp_month,
          expYear: form.value.card_exp_year,
          cvv: '' + form.value.card_cvv,
        },
      },
      terms: form.value.terms,
    };
    return payment;
  }
  ngOnDestroy(): void {
    localStorage.removeItem('client');
  }
}
