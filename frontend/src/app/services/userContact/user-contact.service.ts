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

}
