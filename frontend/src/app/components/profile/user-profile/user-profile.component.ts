import { Component, OnInit } from '@angular/core';
import { NavbarComponent } from '../../navbar/navbar.component';
import { UserService } from '../../../services/user/user.service';
import { decodeJwt } from '../../../utils/jwt-decode';
import { AuthenticationService } from '../../../services/authentication/authentication.service';
import { User } from '../../../models/user/user.model';
import { SharedDataService } from '../../../services/sharedData/shared-data.service';
import { ActivatedRoute } from '@angular/router';
import { Groups } from '../../../models/groups/groups.model';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-user-profile',
  standalone: true,
  imports: [CommonModule, NavbarComponent],
  templateUrl: './user-profile.component.html',
  styleUrl: './user-profile.component.css'
})
export class UserProfileComponent implements OnInit {
  constructor(
    private route: ActivatedRoute,
    private userService: UserService,
    private authService: AuthenticationService,
    private sharedDataService: SharedDataService,
  ) { }

  currentUser!: User | null;
  profileUser!: User | null;

  contacts: any[] = [];
  groups: Groups = { listGroups: [], listOwnedGroups: [] };

  isMyOwnProfile = false;

  ngOnInit(): void {
    const token = this.authService.currentUserValue.token as string;
    const username = (decodeJwt(token).sub)
    const routeUserId = this.route.snapshot.paramMap.get('id')

    this.userService.getUserDetails(username).subscribe({
      next: userDetails => {
        this.currentUser = userDetails;

        // Obtener detalles del usuario de la ruta
        this.userService.getUserDetailsById(+routeUserId!).subscribe({
          next: routeUserDetails => {
            this.profileUser = routeUserDetails;

            if (+routeUserId! === this.currentUser!.id) {
              // El usuario está viendo su propio perfil
              this.isMyOwnProfile = true;
            }

            // Hacemos la suscripcion al observable de contactos
            this.sharedDataService.contacts$.subscribe((contacts: any[]) => {
              this.contacts = contacts;
            });
    
            this.sharedDataService.groups$.subscribe(groups => {
              this.groups = groups;
            });
    
            // Actualizamos la lista de contactos
            this.sharedDataService.updateContactsList(this.profileUser);
    
            // Actualizamos la lista de grupos
            this.sharedDataService.updateGroupList(this.profileUser);

          },
          error: error => {
            console.error('Error al obtener detalles del usuario de la ruta:', error);
          }
        });
      },
      error: error => {
        console.error('Error al obtener detalles del usuario logueado:', error);
      }
    });
  }
}
