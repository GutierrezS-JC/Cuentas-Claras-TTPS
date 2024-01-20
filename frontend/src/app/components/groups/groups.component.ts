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
  constructor(private groupsService: GroupsService) { }

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


  ngOnInit(): void {
    this.getGroups();
  }

  getGroupSpendings(groupId: number) {
    this.groupsService.getGroupSpendings(groupId).subscribe({
      next: (res: any) => {
        console.log(res)
        this.groupSpendings = res;
      },
      error: (error) => {
        console.log(error.message)
      },
      complete: () => console.info('API call completed')
    })
  }

  getGroupCategories() {
    this.groupsService.getGroupCategories().subscribe({
      next: (res: any) => {
        console.log(res)
        this.groupCategories = res;
      },
      error: (error) => {
        console.log(error.message)
      },
      complete: () => console.info('API call completed')
    })
  }

  getGroups() {
    this.groupsService.getAllGroups().subscribe({
      next: (res: any) => {
        this.groups = res;
        // const actualGroupId = res.listOwnedGroups[0]?.groupId || -1;
        const actualGroupId = res.listOwnedGroups.length > 0 ? res.listOwnedGroups[0].groupId : -1;
        this.getGroup(actualGroupId);
        this.getGroupSpendings(actualGroupId);
      },
      error: (error) => {
        console.log(error.message)
      },
      complete: () => console.info('API call completed')
    })
  }

  getGroup(groupId: number) {
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