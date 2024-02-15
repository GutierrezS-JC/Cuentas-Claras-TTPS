import { Component } from '@angular/core';
import { FormGroup, FormControl, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { User } from '../../../../models/user/user.model';

@Component({
  selector: 'app-group-members',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule],
  templateUrl: './group-members.component.html',
  styleUrl: './group-members.component.css'
})
export class GroupMembersComponent {

  membersForm = new FormGroup({
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
}
