import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Groups } from '../../../models/groups/groups.model';
import { GroupsService } from '../../../services/groups/groups.service';
import { GroupDetails } from '../../../models/groups/groupDetails.model';

@Component({
  selector: 'app-groups-sidebar',
  standalone: true,
  imports: [],
  templateUrl: './groups-sidebar.component.html',
  styleUrl: './groups-sidebar.component.css'
})
export class GroupsSidebarComponent {

  constructor(private groupsService: GroupsService) { }

  @Input() groups!: Groups
  @Input() selectedGroupOption!: string;

  @Input() actualGroup!: GroupDetails;
  @Output() actualGroupChange = new EventEmitter<GroupDetails>();

  @Input() groupSpendings: any[] = [];

  getGroup(groupId: number) {
    if (this.actualGroup && this.actualGroup.groupId === groupId) {
      // Si ya esta seleccionado el grupo... eliminamos la seleccion 
      // volviendo a setear los valores por defecto
      this.resetActualGroup();
      // Emite el evento que indica la actualizacion del grupo seleccionado
      this.actualGroupChange.emit(this.actualGroup);

    }
    else {
      this.groupsService.getGroup(groupId).subscribe({
        next: (res: any) => {
          this.actualGroup = res;
          // Emite el evento que indica la actualizacion del grupo seleccionado
          this.actualGroupChange.emit(this.actualGroup);
          this.getGroupSpendings(groupId)
        },
        error: (error) => {
          console.log('Ocurrio un error: ')
          console.log(error)
        }
      })
    }
  }

  getGroupSpendings(groupId: number) {
    this.groupsService.getGroupSpendings(groupId).subscribe({
      next: (res: any) => {
        this.groupSpendings = res;
      },
      error: (error) => {
        console.log(error.message)
      }
    })
  }

  // Usado para setear los detalles del gasto seleccionado
  isSelectedGroup(groupId: number): boolean {
    return !!this.actualGroup.groupId && (groupId === this.actualGroup.groupId);
  }

  // Usado para setear los detalles del grupo a su valor por defecto
  resetActualGroup = () => {
    this.actualGroup = {
      groupId: -1,
      name: '',
      totalBalance: 0,
      groupCategory: {},
      description: '',
      owner: {},
      members: [],
      invitations: []
    };
  }

}
