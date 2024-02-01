import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [ RouterModule ],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {
  parsearVariable: string= 'hello';
  isLoginPage: boolean = false;

  constructor(private router: Router) {
    console.log(this.router.url);
    this.isLoginPage = this.router.url.endsWith('/login');
  }
  
  isLogged(){
    if(localStorage.getItem('currentUser') != null){
      // this.parsearVariable= localStorage.getItem('currentUser') || "";
      // return JSON.parse(this.parsearVariable);
      return true
    }
    return false;
  }

  logOut(){
    localStorage.clear();
  }
}
