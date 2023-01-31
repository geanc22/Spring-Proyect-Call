import { IPlanInternet } from '@Core/models/iplan-internet.metada';
import { PlanesInternetService } from '@Modules/planes-internet/services/planes-internet.service';
import { SwitchService } from '@Shared/services/switch.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-planes-internet',
  templateUrl: './planes-internet.component.html',
  styleUrls: ['./planes-internet.component.css'],
})
export class PlanesInternetComponent implements OnInit {
  precioSelected?: number;
  planesInternet!: IPlanInternet[];
  modalSwitch: boolean = false;
  idProductSelected?: number;
  error: any;

  constructor(
    private _modalSS: SwitchService,
    private _planesInternetService: PlanesInternetService
  ) {}

  ngOnInit(): void {
    this._planesInternetService.getPlanesInternet().subscribe(
      (planes) => (this.planesInternet = planes),
      (err) => {
        this.error = { name: err.name, message: err.message };
      }
    );
    this._modalSS.$modal.subscribe((valor) => (this.modalSwitch = valor));
  }
}
