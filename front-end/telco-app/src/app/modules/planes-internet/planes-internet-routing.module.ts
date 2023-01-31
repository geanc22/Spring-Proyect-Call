import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PlanesInternetComponent } from './pages/planes-internet/planes-internet.component';


const routes: Routes = [{ path: '', component: PlanesInternetComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class PlanesInternetRoutingModule {}
