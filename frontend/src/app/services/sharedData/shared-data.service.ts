import { Injectable } from '@angular/core';
import { Groups } from '../../models/groups/groups.model';
import { BehaviorSubject, Observable } from 'rxjs';
import { User } from '../../models/user/user.model';
import { GroupsService } from '../groups/groups.service';
import { UserContactService } from '../userContact/user-contact.service';

@Injectable({
  providedIn: 'root'
})
export class SharedDataService {

  constructor(
    private groupsService: GroupsService,
    private userContactService: UserContactService
  ) { }
  
  // Grupos
  private groupsSubject = new BehaviorSubject<Groups>({ listGroups: [], listOwnedGroups: [] });
  groups$: Observable<Groups> = this.groupsSubject.asObservable();

  // Contactos
  private contactsSubject = new BehaviorSubject<User[]>([]);
  contacts$: Observable<User[]> = this.contactsSubject.asObservable();

  // Invitaciones
  private invitationsSubject = new BehaviorSubject<User[]>([]);
  invitations$: Observable<User[]> = this.invitationsSubject.asObservable();

  // Grupos
  updateGroupList(user: User): void {
    this.groupsService.getAllGroups(user.id).subscribe({
      next: (res: Groups) => {
        this.groupsSubject.next(res);
      },
      error: (error: any) => {
        console.log(error.message);
      }
    });
  }

  // Contactos
  updateContactsList(user: User): void {
    this.userContactService.getContacts(user.id).subscribe({
      next: (res: any[]) => {
        this.contactsSubject.next(res);
      },
      error: (error: any) => {
        console.log(error.message);
      }
    });
  }

  // Invitaciones
  updateInvitationsList(user: User): void {
    this.userContactService.getInvitations(user.id).subscribe({
      next: (res: any[]) => {
        this.invitationsSubject.next(res);
      },
      error: (error: any) => {
        console.log(error.message);
      }
    });
  }
}
