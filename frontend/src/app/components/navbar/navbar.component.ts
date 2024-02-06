import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { swalToast } from '../../utils/sweet-alert';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [RouterModule],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {
  isLoginPage: boolean = false;

  constructor(private router: Router) {
    this.isLoginPage = this.router.url.endsWith('/login');
  }

  isLogged() {
    if (localStorage.getItem('currentUser')) {
      return true
    }
    return false;
  }

  logOut() {
    localStorage.clear();
    swalToast('info', 'Sesion cerrada. Adios :(');
    this.router.navigate(['/login']);
  }
}
