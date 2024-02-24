import { Component, OnInit } from '@angular/core';
import { NavbarComponent } from '../navbar/navbar.component';
import { ContactSearchComponent } from '../modals/contacts/contact-search/contact-search.component';
import { User } from '../../models/user/user.model';
import { UserService } from '../../services/user/user.service';
import { AuthenticationService } from '../../services/authentication/authentication.service';
import { decodeJwt } from '../../utils/jwt-decode';
import { SharedDataService } from '../../services/sharedData/shared-data.service';
import { FormsModule } from '@angular/forms';
import { ContactsInvitationsListComponent } from './contacts-invitations-list/contacts-invitations-list.component';
import { CommonModule } from '@angular/common';
import { swalAlert } from '../../utils/sweet-alert';
import { UserContactService } from '../../services/userContact/user-contact.service';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-contacts',
  standalone: true,
  imports: [RouterModule, CommonModule, NavbarComponent, ContactSearchComponent, ContactsInvitationsListComponent, FormsModule],
  templateUrl: './contacts.component.html',
  styleUrl: './contacts.component.css'
})
export class ContactsComponent implements OnInit {
  constructor(
    private userService: UserService,
    private authService: AuthenticationService,
    private sharedDataService: SharedDataService,
    private userContactService: UserContactService
  ) { }

  currentUser!: User | null;

  contacts: any[] = [];

  // Opcion de grupo en radio checks para mostrar la lista seleccionada
  selectedSolicitudOption: string = 'solicitudesEnviadas';
  
  invitaciones = { solicitudesEnviadas: [], solicitudesRecibidas: [] };

  ngOnInit() {
    const token = this.authService.currentUserValue.token as string;
    const username = (decodeJwt(token).sub)

    this.userService.getUserDetails(username).subscribe({
      next: userDetails => {
        this.currentUser = userDetails;

        // Hacemos la suscripcion al observable de contactos
        this.sharedDataService.contacts$.subscribe((contacts: any[]) => {
          this.contacts = contacts;
        });

        // Hacemos la suscripcion al observable de invitaciones
        this.sharedDataService.invitations$.subscribe((invitations: any) => {
          this.invitaciones = invitations;
        });

        // Actualizamos la lista de contactos
        this.sharedDataService.updateContactsList(this.currentUser);
        
        // Actualizamos la lista de invitaciones
        this.sharedDataService.updateInvitationsList(this.currentUser);
      },
      error: error => {
        console.error('Error al obtener detalles del usuario:', error);
      }
    });
  }

  // Selector de tipo de grupo (mis grupos y grupos en los que participa el usuario)
  setSolicitudOption(option: string) {
    this.selectedSolicitudOption = option;
  }

  handleDeleteContact(userContactId: number) {
    this.userContactService.deleteContact(this.currentUser?.id as number, userContactId).subscribe({
      next: (response: any) => {
        if (response) {
          this.sharedDataService.updateContactsList(this.currentUser!);
          swalAlert(
            'success',
            'Contacto eliminado',
            'Se ha eliminado el contacto exitosamente.'
          )
        }
      },
      error: (error: any) => {
        console.log(error.message)
        swalAlert(
          'error',
          'Error',
          'No se ha podido eliminar el contacto, por favor intente de nuevo.'
        )
      }
    })
  }
}
