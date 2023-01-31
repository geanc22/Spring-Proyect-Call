import { ICarouselItem } from '@Shared/components/carousel/icarrousel-item.metadata';

export const ITEM_CAROUSEL: ICarouselItem[] = [
  {
    id: 1,
    title: {
      first: 'planes',
      second: 'internet',
    },
    subtitle: 'No te quedes sin internet!',
    link: '/catalogo/planes-internet',
    image: './assets/images/planes-internet-carousel.jpg',
  },
  {
    id: 2,
    title: {
      first: 'apps',
      second: 'ilimitadas',
    },
    subtitle: 'Disfrutas sin parar de tus apps favoritas!',
    link: '/catalogo/apps-ilimitadas',
    image: './assets/images/apps-ilimitadas-carousel.jpg',
  },
  {
    id: 3,
    title: {
      first: 'Recargas',
      second: '',
    },
    subtitle: 'No te quedes sin comunicarte, recarga ya!',
    link: '/catalogo/recargas',
    image: './assets/images/recargas-carousel.jpg',
  },
];
