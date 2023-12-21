import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { forkJoin } from 'rxjs';

import { Spending } from '../../models/spending.model';
import { User } from '../../models/user.model';
import { DivisionEnum } from '../../models/divisionEnum.model';
import { RecurrentEnum } from '../../models/recurrentEnum.model';
import { SpendingCategory } from '../../models/spendingCategory.model';
import { Group } from '../../models/group.model';
import { SpendingService } from '../../services/spending.service';
import { UserContact } from '../../models/userContact.model';
import { RouterModule } from '@angular/router';
import { FooterComponent } from '../footer/footer.component';
import { NavbarComponent } from '../navbar/navbar.component';


@Component({
  selector: 'app-spendings',
  standalone: true,
  imports: [RouterModule, FormsModule, CommonModule, FooterComponent, NavbarComponent],
  templateUrl: './spendings.component.html',
  styleUrl: './spendings.component.css'
})

export class SpendingsComponent {
  showTable = false;

  groups: Group[] = [];
  members: User[] = [];
  categories: SpendingCategory[] = [];
  contacts: UserContact[] = [];
  spendings: Spending[] = [];
  spendingsWithGroup: Spending[] = [];
  spendingsWithoutGroup: Spending[] = [];

  newSpendingGroup: Spending = new Spending("", "", 0, new Date(), new Date(), "", new RecurrentEnum(""), new DivisionEnum(""), new User(""), new SpendingCategory(""), new Group(""), new Array<User>());
  //newSpendingIndividual: Spending = new Spending();

  showTableFunc() {
    this.showTable = true;
  }
  showTableIndividualFunc() {
    this.showTable = false;
  }

  constructor(private spendingService: SpendingService) { }

  onSubmitGroup(): void {
    this.spendingService.createSpending(this.newSpendingGroup).subscribe(
      (response => {
        console.log('Gasto creado correctamente', response);
      }),
      (error) => {
        console.log('Error al crear el gasto', error);
      }
    )
  }

  onSubmitIndividual() { }

  ngOnInit() {
    forkJoin({
      groups: this.spendingService.getGroups(),
      // members: this.spendingService.getMembers(),
      categories: this.spendingService.getSpendingCategories(),
      // contacts: this.spendingService.getContacts(),
      spendings: this.spendingService.getAllSpendings()
    }).subscribe({
      next: (result: any) => {
        this.categories = result.categories;
        this.contacts = result.contacts;
        this.groups = result.groups;
        this.members = result.members;
        this.spendings = result.spendings;
      },
      error: (error) => {
        console.log('Error al obtener los datos', error);
      }
    })
  }

  filterSpendings() {
    this.spendingsWithGroup = this.spendings.filter(s => s.group);
    this.spendingsWithoutGroup = this.spendings.filter(s => !s.group);
  }
}
