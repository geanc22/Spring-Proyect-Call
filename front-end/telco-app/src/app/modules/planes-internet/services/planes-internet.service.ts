import { IPlanInternet } from '@Core/models/iplan-internet.metada';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'enviroment.dev';
import { Observable, map } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class PlanesInternetService {
  private urlEndPoint: string = environment.urlBase+"/api/catalogo/planes-internet"; // TODO conectar con backend
  constructor(private _http: HttpClient) {}

  getPlanesInternet(): Observable<IPlanInternet[]> {
    return this._http.get(this.urlEndPoint).pipe(
      map((response) => {
        return response as IPlanInternet[];
      })
    );
  }

  getProducto(id: number): Observable<IPlanInternet> {
    return this._http
      .get(`${this.urlEndPoint}/${id}`)
      .pipe(map((response) => response as IPlanInternet));
  }
}
