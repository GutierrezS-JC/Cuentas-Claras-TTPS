import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthenticationService } from '../services/authentication/authentication.service';
import { User } from '../models/user/user.model';
import { Subscription } from 'rxjs';

export const authenticationGuard: CanActivateFn = (route, state) => {
  const authenticationService = inject(AuthenticationService);
  const router = inject(Router);

  let currentUser!: User | null;
  let currentUserSubscription!: Subscription;

  console.log('Guard canActivate triggered');
  currentUserSubscription = authenticationService.currentUser.subscribe(user => {
    currentUser = user;
    console.log('Usuario actual:', currentUser);
  });

  const user = authenticationService.currentUser;
  console.log(user)
  if (user) {
    console.log(user)
    console.log('Dentro de user');
    // si esta logeado lo dejo activar la ruta
    return true;
  }

  // No esta logeado, entonces redirecciono a la pagina de login
  console.log('No esta logeado');
  router.navigate(['/login']);
  return false;
};
