import { Component } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { forkJoin } from 'rxjs';

import { Spending } from '../../models/spending.model';
import { User } from '../../models/user.model';
import { SpendingCategory } from '../../models/spendingCategory.model';
import { Group } from '../../models/group.model';
import { SpendingService } from '../../services/spending.service';
import { UserContact } from '../../models/userContact.model';
import { SpendingUser } from '../../models/spendingUser.model';

import { MySpending } from '../../models/mySpending.model';
declare var $: any;



@Component({
  selector: 'app-spendings',
  standalone: true,
  imports: [ FormsModule, CommonModule ],
  providers: [ DatePipe ],
  templateUrl: './spendings.component.html',
  styleUrl: './spendings.component.css'
})

export class SpendingsComponent {
  showTable = false;

  user: User = new User(0,"",0, "");
  groups: Group[] = [];
  members: User[] = []; //estos son los usuarios del grupo que selecciono
  spendingUsers: User[] = []; //estos son los usuarios que participan en el gasto
  spendingMembers: SpendingUser[] = []; //estos son los usuarios que participan en el gasto con el monto que le corresponde a cada uno
  categories: SpendingCategory[] = [];
  contacts: UserContact[] = []; // todos los contactos de un usuario, necesario para crear un gasto individual

  spendings: MySpending[] = [];
  

  // objeto Spending que se crea con el formulario
  newSpending: Spending = new Spending("", "", 0, new Date(), new Date(), "imgComprobante", "NON", "", 0, 0, new Group(0,"",0, new User(0,"", 0, ""), this.members, 1, new Array<Spending>), this.spendingMembers);

  // objeto Spending que se edita con el formulario
  editableSpending: Spending = new Spending("", "", 0, new Date(), new Date(), "", "NON", "", 0, 0, new Group(0,"",0, new User(0,"", 0, ""), this.members, 1, new Array<Spending>), this.spendingMembers);
  
  // objeto Spending seleccionado para editar
  selectedSpendingToEdit: number = 0;


  // estas dos funciones se ocupan de mostrar una u otra tabla según se seleccione el botón
  showTableFunc() {
    this.showTable = true;
  }
  showTableIndividualFunc() {
    this.showTable = false;
  }

  // toma el grupo seleccionado en el desplegable y guarda los usuarios del mismo en la lista members
  getGroupSelect() {
    if (this.newSpending.group) {
      this.members = this.newSpending.group.members;
    }
  }

  // agrega un elemento User de la lista de miembros del grupo seleccionado a la lista de usuarios que participan en el gasto
  addMember( event: any ){
    if (this.newSpending.group) {
      const userAlreadyAdded = this.spendingUsers.some(u => u.id === event.id);
      if (!userAlreadyAdded) {
        this.spendingUsers.push( event );
      };
    }
  }

  // quita un elemento User de la lista de usuarios que participan en el gasto
  deleteMember( member: User ){
    this.spendingUsers = this.spendingUsers.filter( m => m.id !== member.id );
  }

  // inicia el servicio de los gastos
  constructor(private spendingService: SpendingService, private datePipe: DatePipe){}
  // función del formulario para la creación del gasto
  onSubmit(): void {
    this.newSpending.createdAt = this.datePipe.transform( this.newSpending.createdAt, 'yyyy-MM-dd' ) as unknown as Date;
    this.newSpending.endingDate = this.datePipe.transform( this.newSpending.endingDate, 'yyyy-MM-dd' ) as unknown as Date;
    this.spendingService.createSpending(this.newSpending).subscribe(
      (response => {
        console.log('Gasto creado correctamente', response);
      }),
      (error) => {
        console.log('Error al crear el gasto', error);
      }
    )
  }

  
  // esta función define el monto que le corresponde a cada participante del gasto según la opción de división que se tomó
  calculateAmount(amount: number): void {
    let division = this.newSpending.division;
    switch (division) {
      case "EVEN":
        this.newSpending.users = this.spendingUsers.map(user => {
          const mappedUser = { userId: user.id, amount: amount / this.spendingUsers.length };
          return mappedUser;
        });
        break;
      // TODO: los restantes casos, voy a usar EVEN para probar la creación
      default:
        break;
    }
  }

  // mapea al objeto las cantidades ingresadas por el usuario
  calculateFixAmount(event: Event): void {
    event.preventDefault();
    console.log(this.spendingUsers);
    this.newSpending.users = this.spendingUsers.map(user => {
      const mappedUser = { userId: user.id, amount: user.amount };
      return mappedUser;
    });
  }

  // función que se ejecuta al inicio para obtener los datos iniciales
  ngOnInit(){
    forkJoin({
      groups: this.spendingService.getGroups(),
      categories: this.spendingService.getSpendingCategories(),
     // contacts: this.spendingService.getContacts(),
      spendings: this.spendingService.getMySpendingsExtended()
    }).subscribe({
        next: (result: any) => {
          this.categories = result.categories;
          //this.contacts = result.contacts;
          this.groups = result.groups;
          this.spendings = result.spendings;
        },
        error: (error) => {
          console.log('Error al obtener los datos', error);
        }
      })
  }

  // funciones para la edición del gasto TODO
  getSpendingToEdit(spendingId: number): void {
    this.selectedSpendingToEdit = spendingId;
    $('#editSpending').modal('show');
  }
  editSpending(event: Event): void {
    event.preventDefault();
  }
}
