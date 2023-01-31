import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-form-validation',
  templateUrl: './form-validation.component.html',
  styleUrls: ['./form-validation.component.css'],
})
export class FormValidationComponent {
  @Input() precio?: number;
  @Input() type?: 'apps-ilimitadas' | 'recargas' | 'plan-internet';
  @Input() idProduct?: number;

  regexTelf: string = `^(\\d{3}[- ]?){2}\\d{3}$`;

  regexEmail: string =
    "^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$";

  constructor(private _route:Router) {}

  redirecTo(form: any) {
    let client = {
      idProduct: this.idProduct,
      type: this.type,
      email: form.email,
      telf: form.telf,
      price: this.precio,
    };
    localStorage.setItem('client', JSON.stringify(client));
    this._route.navigate(['/payments']);
  }
}
