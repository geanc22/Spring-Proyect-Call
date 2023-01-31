import { ICarouselItem } from '@Shared/components/carousel/icarrousel-item.metadata';
import { Component } from '@angular/core';
import { ITEM_CAROUSEL } from 'app/data/const/carousel.const';
import { CATEGORIES } from 'app/data/const/categories.const';
import { ICategoryConfig } from 'app/data/interfaces/icategory-config.metadata';

@Component({
  selector: 'app-catalogo',
  templateUrl: './catalogo.component.html',
  styleUrls: ['./catalogo.component.css'],
})
export class CatalogoComponent {
  itemCarousel: ICarouselItem[] = [];
  categories!: ICategoryConfig;
  constructor(){
    this.itemCarousel = ITEM_CAROUSEL;
    this.categories = CATEGORIES;
  }
}
