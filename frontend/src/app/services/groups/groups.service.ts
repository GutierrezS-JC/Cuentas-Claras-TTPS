import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserService } from '../user/user.service';
import { Groups } from '../../models/groups/groups.model';
import { GroupDetails } from '../../models/groups/groupDetails.model';
import { GroupCategories } from '../../models/groupCategories/groupCategories.model';
import { GroupCreate } from '../../models/groups/gropCreate.model';
import { GroupEdit } from '../../models/groups/groupEdit.interface';

@Injectable({
  providedIn: 'root'
})
export class GroupsService {
  private apiUrl = 'http://localhost:9090/groups';
  private apiCategoriesUrl = 'http://localhost:9090/groupCategories';
  private apiSpendingsUrl = 'http://localhost:9090/spendings';

  constructor(private http: HttpClient) { }

  getAllGroups(userId: number): Observable<Groups> {
    return this.http.get<Groups>(`${this.apiUrl}/user/${userId}`);
  }

  getGroup(groupId: number): Observable<GroupDetails> {
    return this.http.get<any>(`${this.apiUrl}/${groupId}`);
  }

  getGroupCategories(): Observable<GroupCategories[]> {
    return this.http.get<GroupCategories[]>(`${this.apiCategoriesUrl}`);
  }

  getGroupSpendings(groupId: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiSpendingsUrl}/getGroupSpendings?groupId=${groupId}`);
  }

  createGroup(group: GroupCreate): Observable<any> {
    return this.http.post<GroupCreate>(`${this.apiUrl}`, group);
  }

  updateGroup(group: GroupEdit, groupId: number, userId: number): Observable<GroupDetails> {
    return this.http.put<GroupDetails>(`${this.apiUrl}/${groupId}/owner/${userId}`, group);
  }
}
