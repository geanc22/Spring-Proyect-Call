import { PaymentsCanActivateGuard } from '@Core/guards/payments-can-activate.guard';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: 'catalogo',
    loadChildren: () =>
      import('@Modules/catalogo/catalogo.module').then((m) => m.CatalogoModule),
  },
  {
    path: 'payments',
    loadChildren: () =>
      import('@Modules/payments/payments.module').then((m) => m.PaymentsModule),
      canActivate:[PaymentsCanActivateGuard]
  },
  {
    path: '**',
    redirectTo: '/catalogo',
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class HomeRoutingModule {}
