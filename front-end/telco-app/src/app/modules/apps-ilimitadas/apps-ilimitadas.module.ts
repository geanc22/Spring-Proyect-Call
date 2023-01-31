import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from '@Shared/shared.module';
import { AppsIlimitadasRoutingModule } from './apps-ilimitadas-routing.module';
import { AppsIlimitadasComponent } from './pages/apps-ilimitadas/apps-ilimitadas.component';


@NgModule({
  declarations: [
    AppsIlimitadasComponent,
  ],
  imports: [
    CommonModule,
    AppsIlimitadasRoutingModule,
    SharedModule
  ]
})
export class AppsIlimitadasModule { }
