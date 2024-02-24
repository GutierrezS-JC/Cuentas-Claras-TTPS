import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UserLogin } from '../models/login.model';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private apiUrl = 'http://localhost:9090/users/login'; 

  constructor(private http: HttpClient) { }

  login(user: UserLogin): Observable<any> {
    const headers = new HttpHeaders()
      .set('username', user.username)
      .set('password', user.password);

    return this.http.post<any>(`${this.apiUrl}`, user);

  }

}