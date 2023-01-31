import { IRecargas } from '@Core/models/irecargas.metadata';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-radio-group-precio',
  templateUrl: './radio-group-precio.component.html',
  styleUrls: ['./radio-group-precio.component.css']
})
export class RadioGroupPrecioComponent implements OnInit{
  @Input() recargas:IRecargas[] = [];
  @Output() recargaSelectedEvent = new EventEmitter<number>();
  @Output() idProductEvent = new EventEmitter<number>();

  constructor(){}

  ngOnInit(): void {
  }
  recargaSelected(price:number,idProduct:number){
    this.recargaSelectedEvent.emit(price);
    this.idProductEvent.emit(idProduct);
  }
}
