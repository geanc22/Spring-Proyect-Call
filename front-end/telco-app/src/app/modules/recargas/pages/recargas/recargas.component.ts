import { IRecargas } from '@Core/models/irecargas.metadata';
import { RecargasService } from '@Modules/recargas/services/recargas.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-recargas',
  templateUrl: './recargas.component.html',
  styleUrls: ['./recargas.component.css'],
})
export class RecargasComponent implements OnInit {
  precioSelected?: number;
  recargas!: IRecargas[];
  idProductSelected?: number;
  error: any;
  constructor(private _recargasService: RecargasService) {}

  ngOnInit(): void {
    this._recargasService.getRecargas().subscribe(
      (recargas) => {
        this.recargas = recargas;
      },
      (err) => {
        this.error = {"name":err.name,"message":err.message}
      }
    );
  }
}
