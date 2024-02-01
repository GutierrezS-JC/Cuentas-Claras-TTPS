import { Component, OnInit } from '@angular/core';
import { NavbarComponent } from '../navbar/navbar.component';
import { FooterComponent } from '../footer/footer.component';
import { RouterModule } from '@angular/router';
import { AuthenticationService } from '../../services/authentication/authentication.service';
import { User } from '../../models/user/user.model';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [RouterModule, NavbarComponent, FooterComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit {
  currentUser!: User | null;
  private currentUserSubscription!: Subscription;

  constructor(private authService: AuthenticationService) { }

  ngOnInit(): void {
    this.currentUserSubscription = this.authService.currentUser.subscribe(user => {
      this.currentUser = user;
      console.log('Usuario actual:', this.currentUser);
    });
  }

  ngOnDestroy(): void {
    // Desuscribirse para evitar fugas de memoria
    if (this.currentUserSubscription) {
      this.currentUserSubscription.unsubscribe();
    }
  }
}
