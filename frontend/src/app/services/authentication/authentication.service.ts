import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { UserToken } from '../../models/user/user-token.model';
import { UserRegister } from '../../models/user/user-register.model';

@Injectable({ providedIn: 'root' })
export class AuthenticationService {
  private apiUrl = 'http://localhost:9090/auth';

  private currentUserSubject: BehaviorSubject<UserToken>;
  public currentUser: Observable<UserToken>;

  constructor(private http: HttpClient) {
    this.currentUserSubject = new BehaviorSubject<UserToken>(JSON.parse(localStorage.getItem('currentUser')!));
    this.currentUser = this.currentUserSubject.asObservable();
  }

  public get currentUserValue(): UserToken {
    return this.currentUserSubject.value;
  }

  login(username: string, password: string) {
    return this.http.post<any>(`${this.apiUrl}/authenticate`, { username, password })
      .pipe(map(credentials => {
        if (credentials && credentials.token) {
          localStorage.setItem('currentUser', JSON.stringify(credentials));
          this.currentUserSubject.next(credentials);
        }

        return credentials;
      }));
  }

  register(user: UserRegister) {
    return this.http.post<any>(`${this.apiUrl}/register`, user)
      .pipe(map(credentials => {
        if (credentials && credentials.token) {
          console.log(credentials);
          localStorage.setItem('currentUser', JSON.stringify(credentials));
          this.currentUserSubject.next(credentials);
        }

        return credentials;
      }));
  }

  logout() {
    localStorage.removeItem('currentUser');
    this.currentUserSubject.next(null!);
  }
}