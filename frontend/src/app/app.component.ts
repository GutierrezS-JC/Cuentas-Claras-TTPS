import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterOutlet } from '@angular/router';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { UserService } from './services/user/user.service';
import { GroupsService } from './services/groups/groups.service';
import { SpendingService } from './services/spending.service';

import { HomeComponent } from './components/home/home.component';
import { NavbarComponent } from './components/navbar/navbar.component'
import { IndexComponent } from './components/index/index.component';
import { FooterComponent } from './components/footer/footer.component';
import { RegisterService } from './services/register.service';
import { AuthenticationService } from './services/authentication/authentication.service';
import { User } from './models/user/user.model';
import { Subscription } from 'rxjs';

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
}
