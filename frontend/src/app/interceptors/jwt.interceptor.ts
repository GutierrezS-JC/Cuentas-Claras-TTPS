import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor } from '@angular/common/http';
import { Observable } from 'rxjs';

import { AuthenticationService } from '../services/authentication/authentication.service';
import { UserToken } from '../models/user/user-token.model';

@Injectable()
export class JwtInterceptor implements HttpInterceptor {
  constructor(private authenticationService: AuthenticationService) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    // Bueno no se porque esto es tan irregular que mejor consultamos directamente
    // por el token en el localstorage... malisimo angular
    // This comment was made by the Vue Gang
    
    // agrego Authorization Header con jwt token si esta disponible
    // let currentUser = this.authenticationService.currentUserValue;
    const user: UserToken = JSON.parse(localStorage.getItem('currentUser')!);
    if (user && user.token) {
      req = req.clone({
        setHeaders: {
          Authorization: `Bearer ${user.token}`
        }
      });
    }

    return next.handle(req);
  }
}