import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CatalogoRoutingModule } from './catalogo-routing.module';
import { CatalogoComponent } from './pages/catalogo/catalogo.component';
import { SharedModule } from '@Shared/shared.module';
import { CardProductoComponent } from './components/card-producto/card-producto.component';

@NgModule({
  declarations: [
    CatalogoComponent,
    CardProductoComponent
  ],
  imports: [
    CommonModule,
    CatalogoRoutingModule,
    SharedModule
  ]
})
export class CatalogoModule { }
