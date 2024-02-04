import { Component, Input, OnInit } from '@angular/core';
import { GroupCreate } from '../../../../models/groups/gropCreate.model';
import { FormArray, FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { UserService } from '../../../../services/user/user.service';
import { GroupsService } from '../../../../services/groups/groups.service';
import { GroupCategories } from '../../../../models/groupCategories/groupCategories.model';
import { User } from '../../../../models/user/user.model';

@Component({
  selector: 'app-group-create',
  standalone: true,
  imports: [CommonModule, FormsModule, ReactiveFormsModule],
  templateUrl: './group-create.component.html',
  styleUrl: './group-create.component.css'
})
export class GroupCreateComponent {
  constructor(private userService: UserService) { }
  
  @Input() groupCategories: any;
  @Input() user!: User;

  createForm: FormGroup = new FormGroup({
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

  handleSearch() {
    console.log(this.user)
    this.userService.searchUser(this.user.id, this.createForm.get('searchInput')?.value).subscribe({
      next: (res: any) => {
        console.log(res)
        this.searched = res;
      },
      error: (error) => {
        console.log(error.message)
      },
      complete: () => console.info('API call completed')
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
