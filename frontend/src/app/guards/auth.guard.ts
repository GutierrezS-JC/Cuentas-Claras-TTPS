import { Injectable } from '@angular/core';
import { Router, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';

import { AuthenticationService } from '../services/authentication/authentication.service';

@Injectable({ providedIn: 'root' })
export class AuthGuard {
  constructor(
    private router: Router,
    private authenticationService: AuthenticationService
  ) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    const currentUser = this.authenticationService.currentUserValue;
    console.log(currentUser.token)
    if (currentUser) {
      console.log('Dentro de currentUser');
      // si esta logeado lo dejo activar la ruta
      return true;
    }

    // No esta logeado, entonces redirecciono a la pagina de login
    console.log('No esta logeado');
    this.router.navigate(['/login']);
    return false;
  }
}