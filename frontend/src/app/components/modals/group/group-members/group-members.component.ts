import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormGroup, FormControl, FormsModule, ReactiveFormsModule, FormArray, Validators } from '@angular/forms';
import { User } from '../../../../models/user/user.model';
import { UserService } from '../../../../services/user/user.service';
import { UserContactService } from '../../../../services/userContact/user-contact.service';
import { swalAlert } from '../../../../utils/sweet-alert';
import { GroupDetails } from '../../../../models/groups/groupDetails.model';
import { InvitationService } from '../../../../services/invitation/invitation.service';

@Component({
  selector: 'app-group-members',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule],
  templateUrl: './group-members.component.html',
  styleUrl: './group-members.component.css'
})
export class GroupMembersComponent implements OnInit {
  constructor(
    private userService: UserService,
    private userContactService: UserContactService,
    private invitationService: InvitationService
  ) { }

  @Input() user!: User;
  @Input() actualGroup!: GroupDetails;

  @Output() actualGroupChange = new EventEmitter<GroupDetails>();

  membersForm = new FormGroup({
    selectedUserIds: new FormArray([], Validators.required),
    searchInput: new FormControl('')
  })

  // Arreglo de usuarios buscados/encontrados
  searched: any = [];
  selectedUsers: User[] = [];

  // Opcion de usuario en radio checks para mostrar las opciones disponibles
  selectedUserOption: string = 'misContactos';

  // Contactos del usuario logueado
  contacts: User[] = [];

  ngOnInit(): void {
    this.userContactService.getContacts(this.user.id).subscribe({
      next: (response: any) => {
        this.contacts = response;
      },
      error: (error: any) => {
        console.log(error)
      }
    })
  }

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
      swalAlert("info", "No se pudo agregar al usuario", "El usuario ya se encuentra en la lista de seleccionados");
      return;
    }

    if (this.actualGroup.members.some((member: User) => member.id === user.id)) {
      swalAlert("info", "No se pudo agregar al usuario", "El usuario ya se encuentra registrado en el grupo");
      return;
    }

    if (this.actualGroup.invitations.some((invitation: any) => (invitation.receiverUser.id === user.id) && (invitation.status === 'PENDING'))) {
      swalAlert("info", "No se pudo agregar al usuario", "El usuario ya tiene una invitación pendiente para unirse al grupo");
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

  handleSendInvitation() {
    if (this.membersForm.get('selectedUserIds')?.value.length === 0) {
      swalAlert("error", "No se pudo enviar la invitación", "Debe seleccionar algun usuario para enviar la invitación al grupo");
      return;
    }

    let idList = this.membersForm.controls.selectedUserIds.value as number[];
    this.invitationService.sendInvitations(this.actualGroup.groupId, this.user.id, idList).subscribe({
      next: (res: any) => {
        this.actualGroupChange.emit(res);
        this.handleClose();
        swalAlert("success", "Invitaciones enviadas", "Las invitaciones fueron enviadas correctamente");
      },
      error: (error: any) => {
        console.log(error.message)
      }
    })
  }

  handleClose() {
    this.membersForm.reset({
      searchInput: ''
    })

    const selectedUserIdsArray = this.membersForm.get('selectedUserIds') as FormArray;
    selectedUserIdsArray.clear()

    this.searched = [];
    this.selectedUsers = [];
  }
}
