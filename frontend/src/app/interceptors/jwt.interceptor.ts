import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor } from '@angular/common/http';
import { Observable } from 'rxjs';

import { AuthenticationService } from '../services/authentication/authentication.service';
import { User } from '../models/user/user.model';

@Injectable()
export class JwtInterceptor implements HttpInterceptor {
  constructor(private authenticationService: AuthenticationService) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    console.log('interceptando request y agregando header');
    // agrego Authorization Header con jwt token si esta disponible
    // Bueno no se porque esto es tan irregular que mejor consultamos directamente
    // por el token en el localstorage... malisimo angular
    // This comment was made by the Vue Gang
    
    // let currentUser = this.authenticationService.currentUserValue;
    const user: User = JSON.parse(localStorage.getItem('currentUser')!);
    if (user && user.token) {
      console.log(user);
      req = req.clone({
        setHeaders: {
          Authorization: `Bearer ${user.token}`
        }
      });
      console.log(req.headers.keys());
    }

    return next.handle(req);
  }
}