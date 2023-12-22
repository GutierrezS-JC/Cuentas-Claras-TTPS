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

@Component({
  selector: 'app-groups',
  standalone: true,
  imports: [NgIconComponent, NavbarComponent, FooterComponent, GroupsListComponent,
    GroupCreateComponent, GroupDetailsComponent],
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
  actualGroupId: number = 0;
  actualGroup: GroupDetails = {
    groupId: 0,
    name: '',
    totalBalance: 0,
    groupCategory: {},
    description: '',
    owner: {},
    members: [],
    invitations: []
  };

  ngOnInit(): void {
    this.getGroups();
  }

  getGroups() {
    this.groupsService.getAllGroups().subscribe(
      (response) => { 
        this.groups = response;
        const actualGroupId = response.listOwnedGroups[0].groupId || '';
        this.getGroup(actualGroupId)
      },
      (error) => {
        console.error('Error carga de grupos', error);
      }
    )
  }

  getGroup(actualGroupId: number) {
    this.groupsService.getGroup(actualGroupId).subscribe(
      (res) => {
        this.actualGroup = res;
      },
      (err) => {
        console.error('Error busqueda de grupo individual', err);
      }
    );
  }
}
