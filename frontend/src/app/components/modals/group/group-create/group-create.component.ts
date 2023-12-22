import { Component, Input, OnInit } from '@angular/core';
import { GroupCreate } from '../../../../models/groups/gropCreate.model';
import { FormArray, FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { UserService } from '../../../../services/user/user.service';
import { GroupsService } from '../../../../services/groups/groups.service';
import { GroupCategories } from '../../../../models/groupCategories/groupCategories.model';

@Component({
  selector: 'app-group-create',
  standalone: true,
  imports: [CommonModule, FormsModule, ReactiveFormsModule],
  templateUrl: './group-create.component.html',
  styleUrl: './group-create.component.css'
})
export class GroupCreateComponent {
  constructor(private userService: UserService) { }

  createForm: FormGroup = new FormGroup({
    name: new FormControl(''),
    description: new FormControl(''),
    categoryId: new FormControl(-1, Validators.min(1)),
    amount: new FormControl(),
    selectedUserIds: new FormArray([]),
    searchInput: new FormControl('')
  })

  // Arreglo de usuarios buscados
  searched: any = [];
  @Input() groupCategories: any;

  handleSearch() {
    this.userService.searchUser(this.createForm.get('searchInput')?.value).subscribe({
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
    this.searched = [];
    // this.createForm.reset();
    const selectedUserIdsArray = this.createForm.get('selectedUserIds') as FormArray;
    selectedUserIdsArray.clear()
  }

  handleAddUser(userId: number) {
    const selectedUserIdsArray = this.createForm.get('selectedUserIds') as FormArray;
    
    if (selectedUserIdsArray.value.includes(userId)) {
      console.log('El usuario ya está en la lista');
      return;
    }

    // Agrega el nuevo usuario al FormArray
    selectedUserIdsArray.push(new FormControl(userId));

    // Limpiar input despues de agregar el usuario
    this.createForm.get('searchInput')?.reset();
    this.searched = [];
  }
}
