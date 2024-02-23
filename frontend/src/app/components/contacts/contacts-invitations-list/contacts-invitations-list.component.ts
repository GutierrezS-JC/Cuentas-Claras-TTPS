import { Component, Input } from '@angular/core';
import { swalAlert } from '../../../utils/sweet-alert';
import { SharedDataService } from '../../../services/sharedData/shared-data.service';
import { User } from '../../../models/user/user.model';
import { UserContactService } from '../../../services/userContact/user-contact.service';

@Component({
  selector: 'app-contacts-invitations-list',
  standalone: true,
  imports: [],
  templateUrl: './contacts-invitations-list.component.html',
  styleUrl: './contacts-invitations-list.component.css'
})
export class ContactsInvitationsListComponent {

  constructor(
    private sharedDataService: SharedDataService,
    private userContactService: UserContactService
  ) { }

  @Input() user!: User;
  @Input() invitations: any[] = [];
  @Input() option!: string;

  cancelSolicitud(solicitudId: number) {
    this.userContactService.cancelSolicitud(solicitudId, this.user.id).subscribe({
      next: (response: any) => {
        if (response) {
          this.sharedDataService.updateInvitationsList(this.user)
          swalAlert(
            'success',
            'Solicitud eliminada',
            'Se ha eliminado la solicitud exitosamente.'
          )
        }
      },
      error: (error: any) => {
        console.log(error.message)
        swalAlert(
          'error',
          'Error',
          'No se ha podido eliminar la solicitud, por favor intente de nuevo.'
        )
      }
    })
  }

  acceptSolicitud(solicitudId: number) {
    this.userContactService.acceptSolicitud(solicitudId, this.user.id).subscribe({
      next: (response: any) => {
        if (response) {
          this.sharedDataService.updateInvitationsList(this.user)
          this.sharedDataService.updateContactsList(this.user)
          swalAlert(
            'success',
            'Solicitud aceptada',
            'Se ha aceptado la solicitud exitosamente.'
          )
        }
      },
      error: (error: any) => {
        console.log(error.message)
        swalAlert(
          'error',
          'Error',
          'No se ha podido aceptar la solicitud, por favor intente de nuevo.'
        )
      }
    })
  }
}
