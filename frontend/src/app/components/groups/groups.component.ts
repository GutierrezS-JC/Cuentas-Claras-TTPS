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
import { AuthenticationService } from '../../services/authentication/authentication.service';
import { User } from '../../models/user/user.model';
import { decodeJwt } from '../../utils/jwt-decode';
import { UserService } from '../../services/user/user.service';
import { FormsModule } from '@angular/forms';
import { GroupsSidebarComponent } from './groups-sidebar/groups-sidebar.component';
import { GroupsMainComponent } from './groups-main/groups-main.component';
import { SharedDataService } from '../../services/sharedData/shared-data.service';

@Component({
  selector: 'app-groups',
  standalone: true,
  imports: [NgIconComponent, NavbarComponent, FooterComponent, GroupsListComponent, GroupsSidebarComponent,
    GroupsMainComponent, GroupCreateComponent, GroupDetailsComponent, FormsModule],
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
    private userService: UserService,
    private sharedDataService: SharedDataService
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

  // Opcion de grupo en radio checks para mostrar la lista seleccionada
  selectedGroupOption: string = 'misGrupos';

  ngOnInit(): void {
    const token = this.authService.currentUserValue.token as string;
    const username = (decodeJwt(token).sub)

    this.userService.getUserDetails(username).subscribe({
      next: userDetails => {
        this.currentUser = userDetails;

        // Hacemos la suscripcion al observable de grupos :)
        this.sharedDataService.groups$.subscribe(updatedGroups => {
          this.groups = updatedGroups;
        });

        this.sharedDataService.updateGroupList(this.currentUser);
      },
      error: error => {
        console.error('Error al obtener detalles del usuario:', error);
      }
    });
    
  }

  // Selector de tipo de grupo (mis grupos y grupos en los que participa el usuario)
  setGroupOption(option: string) {
    this.selectedGroupOption = option;
    this.resetActualGroup();
    this.resetGroupSpendings();
  }

  // Recibo el evento de actualizacion desde groups-sidebar
  onActualGroupChange(newActualGroup: GroupDetails) {
    this.actualGroup = newActualGroup;
    if (this.actualGroup.groupId !== -1) {
      this.getGroupSpendings(newActualGroup.groupId);
    }
  }

  // Obtengo los gastos del grupo recibido por el evento de actualizacion
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

  // Reset de actualGroup al estado inicial
  resetActualGroup() {
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

  // Reset de groupSpendings al estado inicial
  resetGroupSpendings() {
    this.groupSpendings = [];
  }
}

