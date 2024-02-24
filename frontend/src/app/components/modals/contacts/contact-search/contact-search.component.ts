import { Component, Input } from '@angular/core';
import { User } from '../../../../models/user/user.model';
import { FormGroup, FormArray, Validators, FormControl, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { UserContactService } from '../../../../services/userContact/user-contact.service';
import { swalAlert } from '../../../../utils/sweet-alert';
import { SharedDataService } from '../../../../services/sharedData/shared-data.service';

@Component({
  selector: 'app-contact-search',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule],
  templateUrl: './contact-search.component.html',
  styleUrl: './contact-search.component.css'
})
export class ContactSearchComponent {

  constructor(
    private userContactService: UserContactService,
    private sharedDataService: SharedDataService
  ) { }

  @Input() user!: User;
  @Input() contacts: any[] = [];

  contactsForm = new FormGroup({
    selectedUserIds: new FormArray([], Validators.required),
    searchInput: new FormControl('')
  })

  searched: any = [];
  selectedUsers: User[] = [];

  // Busqueda de usuarios
  handleSearch() {
    let searchInput = this.contactsForm.controls.searchInput.value?.trim() as string;
    if (searchInput.trim() !== '' && searchInput.length > 0) {
      this.userContactService.searchUserContact(this.user.id, searchInput).subscribe({
        next: (res: any) => {
          this.searched = res;
        },
        error: (error: any) => {
          console.log(error.message)
        }
      })
    }
    else {
      this.searched = [];
      this.contactsForm.get('searchInput')?.reset('');

    }
  }

  handleAddUser(user: User) {
    const selectedUserIdsArray = this.contactsForm.get('selectedUserIds') as FormArray;

    if (selectedUserIdsArray.value.includes(user.id)) {
      swalAlert("info", "No se pudo agregar al usuario", "El usuario ya se encuentra en la lista de seleccionados");
      return;
    }

    if (this.contacts.some((member: User) => member.id === user.id)) {
      swalAlert("info", "No se pudo agregar al usuario", "El usuario seleccionado ya es un contacto");
      return;
    }

    // if (this.contacts.some((invitation: any) => (invitation.receiverUser.id === user.id) && (invitation.status === 'PENDING'))) {
    //   swalAlert("info", "No se pudo agregar al usuario", "El usuario ya tiene una solicitud de amistad pendiente");
    //   return;
    // }

    // Agrega el nuevo usuario al FormArray
    selectedUserIdsArray.push(new FormControl(user.id));
    this.selectedUsers = this.selectedUsers = [...this.selectedUsers, user];

    // Limpiar input despues de agregar el usuario
    this.contactsForm.get('searchInput')?.reset('');
    this.searched = [];
  }

  handleDeleteMember(member: User) {
    const selectedUserIdsArray = this.contactsForm.get('selectedUserIds') as FormArray;
    const index = selectedUserIdsArray.value.indexOf(member.id);

    if (index !== -1) {
      selectedUserIdsArray.removeAt(index);
    }

    this.selectedUsers = this.selectedUsers.filter(u => u.id !== member.id);
  }

  handleSendInvitations() {
    if (this.contactsForm.get('selectedUserIds')?.value.length === 0) {
      swalAlert("error", "No se pudo enviar la invitación", "Debe seleccionar algun usuario para enviar la invitación al grupo");
      return;
    }

    let idList = this.contactsForm.controls.selectedUserIds.value as number[];
    this.userContactService.sendFriendRequests(this.user.id, idList).subscribe({
      next: () => {
        this.sharedDataService.updateInvitationsList(this.user);
        this.handleClose();
        swalAlert("success", "Invitaciones enviadas", "Las invitaciones fueron enviadas correctamente");
      },
      error: (error: any) => {
        console.log(error.message)
      }
    })
  }

  handleClose() {
    this.contactsForm.reset({
      searchInput: ''
    })

    const selectedUserIdsArray = this.contactsForm.get('selectedUserIds') as FormArray;
    selectedUserIdsArray.clear()

    this.searched = [];
    this.selectedUsers = [];
  }
}
