export interface IPlanInternet {
  id?: number;
  title: string;
  subtitle: string;
  price: number;
  duration: number;
  infoExtras: infoExtra[];
}

interface infoExtra {
  property: string;
  value: string;
}
