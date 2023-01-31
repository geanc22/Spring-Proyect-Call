import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PlanesInternetComponent } from './pages/planes-internet/planes-internet.component';
import { PlanesInternetRoutingModule } from './planes-internet-routing.module';
import { SharedModule } from '@Shared/shared.module';



@NgModule({
  declarations: [
    PlanesInternetComponent,
  ],
  imports: [
    CommonModule,
    PlanesInternetRoutingModule,
    SharedModule
  ]
})
export class PlanesInternetModule { }
