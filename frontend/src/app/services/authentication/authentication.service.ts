import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { User } from '../../models/user/user.model';

@Injectable({ providedIn: 'root' })
export class AuthenticationService {
  private apiUrl = 'http://localhost:9090/auth';

  private currentUserSubject: BehaviorSubject<User>;
  public currentUser: Observable<User>;

  constructor(private http: HttpClient) {
    this.currentUserSubject = new BehaviorSubject<User>(JSON.parse(localStorage.getItem('currentUser')!));
    this.currentUser = this.currentUserSubject.asObservable();
  }

  public get currentUserValue(): User {
    console.log(this.currentUser);
    console.log(this.currentUserSubject.value);
    return this.currentUserSubject.value;
  }

  login(username: string, password: string) {
    return this.http.post<any>(`${this.apiUrl}/authenticate`, { username, password })
      .pipe(map(credentials => {
        // login successful si hay un token en la respuesta
        if (credentials && credentials.token) {
          // store user details and jwt token in local storage to keep user logged in between page refreshes
          localStorage.setItem('currentUser', JSON.stringify(credentials));
          this.currentUserSubject.next(credentials);
        }

        return credentials;
      }));
  }

  logout() {
    // elimino las credenciales del localstorage al deslogearme
    localStorage.removeItem('currentUser');
    this.currentUserSubject.next(null!);
  }
}