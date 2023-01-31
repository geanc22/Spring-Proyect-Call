import { SwitchService } from '@Shared/services/switch.service';
import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-modal',
  templateUrl: './modal.component.html',
  styleUrls: ['./modal.component.css'],
})
export class ModalComponent{
  @Input() precio?:number;
  @Input() type?: 'apps-ilimitadas' | 'recargas' | 'plan-internet';
  @Input() idProduct?: number;
  
  constructor(private _modalSS: SwitchService) {}

  closeModal(){
    this._modalSS.$modal.emit(false);
  }
}
