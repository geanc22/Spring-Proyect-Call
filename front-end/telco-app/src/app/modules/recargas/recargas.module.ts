import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RecargasComponent } from './pages/recargas/recargas.component';
import { RecargasRoutingModule } from './recargas-routing.module';
import { RadioGroupPrecioComponent } from './components/radio-group-precio/radio-group-precio.component';
import { SharedModule } from '@Shared/shared.module';
 


@NgModule({
  declarations: [
    RecargasComponent,
    RadioGroupPrecioComponent,
  ],
  imports: [
    CommonModule,
    RecargasRoutingModule,
    SharedModule
  ]
})
export class RecargasModule { }
