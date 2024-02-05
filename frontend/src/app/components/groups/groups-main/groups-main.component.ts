import { Component, Input } from '@angular/core';
import { Groups } from '../../../models/groups/groups.model';
import { GroupDetails } from '../../../models/groups/groupDetails.model';
import { GroupsInvitationsListComponent } from '../groups-invitations-list/groups-invitations-list.component';
import { GroupsListComponent } from '../groups-list/groups-list.component';
import { GroupDetailsComponent } from '../../modals/group/group-details/group-details.component';

@Component({
  selector: 'app-groups-main',
  standalone: true,
  imports: [GroupsInvitationsListComponent, GroupsListComponent, GroupDetailsComponent],
  templateUrl: './groups-main.component.html',
  styleUrl: './groups-main.component.css'
})
export class GroupsMainComponent {

  @Input() groups!: Groups
  @Input() actualGroup!: GroupDetails;
  @Input() groupSpendings: any[] = [];
  @Input() selectedGroupOption!: string;
  
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
}
