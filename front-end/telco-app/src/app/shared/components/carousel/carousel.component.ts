import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import { ICarouselItem } from './icarrousel-item.metadata';
import { interval } from 'rxjs';
@Component({
  selector: 'app-carousel',
  templateUrl: './carousel.component.html',
  styleUrls: ['./carousel.component.css'],
})
export class CarouselComponent implements OnInit, OnDestroy {
  @Input() height: number = 500;
  @Input() isFullScreen: boolean = false;
  @Input() items: ICarouselItem[] = [];

  contador = interval(4500);
  public finalHeight: string | number = 0;
  public currentPosition = 0;
  subscription = this.contador.subscribe((n) => {
    this.setNext();
  });
  constructor() {}

  ngOnInit(): void {
    this.items.map((i, index) => {
      i.id = index;
      i.marginLeft = 0;
    });
    this.finalHeight = this.isFullScreen ? '100vh' : `${this.height}px`;
  }
  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  setCurrentPosition(position: number) {
    this.currentPosition = position;
    this.items.find((i) => i.id === 0)!.marginLeft = -100 * position;
  }

  setNext() {
    let finalPercentage = 0;
    let nextPosition = this.currentPosition + 1;
    if (nextPosition <= this.items.length - 1) {
      finalPercentage = -100 * nextPosition;
    } else {
      nextPosition = 0;
    }
    this.items.find((i) => i.id === 0)!.marginLeft = finalPercentage;
    this.currentPosition = nextPosition;
  }

  setBack() {
    let finalPercentage = 0;
    let backPosition = this.currentPosition - 1;
    if (backPosition >= 0) {
      finalPercentage = -100 * backPosition;
    } else {
      backPosition = this.items.length - 1;
      finalPercentage = -100 * backPosition;
    }
    this.items.find((i) => i.id === 0)!.marginLeft = finalPercentage;
    this.currentPosition = backPosition;
  }
}
