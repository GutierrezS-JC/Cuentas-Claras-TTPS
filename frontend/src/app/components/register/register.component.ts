import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { UserRegister } from '../../models/register.model';
import { RegisterService } from '../../services/register.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  user= new UserRegister('','','','','','Imagen perfil basica',-1) //IMPORTANTE! Note que si el string en base 64 es muy largo, el codigo de petición es 500! Y en el spring tool dice: 'Data truncation: Data too long for column 'profilepic_base64' at row 1'
  confirmPassword= '';
  router= new Router();

  constructor(private RegisterService: RegisterService) { }

  register(): void{
    if(this.confirmPassword != this.user.password){
      alert('Las contraseñas deben ser iguales');
    }
    else{
      if(this.user.password.length < 8){
        alert('La contraseña es insegura: posee menos de 8 carácteres'); //add more validators
      }else{
        this.RegisterService.registerUser(this.user).subscribe(
          (response) => {
            console.log('Registro exitoso');
            /*
            console.log(response.data)
            this.user.password= '';
            this.user.id= response.id;
            localStorage.setItem('user', JSON.stringify(this.user))
            */
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
