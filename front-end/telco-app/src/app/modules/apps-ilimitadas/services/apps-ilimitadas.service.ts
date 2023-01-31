import { IPaqueteApps } from '@Core/models/ipaquete-apps.metadata';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'enviroment.dev';
import { Observable, catchError, map, of, throwError } from 'rxjs';
import Swal from 'sweetalert2';

@Injectable({
  providedIn: 'root',
})
export class AppsIlimitadasService {
  private urlEndPoint: string = environment.urlBase+"/api/catalogo/apps-ilimitadas"; //TODO conectar con el backend

  constructor(private _http: HttpClient) {}

  getAppsIlimitadas(): Observable<IPaqueteApps[]> {
    return this._http
      .get(this.urlEndPoint)
      .pipe(map((response) => response as IPaqueteApps[]));

  }

  getProducto(id: string): Observable<IPaqueteApps> {
    return this._http
      .get(`${this.urlEndPoint}/${id}`)
      .pipe(map((response) => response as IPaqueteApps));
  }

  subirImg(arhcivo: File, id: string): Observable<IPaqueteApps> {
   
    let formData = new FormData();
    formData.append('img', arhcivo);
    formData.append('id', id);

    return this._http.post(`${this.urlEndPoint}/upload`, formData).pipe(
      map((response: any) => response.appsIlimitadas as IPaqueteApps),
      catchError((e) => {
        Swal.fire(e.error.mensaje, e.error.error, 'error');
        return throwError(() => e);
      })
    );
  }
}
