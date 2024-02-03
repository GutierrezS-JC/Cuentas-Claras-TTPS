import { NgIconComponent, provideIcons } from '@ng-icons/core';
import { Component, OnInit } from '@angular/core';
import { NavbarComponent } from '../navbar/navbar.component';
import { FooterComponent } from '../footer/footer.component';
import {
  faSolidCakeCandles, faSolidPlaneDeparture, faSolidBagShopping,
  faSolidPeopleGroup, faSolidTaxi
} from '@ng-icons/font-awesome/solid';
import { GroupsListComponent } from './groups-list/groups-list.component';
import { GroupCreateComponent } from '../modals/group/group-create/group-create.component';
import { GroupDetailsComponent } from '../modals/group/group-details/group-details.component';
import { GroupsService } from '../../services/groups/groups.service';
import { Groups } from '../../models/groups/groups.model';
import { GroupDetails } from '../../models/groups/groupDetails.model';
import { GroupCategories } from '../../models/groupCategories/groupCategories.model';
import { GroupsInvitationsListComponent } from './groups-invitations-list/groups-invitations-list.component';
import { AuthenticationService } from '../../services/authentication/authentication.service';
import { UserToken } from '../../models/user/user-token.model';
import { User } from '../../models/user/user.model';
import { decodeJwt } from '../../utils/jwt-decode';
import { UserService } from '../../services/user/user.service';

@Component({
  selector: 'app-groups',
  standalone: true,
  imports: [NgIconComponent, NavbarComponent, FooterComponent, GroupsListComponent,
    GroupCreateComponent, GroupDetailsComponent, GroupsInvitationsListComponent],
  providers: [
    provideIcons({
      faSolidCakeCandles, faSolidPlaneDeparture, faSolidBagShopping,
      faSolidPeopleGroup, faSolidTaxi
    })],
  templateUrl: './groups.component.html',
  styleUrl: './groups.component.css'
})

export class GroupsComponent implements OnInit {

  constructor(
    private groupsService: GroupsService, 
    private authService: AuthenticationService,
    private userService: UserService
    ) { }

  currentUser!: User | null;

  groups: Groups = { listGroups: [], listOwnedGroups: [] };
  actualGroup: GroupDetails = {
    groupId: -1,
    name: '',
    totalBalance: 0,
    groupCategory: {},
    description: '',
    owner: {},
    members: [],
    invitations: []
  };
  error: any = {
    message: '',
    description: '',
    status: ''
  }

  // Categorias para la creacion de grupo
  groupCategories: GroupCategories[] = [{ id: 0, name: '', base64Image: '' }];

  // spendings
  groupSpendings: any[] = [];

  // Spending (gasto) seleccionado - ACTUAL
  selectedSpending: any | null = null;

  ngOnInit(): void {
    const token = this.authService.currentUserValue.token as string;
    const username = (decodeJwt(token).sub)

    this.userService.getUserDetails(username).subscribe({
      next: userDetails => {
        this.currentUser = userDetails;
        this.getGroups();
      },
      error: error => {
        console.error('Error al obtener detalles del usuario:', error);
      }
    });
  }

  getGroupSpendings(groupId: number) {
    this.groupsService.getGroupSpendings(groupId).subscribe({
      next: (res: any) => {
        this.groupSpendings = res;
      },
      error: (error) => {
        console.log(error.message)
      },
      complete: () => console.info('API call completed')
    })
  }

  getGroupCategories() {
    this.groupsService.getGroupCategoriesNoBs().subscribe({
      next: (res: any) => {
        this.groupCategories = res;
      },
      error: (error) => {
        console.log(error)
      },
      complete: () => console.info('API call completed')
    })
  }

  getGroups() {
    this.groupsService.getAllGroups(this.currentUser!.id).subscribe({
      next: (res: any) => {
        console.log(res)
        this.groups = res;
      },
      error: (error) => {
        console.log(error.message)
      },
      complete: () => console.info('API call completed')
    })
  }

  getGroup(groupId: number) {
    if (this.actualGroup && this.actualGroup.groupId === groupId) {
      // Si ya esta seleccionado el grupo... eliminamos la seleccion 
      // volviendo a setear los valores por defecto
      this.resetActualGroup();
    }
    else {
      this.groupsService.getGroup(groupId).subscribe({
        next: (res: any) => {
          this.actualGroup = res;
          this.getGroupSpendings(groupId)
        },
        error: (error) => {
          this.error.message = "El usuario no tiene grupos";
          this.error.status = error.status;
          this.error.description = error.message
        },
        complete: () => console.info('API call completed')
      })
    }
  }
  // Usado para setear los detalles del gasto seleccionado
  setSelectedSpending = (spending: any) => {
    this.selectedSpending = spending;
  }

  // Eliminamos el detalle del gasto seleccionado
  deleteSelectedSpending = () => {
    this.selectedSpending = null;
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

