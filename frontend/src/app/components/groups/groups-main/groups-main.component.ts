import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Groups } from '../../../models/groups/groups.model';
import { GroupDetails } from '../../../models/groups/groupDetails.model';
import { GroupsInvitationsListComponent } from '../groups-invitations-list/groups-invitations-list.component';
import { GroupsListComponent } from '../groups-list/groups-list.component';
import { GroupDetailsComponent } from '../../modals/group/group-details/group-details.component';
import { User } from '../../../models/user/user.model';
import { GroupEditComponent } from '../../modals/group/group-edit/group-edit.component';
import { GroupMembersComponent } from '../../modals/group/group-members/group-members.component';

@Component({
  selector: 'app-groups-main',
  standalone: true,
  imports: [GroupsInvitationsListComponent, GroupsListComponent, GroupDetailsComponent, 
    GroupEditComponent, GroupMembersComponent],
  templateUrl: './groups-main.component.html',
  styleUrl: './groups-main.component.css'
})
export class GroupsMainComponent {

  @Input() groups!: Groups
  @Input() actualGroup!: GroupDetails;
  @Input() groupSpendings: any[] = [];
  @Input() selectedGroupOption!: string;
  
  @Input() user!: User;
  @Output() actualGroupChange = new EventEmitter<GroupDetails>();

  // Spending (gasto) seleccionado - ACTUAL
  selectedSpending: any | null = null;

  // Usado para setear los detalles del gasto seleccionado
  setSelectedSpending = (spending: any) => {
    this.selectedSpending = spending;
  }

  // Eliminamos el detalle del gasto seleccionado
  deleteSelectedSpending = () => {
    this.selectedSpending = null;
  }

  onActualGroupChange(newActualGroup: GroupDetails) {
    this.actualGroupChange.emit(newActualGroup);
  }
}
