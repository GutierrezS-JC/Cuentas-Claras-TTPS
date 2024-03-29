import { Component, Input } from '@angular/core';
import { NgIconComponent, provideIcons } from '@ng-icons/core';
import { faSolidCirclePlus } from '@ng-icons/font-awesome/solid';

@Component({
  selector: 'app-groups-list',
  standalone: true,
  imports: [NgIconComponent],
  providers: [provideIcons({ faSolidCirclePlus })],
  templateUrl: './groups-list.component.html',
  styleUrl: './groups-list.component.css'
})
export class GroupsListComponent {
  @Input() groupSpendings: any;
  @Input() setSelectedSpending!: (spending: any) => void;
}
