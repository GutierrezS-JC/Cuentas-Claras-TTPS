import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { forkJoin } from 'rxjs';

import { Spending } from '../../models/spending.model';
import { User } from '../../models/user.model';
import { SpendingCategory } from '../../models/spendingCategory.model';
import { Group } from '../../models/group.model';
import { SpendingService } from '../../services/spending.service';
import { UserContact } from '../../models/userContact.model';
import { SpendingUser } from '../../models/spendingUser.model';


@Component({
  selector: 'app-spendings',
  standalone: true,
  imports: [ FormsModule, CommonModule ],
  templateUrl: './spendings.component.html',
  styleUrl: './spendings.component.css'
})

export class SpendingsComponent {
  showTable = false;

  user: User = new User(0,"", "");
  groups: Group[] = [];
  members: User[] = []; //estos son los usuarios del grupo que selecciono
  spendingUsers: User[] = []; //estos son los usuarios que participan en el gasto
  spendingMembers: SpendingUser[] = []; //estos son los usuarios que participan en el gasto con el monto
  categories: SpendingCategory[] = [];
  contacts: UserContact[] = [];
  spendings: Spending[] = [];
  spendingsWithGroup: Spending[] = [];
  spendingsWithoutGroup: Spending[] = [];

  newSpending: Spending = new Spending("", "", 0, new Date(), new Date(), "", "NON", "EVEN", 0, 0, new Group(0,"",0, new User(0,"",""), this.members, 1, new Array<Spending>), this.spendingMembers);

  editableSpending: Spending = new Spending("", "", 0, new Date(), new Date(), "", "NON", "EVEN", 0, 0, new Group(0,"",0, new User(0,"",""), this.members, 1, new Array<Spending>), this.spendingMembers);

  showTableFunc() {
    this.showTable = true;
  }
  showTableIndividualFunc() {
    this.showTable = false;
  }

  addMember( event: any ){
    if (this.newSpending.group) {
      const userAlreadyAdded = this.spendingUsers.some(u => u.id === event.id);
      if (!userAlreadyAdded) {
        this.spendingUsers.push( event );
      };
      console.log(this.spendingUsers);
    }
  }

  deleteMember( member: User ){
    this.spendingUsers = this.spendingUsers.filter( m => m.id !== member.id );
  }

  getGroupSelect() {
    if (this.newSpending.group) {
      this.members = this.newSpending.group.members;
    }
  }

  constructor(private spendingService: SpendingService){}

  onSubmit(): void {
    this.spendingService.createSpending(this.newSpending).subscribe(
      (response => {
        console.log('Gasto creado correctamente', response);
        
      }),
      (error) => {
        console.log('Error al crear el gasto', error);
      }
    )
  }

  getSpendingToEdit(spending: Spending){}
  editSpending(): void {

  }


  ngOnInit(){
    this.spendingsWithGroup = this.spendings.filter(s => s.group);
    this.spendingsWithoutGroup = this.spendings.filter(s => !s.group);
    forkJoin({
      groups: this.spendingService.getGroups(),
      categories: this.spendingService.getSpendingCategories(),
     // contacts: this.spendingService.getContacts(),
      spendings: this.spendingService.getAllSpendings()
    }).subscribe({
        next: (result: any) => {
          this.categories = result.categories;
          this.contacts = result.contacts;
          this.groups = result.groups;
          this.spendings = result.spendings;
        },
        error: (error) => {
          console.log('Error al obtener los datos', error);
        }
      })
  }

}
