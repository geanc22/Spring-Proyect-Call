import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PaymentsRoutingModule } from './payments-routing.module';
import { PaymentsComponent } from './pages/payments/payments.component';
import { SharedModule } from '@Shared/shared.module';
import { ReactiveFormsModule } from '@angular/forms';

@NgModule({
  imports: [
    CommonModule,
    PaymentsRoutingModule,
    ReactiveFormsModule,
    SharedModule
  ],
  declarations: [
    PaymentsComponent
  ]
})
export class PaymentsModule { }
