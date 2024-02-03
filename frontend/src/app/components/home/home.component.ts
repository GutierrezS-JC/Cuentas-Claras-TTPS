import { Component, OnInit } from '@angular/core';
import { NavbarComponent } from '../navbar/navbar.component';
import { FooterComponent } from '../footer/footer.component';
import { RouterModule } from '@angular/router';
import { AuthenticationService } from '../../services/authentication/authentication.service';
import { UserToken } from '../../models/user/user-token.model';
import { Subscription } from 'rxjs';
import { decodeJwt } from '../../utils/jwt-decode';
import { User } from '../../models/user/user.model';
import { UserService } from '../../services/user/user.service';

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

  constructor(
    private authService: AuthenticationService,
    private userService: UserService
    ) { }

  ngOnInit(): void {
    this.currentUserSubscription = this.authService.currentUser.subscribe(user => {
      const token = user?.token as string;
      const username = (decodeJwt(token)?.sub)

      this.userService.getUserDetails(username).subscribe({
        next: userDetails => {
          this.currentUser = userDetails;
        },
        error: error => {
          console.error('Error al obtener detalles del usuario:', error);
        }
      });
    });
  }

  ngOnDestroy(): void {
    if (this.currentUserSubscription) {
      this.currentUserSubscription.unsubscribe();
    }
  }
}
