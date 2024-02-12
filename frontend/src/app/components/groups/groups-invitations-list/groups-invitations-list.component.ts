import { Component, Input } from '@angular/core';
import { User } from '../../../models/user/user.model';

@Component({
  selector: 'app-groups-invitations-list',
  standalone: true,
  imports: [],
  templateUrl: './groups-invitations-list.component.html',
  styleUrl: './groups-invitations-list.component.css'
})
export class GroupsInvitationsListComponent {
  @Input() invitations : any;
  @Input() user!: User;

}
