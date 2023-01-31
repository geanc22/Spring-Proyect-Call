import { Component, Input, OnInit } from '@angular/core';
import { ICategoryConfig } from '../../../data/interfaces/icategory-config.metadata';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css'],
})
export class NavbarComponent implements OnInit {
  config!: ICategoryConfig;
  title!:string;

  @Input() configCustom?: ICategoryConfig;
  @Input() titleCustom?:string;
  
  constructor() { }

  ngOnInit(): void {
    if(this.configCustom && this.titleCustom){
      this.title = this.titleCustom;
      this.config = this.configCustom;
    }else{
      console.log(this.configCustom,this.titleCustom);
      
      this.configDefault();
    }
  }

  configDefault() {
    this.title= 'title',
    this.config = {
      links: [{ name: 'item', link: ['/'] }],
    };
  }
}
