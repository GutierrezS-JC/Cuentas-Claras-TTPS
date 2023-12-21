import { NgIconComponent, provideIcons } from '@ng-icons/core';
import { Component } from '@angular/core';
import { NavbarComponent } from '../navbar/navbar.component';
import { FooterComponent } from '../footer/footer.component';
import { faSolidCakeCandles, faSolidPlaneDeparture, faSolidBagShopping, faSolidPeopleGroup, faSolidTaxi } from '@ng-icons/font-awesome/solid';

@Component({
  selector: 'app-groups',
  standalone: true,
  imports: [NgIconComponent, NavbarComponent, FooterComponent],
  providers: [provideIcons({ faSolidCakeCandles, faSolidPlaneDeparture, faSolidBagShopping, faSolidPeopleGroup, faSolidTaxi })],
  templateUrl: './groups.component.html',
  styleUrl: './groups.component.css'
})
export class GroupsComponent {

}
