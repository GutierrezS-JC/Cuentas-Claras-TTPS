import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor } from '@angular/common/http';
import { Observable } from 'rxjs';

import { AuthenticationService } from '../services/authentication/authentication.service';

@Injectable()
export class JwtInterceptor implements HttpInterceptor {
  constructor(private authenticationService: AuthenticationService) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    console.log('interceptando request y agregando header');
    // agrego Authorization Header con jwt token si esta disponible
    let currentUser = this.authenticationService.currentUserValue;
    if (currentUser && currentUser.token) {
      console.log(currentUser);
      req = req.clone({
        setHeaders: {
          Authorization: `Bearer ${currentUser.token}`
        }
      });
      console.log(req.headers.keys());
    }

    return next.handle(req);
  }
}