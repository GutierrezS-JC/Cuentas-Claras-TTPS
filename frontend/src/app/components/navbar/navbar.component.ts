import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [ RouterModule ],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {
  parsearVariable: string= 'hello';

  isLogged(){
    if(localStorage.getItem('user') != null){
      this.parsearVariable= localStorage.getItem('user') || "";
      return JSON.parse(this.parsearVariable);
    }
    return false;
  }

  logOut(){
    localStorage.clear();
  }
}
