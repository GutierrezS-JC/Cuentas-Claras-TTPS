import { Injectable } from '@angular/core';
import { User } from '../../models/user/user.model';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserContactService {
  private apiUrl = 'http://localhost:9090/userContacts';
  
  constructor(private http: HttpClient) { }
  
  private user: User | null = null;
  
  getContacts(userId: number): Observable<User[]> {
    return this.http.get<User[]>(`${this.apiUrl}/user/${userId}`);
  }
  
  getInvitations(userId: number): Observable<User[]> {
    return this.http.get<User[]>(`${this.apiUrl}/user/${userId}/invitations`);
  }
  
  cancelSolicitud(solicitudId: number, id: number) {
    return this.http.delete(`${this.apiUrl}/deleteSolicitud/${solicitudId}/user/${id}`);
  }
 
  acceptSolicitud(solicitudId: number, id: number) {
    return this.http.put(`${this.apiUrl}/acceptSolicitud/${solicitudId}/user/${id}`, null);
  }

  searchUserContact(userId: number, searchString: string): Observable<User[]> {
    return this.http.get<User[]>(`${this.apiUrl}/user/${userId}/search?searchString=${searchString}`);
  }

  sendFriendRequests(senderId: number, userList: number[]): Observable<any> {
    let payload = {
      senderId: senderId,
      receiverListId: userList
    } 
    return this.http.post(`${this.apiUrl}/sendFriendRequest`, payload);
  }

}
