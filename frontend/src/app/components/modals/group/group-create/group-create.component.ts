import { Component, Input, OnInit } from '@angular/core';
import { GroupCreate } from '../../../../models/groups/gropCreate.model';
import { FormArray, FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { UserService } from '../../../../services/user/user.service';
import { GroupsService } from '../../../../services/groups/groups.service';
import { User } from '../../../../models/user/user.model';
import { swalAlert } from '../../../../utils/sweet-alert';
import { SharedDataService } from '../../../../services/sharedData/shared-data.service';

@Component({
  selector: 'app-group-create',
  standalone: true,
  imports: [CommonModule, FormsModule, ReactiveFormsModule],
  templateUrl: './group-create.component.html',
  styleUrl: './group-create.component.css'
})
export class GroupCreateComponent implements OnInit {
  constructor(
    private userService: UserService,
    private groupsService: GroupsService,
    private sharedDataService: SharedDataService
  ) { }

  @Input() groupCategories: any;
  @Input() user!: User;

  loading = false;
  submitted = false;

  createForm = new FormGroup({
    name: new FormControl('', [
      Validators.required,
      Validators.minLength(4),
      Validators.maxLength(50)
    ]),
    description: new FormControl('', [
      Validators.required,
      Validators.maxLength(200)
    ]),
    categoryId: new FormControl(-1, [
      Validators.required,
      Validators.min(1),
    ]),
    amount: new FormControl(0, [
      Validators.required,
      Validators.min(1),
      Validators.pattern(/^[1-9]\d*$/)
    ]),
    selectedUserIds: new FormArray([], [
      Validators.required,
      Validators.minLength(1)
    ]),
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

      this.groupsService.createGroup(group).subscribe({
        next: () => {
          this.sharedDataService.updateGroupList(this.user);
          swalAlert('success', 'Grupo creado', 'El grupo se ha creado correctamente')
        },
        error: (error) => {
          console.log(error)
        },
        complete: () => {
          this.loading = false;
          this.submitted = false;
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
      swalAlert("info", "No se pudo agregar al usuario", "El usuario ya se encuentra en la lista de seleccionados");
      return;
    }

    // Agrega el nuevo usuario al FormArray
    selectedUserIdsArray.push(new FormControl(user.id));
    this.selectedUsers = this.selectedUsers = [...this.selectedUsers, user];

    // Limpiar input despues de agregar el usuario
    this.createForm.get('searchInput')?.reset('');
    this.searched = [];
  }

  handleDeleteMember(member: User) {
    const selectedUserIdsArray = this.createForm.get('selectedUserIds') as FormArray;
    const index = selectedUserIdsArray.value.indexOf(member.id);

    if (index !== -1) {
      selectedUserIdsArray.removeAt(index);
    }

    this.selectedUsers = this.selectedUsers.filter(u => u.id !== member.id);
  }

}
