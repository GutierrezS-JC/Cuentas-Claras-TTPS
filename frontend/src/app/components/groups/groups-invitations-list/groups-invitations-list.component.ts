import { Component, EventEmitter, Input, Output } from '@angular/core';
import { User } from '../../../models/user/user.model';
import { swalAlert } from '../../../utils/sweet-alert';
import { InvitationService } from '../../../services/invitation/invitation.service';
import { GroupDetails } from '../../../models/groups/groupDetails.model';
import { GroupsService } from '../../../services/groups/groups.service';

@Component({
  selector: 'app-groups-invitations-list',
  standalone: true,
  imports: [],
  templateUrl: './groups-invitations-list.component.html',
  styleUrl: './groups-invitations-list.component.css'
})
export class GroupsInvitationsListComponent {
  constructor(
    private invitationService: InvitationService,
    private groupsService: GroupsService,
  ) { }

  @Input() invitations: any;
  @Input() user!: User;
  @Input() actualGroup!: GroupDetails;

  @Output() actualGroupChange = new EventEmitter<GroupDetails>();

  cancelInvitation(invitationId: number) {
    this.invitationService.cancelInvitation(invitationId, this.user.id).subscribe({
      next: (response: any) => {
        if (response) {
          this.groupsService.getGroup(this.actualGroup.groupId).subscribe({
            next: (res: any) => {
              this.actualGroup = res;
              // Emite el evento que indica la actualizacion del grupo seleccionado
              this.actualGroupChange.emit(this.actualGroup);
              swalAlert(
                'success',
                'Invitación cancelada',
                'Se ha cancelado la invitación exitosamente.'
              )
            }
          })
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
