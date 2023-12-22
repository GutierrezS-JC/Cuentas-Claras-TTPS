import { Injectable } from '@angular/core';

interface User {
  email: string;
  id: string;
  lastName: string;
  name: string;
  profilepicBase64: string;
  username: string;
}

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor() { }

  private user: User | null = null;

  getUser(): User | null {
    // return this.user;
    const userLogged =  localStorage.getItem('user') || "";
    return JSON.parse(userLogged);
  }

  setUser(user: User): void {
    this.user = user;
    localStorage.setItem('user', JSON.stringify(user));
  }

  isUserSet(): boolean {
    return !!this.user;
  }

  // getUserId(): string | null {
    getUserId(): string | null{
    // return this.user?.id || null;
    return this.getUser()?.id || null;
  }
}
