import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { UserRegister } from '../../models/register.model';
import { RegisterService } from '../../services/register.service';
import { Router } from '@angular/router';
import { NavbarComponent } from '../navbar/navbar.component';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [FormsModule, NavbarComponent],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  user = new UserRegister('', '', '', '', '', 'Imagen perfil basica', -1)
  confirmPassword = '';
  router = new Router();

  constructor(private RegisterService: RegisterService) { }

  register(): void {
    if (this.confirmPassword != this.user.password) {
      alert('Las contraseñas deben ser iguales');
    }
    else {
      if (this.user.password.length < 8) {
        alert('La contraseña es insegura: posee menos de 8 carácteres'); //add more validators
      } else {
        this.RegisterService.registerUser(this.user).subscribe(
          (response) => {
            console.log('Registro exitoso');
            alert('Register succeded!')
            this.router.navigate(['/login']);
          },
          (error) => {
            // Manejar el error de autenticación
            console.error('Error en el registro', error);
          }
        );
      }
    }
  }
}
