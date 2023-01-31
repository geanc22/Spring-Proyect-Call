import { Component } from '@angular/core';
import { CATEGORIES } from 'app/data/const/categories.const';
import { ICategoryConfig } from 'app/data/interfaces/icategory-config.metadata';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css'],
})
export class HomePageComponent {
  title!:string;
  config!: ICategoryConfig;
  nameCompany!:string;
  constructor() {
    this.title = "bocky call";
    this.config = CATEGORIES;
    this.nameCompany = 'Bocky Call SAC.';
  }
}
