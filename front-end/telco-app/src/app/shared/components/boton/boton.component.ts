import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-boton-generico',
  template: `<button class="jsc-btn__link" [disabled]="disabled">{{label}}</button>`,
  styleUrls: ['./boton.component.css']
})
export class BotonComponent {
  @Input() label:string='';
  @Input() disabled:boolean | null =false;
}
