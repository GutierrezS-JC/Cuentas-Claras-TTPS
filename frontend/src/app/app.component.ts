import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { SpendingService } from './services/spending.service';
import { HttpClientModule } from '@angular/common/http';
import { UserService } from './services/user/user.service';
import { GroupsService } from './services/groups/groups.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterOutlet, HttpClientModule],
  providers: [SpendingService, UserService, GroupsService],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'Cuentas claras';
}
