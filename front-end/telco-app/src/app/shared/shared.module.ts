import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import * as fromComponents from './components';
import { RouterModule } from '@angular/router';


@NgModule({
  declarations: [...fromComponents.componentes],
  imports: [
    CommonModule,
    FormsModule,
    RouterModule,
    ReactiveFormsModule
  ],
  exports:[
    CommonModule,
    FormsModule,
    ...fromComponents.componentes,
  ]
})
export class SharedModule { }
