import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { IPaqueteApps } from '@Core/models/ipaquete-apps.metadata';
import { environment } from 'enviroment.dev';

@Component({
  selector: 'app-card-apps-ilimitadas',
  templateUrl: './card-apps-ilimitadas.component.html',
  styleUrls: ['./card-apps-ilimitadas.component.css']
})
export class CardAppsIlimitadasComponent implements OnInit{

  @Input() pack!:IPaqueteApps;
  @Input() boton?:boolean = false;
  @Output() modalState = new EventEmitter<boolean>();
  @Output() priceEvent = new EventEmitter<number>();
  @Output() idProductEvent = new EventEmitter<number>();
  urlBase:String = environment.urlBase+"/api/catalogo/apps-ilimitadas";
  ngOnInit(): void {
  }

  openModal() {
    this.modalState.emit(true);
  }

  emitirDatos(price:number,idProduct:number){
    this.priceEvent.emit(price);
    this.idProductEvent.emit(idProduct);
  }

}
