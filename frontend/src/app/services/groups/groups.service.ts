import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserService } from '../user/user.service';
import { Groups } from '../../models/groups/groups.model';
import { GroupDetails } from '../../models/groups/groupDetails.model';

@Injectable({
  providedIn: 'root'
})
export class GroupsService {
  private apiUrl = 'http://localhost:9090/groups';
  jsonLocalStorage = { 'user': 'mati', 'id': -1 }

  constructor(private http: HttpClient, private userService: UserService) { }

  getAllGroups(): Observable<Groups> {
    const userId = this.userService.getUserId();
    // const user = this.userService.getUser();

    // const user = localStorage.getItem('user');
    // if (localStorage.getItem('user') !== null ) {
    //   this.jsonLocalStorage = localStorage.getItem('user');
    // }
    return this.http.get<any>(`${this.apiUrl}/user/${userId}`);
  }

  getGroup(groupId: number): Observable<GroupDetails> {
    return this.http.get<any>(`${this.apiUrl}/${groupId}`);
  }
}
