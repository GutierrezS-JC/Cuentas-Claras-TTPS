import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterOutlet } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { UserService } from './services/user/user.service';
import { GroupsService } from './services/groups/groups.service';
import { SpendingService } from './services/spending.service';

import { HomeComponent } from './components/home/home.component';
import { NavbarComponent } from './components/navbar/navbar.component'
import { IndexComponent } from './components/index/index.component';
import { FooterComponent } from './components/footer/footer.component';
import { RegisterService } from './services/register.service';
import { AuthService } from './services/auth.service';
import { AuthenticationService } from './services/authentication/authentication.service';
import { User } from './models/user/user.model';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterOutlet, HomeComponent, NavbarComponent, IndexComponent, FooterComponent, HttpClientModule],
  providers: [SpendingService, AuthenticationService, RegisterService, UserService, GroupsService],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'Cuentas Claras';
  currentUser: User | undefined;

  constructor(
    private router: Router,
    private authenticationService: AuthenticationService
  ) {
    this.authenticationService.currentUser.subscribe(x => this.currentUser = x);
  }
  // parsearVariable = null;

  isLogged() {
    // if (localStorage.getItem('user') != null) {
    //   const parsearVariable = localStorage.getItem('user') || "";
    //   console.log(parsearVariable)
    //   return JSON.parse(parsearVariable);
    // }
    // return false;
    return localStorage.getItem('currentUser') != null;
  }

  logout() {
    this.authenticationService.logout();
    this.router.navigate(['/login']);
  }
}
