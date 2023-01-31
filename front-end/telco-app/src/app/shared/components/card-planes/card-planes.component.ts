import { IPlanInternet } from '@Core/models/iplan-internet.metada';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-card-planes',
  templateUrl: './card-planes.component.html',
  styleUrls: ['./card-planes.component.css'],
})
export class CardPlanesComponent implements OnInit {
  @Input() planInternet!: IPlanInternet;
  @Input() boton?:boolean = false;
  @Output() modalState = new EventEmitter<boolean>();
  @Output() priceEmiter = new EventEmitter<number>();
  @Output() idProductEvent = new EventEmitter<number>();
  
  ngOnInit(): void {
  }
  openModal() {
    this.modalState.emit(true);
  }
  emitirDatos(price:number,idProduct:number){
    this.priceEmiter.emit(price);
    this.idProductEvent.emit(idProduct);
  }
  
}
