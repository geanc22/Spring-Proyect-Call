import { IPayment } from '@Core/models/ipayment.metadata';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'enviroment.dev';
import { Observable, catchError, map, throwError } from 'rxjs';
import Swal from 'sweetalert2';

@Injectable({
  providedIn: 'root',
})
export class PaymentsService {
  private urlEndPoint: string = environment.urlBase+"/payments";

  constructor(private _http: HttpClient) {}

  getPayments(): Observable<IPayment[]> {
    return this._http.get(this.urlEndPoint).pipe(
      map((response) => {
        return response as IPayment[];
      })
    );
  }

  getPayment(id: number): Observable<IPayment> {
    return this._http
      .get(`${this.urlEndPoint}/${id}`)
      .pipe(map((response: any) => response as IPayment));
  }

  savePayment(payment: IPayment): Observable<IPayment> {
    return this._http.post(this.urlEndPoint, payment).pipe(
      map((response: any) => {
        return response.payment as IPayment;
      }),
      catchError((err) => {
        if (err.status == 400) {
          return throwError(() => err);
        }
        console.error(err.error);
        Swal.fire(err.error, err.error.error, 'error');
        return throwError(() => err);
      })
    );
  }
}
