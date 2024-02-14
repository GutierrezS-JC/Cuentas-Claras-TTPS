import { Injectable } from '@angular/core';
import { Groups } from '../../models/groups/groups.model';
import { BehaviorSubject, Observable } from 'rxjs';
import { User } from '../../models/user/user.model';
import { GroupsService } from '../groups/groups.service';

@Injectable({
  providedIn: 'root'
})
export class SharedDataService {

  constructor(
    private groupsService: GroupsService
  ) { }
  
  private groupsSubject = new BehaviorSubject<Groups>({ listGroups: [], listOwnedGroups: [] });
  groups$: Observable<Groups> = this.groupsSubject.asObservable();

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
}
