import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../services/auth.service';
import { UserLogin } from '../../models/login.model';
import { RouterModule } from '@angular/router';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, RouterModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  user= new UserLogin('','');
  router= new Router();

  constructor(private authService: AuthService) { }

  onSubmit(): void {
    this.authService.login(this.user).subscribe(
      (response) => {
        console.log('Login exitoso', response);
        localStorage.setItem('user', JSON.stringify(response))
        alert('Login succeded!')
        this.router.navigate(['/home']);
      },
      (error) => {
        // Manejar el error de autenticación
        console.error('Error en el login', error);
      }
    );
  }
}
