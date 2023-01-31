import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { AppsIlimitadasComponent } from "./pages/apps-ilimitadas/apps-ilimitadas.component";

const routes: Routes = [{ path: '', component: AppsIlimitadasComponent }];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule],
  })
export class AppsIlimitadasRoutingModule {}