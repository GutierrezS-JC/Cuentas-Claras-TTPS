import { Component, Input } from '@angular/core';
import { User } from '../../../models/user/user.model';
import { swalAlert } from '../../../utils/sweet-alert';
import { InvitationService } from '../../../services/invitation/invitation.service';

@Component({
  selector: 'app-groups-invitations-list',
  standalone: true,
  imports: [],
  templateUrl: './groups-invitations-list.component.html',
  styleUrl: './groups-invitations-list.component.css'
})
export class GroupsInvitationsListComponent {
  constructor(
    private invitationService: InvitationService
  ) { }

  @Input() invitations : any;
  @Input() user!: User;

  cancelInvitation(invitationId: number) {
    this.invitationService.cancelInvitation(invitationId, this.user.id).subscribe({
      next: (response: any) => {
        if(response){
          swalAlert(
            'success',
            'Invitación cancelada',
            'Se ha cancelado la invitación exitosamente.'
          )
        }
      },
      error: (error: any) => {
        console.log(error.message)
        swalAlert(
          'error',
          'Error',
          'No se ha podido cancelar la invitación, por favor intente de nuevo.'
        )
      }
    })
  }
}
