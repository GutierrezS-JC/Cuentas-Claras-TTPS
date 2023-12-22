import { Component } from '@angular/core';
import { NgIconComponent, provideIcons } from '@ng-icons/core';
import { faSolidFileArrowDown } from '@ng-icons/font-awesome/solid';

@Component({
  selector: 'app-group-details',
  standalone: true,
  imports: [NgIconComponent],
  providers: [provideIcons({ faSolidFileArrowDown })],
  templateUrl: './group-details.component.html',
  styleUrl: './group-details.component.css'
})
export class GroupDetailsComponent {

}
