import { SwitchService } from '@Shared/services/switch.service';
import { Component, OnInit } from '@angular/core';
import { IPaqueteApps } from '@Core/models/ipaquete-apps.metadata';
import { AppsIlimitadasService } from '@Modules/apps-ilimitadas/services/apps-ilimitadas.service';

@Component({
  selector: 'app-apps-ilimitadas',
  templateUrl: './apps-ilimitadas.component.html',
  styleUrls: ['./apps-ilimitadas.component.css'],
})
export class AppsIlimitadasComponent implements OnInit {
  paquetesApps!: IPaqueteApps[];
  modalSwitch: boolean = false;
  precioSelected?: number;
  idProductSelected?: number;
  error:any;

  constructor(
    private _modalSS: SwitchService,
    private _appsIlimitadasService: AppsIlimitadasService
  ) {}

  ngOnInit(): void {
    this._appsIlimitadasService.getAppsIlimitadas().subscribe(
      (appsIlimitadas: IPaqueteApps[]) => (this.paquetesApps = appsIlimitadas),
      (err) => {
        this.error = {"name":err.name,"message":err.message}
      }
    );
    this._modalSS.$modal.subscribe((valor) => (this.modalSwitch = valor));
  }
}
