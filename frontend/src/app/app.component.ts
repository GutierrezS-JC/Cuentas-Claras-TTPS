import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
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

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterOutlet, HomeComponent, NavbarComponent, IndexComponent, FooterComponent, HttpClientModule],
  providers: [SpendingService, AuthService, RegisterService, UserService, GroupsService],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'Cuentas Claras';

  // parsearVariable = null;

  isLogged() {
    // if (localStorage.getItem('user') != null) {
    //   const parsearVariable = localStorage.getItem('user') || "";
    //   console.log(parsearVariable)
    //   return JSON.parse(parsearVariable);
    // }
    // return false;
    console.log(localStorage.getItem('user') != null)
    return localStorage.getItem('user') != null;
  }
}
