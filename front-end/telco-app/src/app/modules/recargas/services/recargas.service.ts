import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, map } from 'rxjs';
import { IRecargas } from '@Core/models/irecargas.metadata';
import { environment } from 'enviroment.dev';
@Injectable({
  providedIn: 'root',
})
export class RecargasService {
  private urlEndPoint: string = environment.urlBase+"/api/catalogo/recargas"; // TODO conectar con el backend

  constructor(private _http: HttpClient) {}

  getRecargas(): Observable<IRecargas[]> {
    return this._http.get(this.urlEndPoint).pipe(
      map((response) => {
        return response as IRecargas[];
      })
    );
  }

  getProducto(id: number): Observable<IRecargas> {
    return this._http
      .get(`${this.urlEndPoint}/${id}`)
      .pipe(map((response) => response as IRecargas));
  }
}
