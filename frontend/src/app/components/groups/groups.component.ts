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

  // Opcion de grupo en radio checks para mostrar la lista seleccionada
  selectedGroupOption: string = 'misGrupos';

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

  // Selector de tipo de grupo (mis grupos y grupos en los que participa el usuario)
  setGroupOption(option: string) {
    this.selectedGroupOption = option;
  }
}

