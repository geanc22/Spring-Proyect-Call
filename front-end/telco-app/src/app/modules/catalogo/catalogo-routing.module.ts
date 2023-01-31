import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CatalogoComponent } from './pages/catalogo/catalogo.component';

const routes: Routes = [
  {
    path: '',
    component: CatalogoComponent,
  },
  {
    path: 'planes-internet',
    loadChildren: () =>
      import('@Modules/planes-internet/planes-internet.module').then(
        (m) => m.PlanesInternetModule
      ),
  },
  {
    path: 'apps-ilimitadas',
    loadChildren: () =>
      import('@Modules/apps-ilimitadas/apps-ilimitadas.module').then(
        (m) => m.AppsIlimitadasModule
      ),
  },
  {
    path: 'recargas',
    loadChildren: () =>
      import('@Modules/recargas/recargas.module').then(
        (m) => m.RecargasModule
      ),
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class CatalogoRoutingModule {}
