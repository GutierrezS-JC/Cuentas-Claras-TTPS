import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../../models/user/user.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiUrl = 'http://localhost:9090/users';

  constructor(private http: HttpClient) { }

  private user: User | null = null;

  isUserSet(): boolean {
    return !!this.user;
  }

  searchUser(userId: number, username: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/searchUser/${userId}?username=${username}`);
  }

  getUserDetails(username: string): Observable<User> {
    return this.http.get<User>(`${this.apiUrl}/getUserDetails?username=${username}`);
  }
  
  getUserDetailsById(userId: number): Observable<User> {
    return this.http.get<User>(`${this.apiUrl}/getUserDetails/${+userId}`);
  }
}
