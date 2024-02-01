import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserService } from '../user/user.service';
import { Groups } from '../../models/groups/groups.model';
import { GroupDetails } from '../../models/groups/groupDetails.model';
import { GroupCategories } from '../../models/groupCategories/groupCategories.model';

@Injectable({
  providedIn: 'root'
})
export class GroupsService {
  private apiUrl = 'http://localhost:9090/groups';
  private apiCategoriesUrl = 'http://localhost:9090/groupCategories';
  private apiSpendingsUrl = 'http://localhost:9090/spendings';

  private getAuthorizationHeader(): HttpHeaders {
    const token = localStorage.getItem('currentUser');
    console.log(token);
    return new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
  }
  constructor(private http: HttpClient, private userService: UserService) { }

  getAllGroups(): Observable<Groups> {
    const userId = this.userService.getUserId();
    return this.http.get<any>(`${this.apiUrl}/user/${userId}`);
  }

  getGroup(groupId: number): Observable<GroupDetails> {
    return this.http.get<any>(`${this.apiUrl}/${groupId}`);
  }

  getGroupCategories(): Observable<GroupCategories[]> {
    const headers = this.getAuthorizationHeader();
    return this.http.get<GroupCategories[]>(`${this.apiCategoriesUrl}`, { headers });
  }

  getGroupCategoriesNoBs(): Observable<GroupCategories[]> {
    return this.http.get<GroupCategories[]>(`${this.apiCategoriesUrl}`);
  }

  getGroupSpendings(groupId: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiSpendingsUrl}/getGroupSpendings?groupId=${groupId}`);
  }

}
