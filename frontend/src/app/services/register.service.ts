import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UserRegister } from '../models/register.model';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  private apiUrl = 'http://localhost:9090/users'; 

  constructor(private http: HttpClient) { }

  registerUser(user: UserRegister): Observable<any> {
    const headers = new HttpHeaders()
      .set('email', user.email)
      .set('username', user.username)
      .set('name', user.name)
      .set('lastName', user.lastName)
      .set('profilepicBase64', user.profilepicBase64)
      .set('password', user.password);

    return this.http.post<any>(`${this.apiUrl}`, user);

  }

}