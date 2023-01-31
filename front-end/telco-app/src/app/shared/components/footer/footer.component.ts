import { Component, Input } from '@angular/core';
import { ICategoryConfig } from 'app/data/interfaces/icategory-config.metadata';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent {
  config!: ICategoryConfig;
  nameCompany!:string;

  @Input() configCustom?: ICategoryConfig;
  @Input() nameCompanyCustom?:string;
  
  constructor() { }

  ngOnInit(): void {
    if(this.configCustom && this.nameCompanyCustom){
      this.config = this.configCustom;
      this.nameCompany = this.nameCompanyCustom;
    }else{
      this.configDefault();
    }
  }

  configDefault() {
    this.nameCompany= 'Name Company',
    this.config = {
      links: [{ name: 'item', link: ['/'] }],
    };
  }
}
