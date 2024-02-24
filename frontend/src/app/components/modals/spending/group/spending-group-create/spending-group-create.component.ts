import { Component, Input } from '@angular/core';
import { FormArray, FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { SpendingUser } from '../../../../../models/spendingUser.model';
import { GroupsService } from '../../../../../services/groups/groups.service';
import { SpendingService } from '../../../../../services/spending.service';
import { UserWithAmount } from '../../../../../models/spending/user-with-amount.model';
import { GroupDetails } from '../../../../../models/groups/groupDetails.model';
import { User } from '../../../../../models/user/user.model';

@Component({
  selector: 'app-spending-group-create',
  standalone: true,
  imports: [ReactiveFormsModule, FormsModule],
  templateUrl: './spending-group-create.component.html',
  styleUrl: './spending-group-create.component.css'
})
export class SpendingGroupCreateComponent {

  constructor(
    private spendingService: SpendingService,
    private groupsService: GroupsService
  ) { }

  @Input() spendingCategories: any;
  @Input() groups: any;
  @Input() currentUser: any;

  createSpendingForm: FormGroup = new FormGroup({
    groupId: new FormControl(-1, Validators.min(1)),
    totalAmount: new FormControl(0),
    createdAt: new FormControl(new Date()),
    description: new FormControl(''),
    division: new FormControl(''),
    spendingCategory: new FormControl(''),
    recurrence: new FormControl(''),
    endingDate: new FormControl(new Date()),
  })

  // Guardamos el userId --> spendingUser
  usersWithAmount: UserWithAmount[] = [];
  usersSelected: User[] = [];

  // Los users que son parte del grupo seleccionado
  groupUsers: any[] | null = null;

  // Busco los miembros del grupo para el select
  getGroupMembersSelect() {
    if (this.createSpendingForm.get('groupId')?.value) {
      this.groupsService.getGroup(this.createSpendingForm.get('groupId')?.value).subscribe(
        (group: GroupDetails) => {
          this.groupUsers = group.members;
        }
      );
    }
  }

  addMember(member: User) {
    const groupId = this.createSpendingForm.get('groupId')?.value;
    if (groupId && groupId !== -1) {
      const userAlreadyAdded = this.usersSelected.some(user => user.id === member.id);
      if (!userAlreadyAdded) {
        // this.usersSelected.push(member);
        this.usersSelected = [...this.usersSelected, member];
        this.usersWithAmount = [...this.usersWithAmount, { userId: member.id, amount: 0 }];
      };
    }
  }

  deleteMember(member: User) {
    this.usersSelected = this.usersSelected.filter(user => user.id !== member.id);
    this.usersWithAmount = this.usersWithAmount.filter(user => user.userId !== member.id);
  }

  onSubmit(): void {
    // this.newSpending.createdAt = this.datePipe.transform(this.newSpending.createdAt, 'yyyy-MM-dd') as unknown as Date;
    // this.newSpending.endingDate = this.datePipe.transform(this.newSpending.endingDate, 'yyyy-MM-dd') as unknown as Date;
    // this.spendingService.createSpending(this.newSpending, this.currentUser!.id).subscribe(
    //   (response => {
    //     console.log('Gasto creado correctamente', response);
    //   }),
    //   (error) => {
    //     console.log('Error al crear el gasto', error);
    //   }
    // )
  }

  calculateAmount(): void {
    let division = this.createSpendingForm.get('division')?.value
    let amount = this.createSpendingForm.get('amount')?.value;
    
    if (amount !== undefined && division !== undefined) {
      switch (division) {
        case "EVEN":
          this.usersWithAmount.map(user => {
            const mappedUser = { userId: user.userId, amount: (amount / this.usersWithAmount.length) };
            return mappedUser;
          });
          break;
        // TODO: los restantes casos, voy a usar EVEN para probar la creación
        default:
          break;
      }
    }
  }

  // No deberia ser un FORM. Refactorizar!
  calculateFixAmount(event: Event): void {
    event.preventDefault();
    this.usersWithAmount = this.usersWithAmount.map(user => {
      const mappedUser = { userId: user.userId, amount: user.amount };
      return mappedUser;
    });
  }

}
