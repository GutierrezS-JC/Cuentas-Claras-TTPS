import { Component, Input, OnInit } from '@angular/core';
import { GroupCreate } from '../../../../models/groups/gropCreate.model';
import { FormArray, FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { UserService } from '../../../../services/user/user.service';
import { GroupsService } from '../../../../services/groups/groups.service';
import { GroupCategories } from '../../../../models/groupCategories/groupCategories.model';
import { User } from '../../../../models/user/user.model';
import { GroupDetails } from '../../../../models/groups/groupDetails.model';

@Component({
  selector: 'app-group-create',
  standalone: true,
  imports: [CommonModule, FormsModule, ReactiveFormsModule],
  templateUrl: './group-create.component.html',
  styleUrl: './group-create.component.css'
})
export class GroupCreateComponent implements OnInit {
  constructor(private userService: UserService, private groupsService: GroupsService) { }
  @Input() groupCategories: any;
  @Input() user!: User;

  loading = false;
  submitted = false;

  createForm = new FormGroup({
    name: new FormControl(''),
    description: new FormControl(''),
    categoryId: new FormControl(-1, Validators.min(1)),
    amount: new FormControl(0),
    selectedUserIds: new FormArray([]),
    searchInput: new FormControl('')
  })

  // Arreglo de usuarios buscados/encontrados
  searched: any = [];

  selectedUsers: User[] = [];

  ngOnInit(): void {
    this.groupsService.getGroupCategories().subscribe({
      next: (res: any) => {
        this.groupCategories = res;
      },
      error: (error) => {
        console.log(error)
      }
    })
  }

  handleGroupCreation() {
    this.submitted = true;
    if (this.createForm.valid) {
      console.log('Es valido')
      this.loading = true;

      let idList = this.createForm.controls.selectedUserIds.value.map((userId: number) => {
        return { id: userId }
      })

      let group: GroupCreate = {
        name: this.createForm.controls.name.value as string,
        totalBalance: this.createForm.controls.amount.value as number,
        userOwnerId: this.user.id,
        description: this.createForm.controls.description.value as string,
        groupCategoryId: this.createForm.controls.categoryId.value as number,
        usersIds: idList
      };
      //Subscribe
      this.groupsService.createGroup(group).subscribe({
        next: (res: any) => {
          console.log('Grupo creado')
        },
        error: (error) => {
          console.log(error)
        }
      });
    }
  }

  // Busqueda de usuarios en formulario de creacion de grupo
  handleSearch() {
    this.userService.searchUser(this.user.id, this.createForm.controls.searchInput.value as string).subscribe({
      next: (res: any) => {
        this.searched = res;
      },
      error: (error) => {
        console.log(error.message)
      }
    })
  }

  handleClose() {
    this.createForm.reset({
      name: '',
      description: '',
      categoryId: -1,
      amount: 0,
      searchInput: ''
    });

    const selectedUserIdsArray = this.createForm.get('selectedUserIds') as FormArray;
    selectedUserIdsArray.clear()

    this.searched = [];
    this.selectedUsers = [];
  }

  // Agregar usuario de la lista de integrantes del grupo
  handleAddUser(user: User) {
    const selectedUserIdsArray = this.createForm.get('selectedUserIds') as FormArray;

    if (selectedUserIdsArray.value.includes(user.id)) {
      console.log('El usuario ya está en la lista');
      return;
    }

    // Agrega el nuevo usuario al FormArray
    selectedUserIdsArray.push(new FormControl(user.id));
    this.selectedUsers = this.selectedUsers = [...this.selectedUsers, user];

    // Limpiar input despues de agregar el usuario
    this.createForm.get('searchInput')?.reset('');
    this.searched = [];
  }
}
