import { Injectable } from '@angular/core';
import { Router, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';

@Injectable({ providedIn: 'root' })
export class AuthGuard {
  constructor(private router: Router) { }

  canActivate(next: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    // Bueno, hcie el intento con el metodo original pero no se pudo
    // asi que la solucion mas facil es la siguiente ok? 
    const currentUserString = localStorage.getItem('currentUser');
    let isLogged: boolean = false;

    if (currentUserString) {
      isLogged = !!JSON.parse(currentUserString);
    }

    if (isLogged) {
      return true;
    }

    // No esta logeado, entonces redirecciono a la pagina de login
    this.router.navigate(['/login']);
    return false;
  }
}