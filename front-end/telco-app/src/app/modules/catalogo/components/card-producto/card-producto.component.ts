import { Component, Input, OnInit } from '@angular/core';
import { ICategory } from 'app/data/interfaces/icategory-config.metadata';

@Component({
  selector: 'app-card-producto',
  templateUrl: './card-producto.component.html',
  styleUrls: ['./card-producto.component.css'],
})
export class CardProductoComponent implements OnInit {
  @Input() category!: ICategory;
  item!:ICategory;
  constructor() {}

  ngOnInit(): void {
    this.item = this.category;
  }
}
