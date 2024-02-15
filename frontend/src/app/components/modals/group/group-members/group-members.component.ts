import { Component, Input } from '@angular/core';
import { FormGroup, FormControl, FormsModule, ReactiveFormsModule, FormArray } from '@angular/forms';
import { User } from '../../../../models/user/user.model';
import { UserService } from '../../../../services/user/user.service';

@Component({
  selector: 'app-group-members',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule],
  templateUrl: './group-members.component.html',
  styleUrl: './group-members.component.css'
})
export class GroupMembersComponent {
  constructor(
    private userService: UserService,
  ) { }
  
  @Input() user!: User;

  membersForm = new FormGroup({
    selectedUserIds: new FormArray([]),
    searchInput: new FormControl('')
  })

  // Arreglo de usuarios buscados/encontrados
  searched: any = [];
  selectedUsers: User[] = [];

  // Opcion de usuario en radio checks para mostrar las opciones disponibles
  selectedUserOption: string = 'misContactos';

  setUserOption(option: string) {
    this.selectedUserOption = option;
  }

  // Busqueda de usuarios
  handleSearch() {
    let searchInput = this.membersForm.controls.searchInput.value as string;   
    this.userService.searchUser(this.user.id, searchInput).subscribe({
      next: (res: any) => {
        this.searched = res;
      },
      error: (error) => {
        console.log(error.message)
      }
    })
  }

  handleAddUser(user: User) {
    const selectedUserIdsArray = this.membersForm.get('selectedUserIds') as FormArray;

    if (selectedUserIdsArray.value.includes(user.id)) {
      console.log('El usuario ya está en la lista');
      return;
    }

    // Agrega el nuevo usuario al FormArray
    selectedUserIdsArray.push(new FormControl(user.id));
    this.selectedUsers = this.selectedUsers = [...this.selectedUsers, user];

    // Limpiar input despues de agregar el usuario
    this.membersForm.get('searchInput')?.reset('');
    this.searched = [];
  }

  handleDeleteMember(member: User) {
    const selectedUserIdsArray = this.membersForm.get('selectedUserIds') as FormArray;
    const index = selectedUserIdsArray.value.indexOf(member.id);

    if (index !== -1) {
      selectedUserIdsArray.removeAt(index);
    }
    
    this.selectedUsers = this.selectedUsers.filter(u => u.id !== member.id);
  }


  handleClose() {
    this.membersForm.reset({
      searchInput:''
    })

    const selectedUserIdsArray = this.membersForm.get('selectedUserIds') as FormArray;
    selectedUserIdsArray.clear()

    this.searched = [];
    this.selectedUsers = [];
  }
}
