import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TokenStorageService } from './token-storage.service';

@Injectable({
  providedIn: 'root'
})
export class TokenInterceptorServiceService implements HttpInterceptor{

  constructor(private tokenStorageService:TokenStorageService) { }
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const authToken= this.tokenStorageService.getToken()
    const authRequest = !authToken ? req : req = req.clone({
      setHeaders: {
        'Authorization': `Bearer ${authToken}`,
      },
    });
    return next.handle(authRequest);
  }
}
