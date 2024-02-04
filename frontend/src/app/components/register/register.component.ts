import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NavbarComponent } from '../navbar/navbar.component';
import { AuthenticationService } from '../../services/authentication/authentication.service';
import { UserRegister } from '../../models/user/user-register.model';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [FormsModule, NavbarComponent, ReactiveFormsModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent implements OnInit {
  loading = false;
  submitted = false;
  error = '';

  registerForm = new FormGroup({
    name: new FormControl('', [Validators.required]),
    lastName: new FormControl('', [Validators.required]),
    email: new FormControl('', [Validators.required]),
    username: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required]),
    confirmPassword: new FormControl('', [Validators.required]),
  });

  constructor(
    private authenticationService: AuthenticationService,
    private router: Router
  ) { }

  ngOnInit(): void {
    // elimino las credenciales del usuario, si es que existen
    this.authenticationService.logout();
  }

  onSubmit(): void {
    this.submitted = true;

    if (this.registerForm.valid) {
      this.loading = true;
      // Agregar validaciones
      if (true) {
        const user: UserRegister = {
          name: this.registerForm.get('name')?.value as string,
          lastName: this.registerForm.get('lastName')?.value as string,
          email: this.registerForm.get('email')?.value as string,
          username: this.registerForm.get('username')?.value as string,
          password: this.registerForm.get('password')?.value as string,
          confirmPassword: this.registerForm.get('confirmPassword')?.value as string,
        };

        this.authenticationService.register(user).subscribe({
          next: (result: any) => {
            console.log(result);
            alert('Register succeded!')
            this.router.navigate(['/home']);
          },
          error: (error: any) => {
            console.error('Error en el registro', error);
          }
        });
      }
      else {
        console.log('Formulario inválido');
      }
    }
  }
}
