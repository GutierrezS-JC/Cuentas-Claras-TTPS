import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { Router } from '@angular/router';
import { NavbarComponent } from '../navbar/navbar.component';
import { AuthenticationService } from '../../services/authentication/authentication.service';
import { first } from 'rxjs';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, RouterModule, NavbarComponent, ReactiveFormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit {

  loading = false;
  submitted = false;

  loginForm = new FormGroup({
    username: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required]),
  });

  error = '';

  constructor(private authenticationService: AuthenticationService, private router: Router) { }

  ngOnInit(): void {
    // elimino las credenciales del usuario, si es que existen
    this.authenticationService.logout();
  }

  onSubmit() {
    this.submitted = true;

    // Valido que el formulario sea valido antes del submit
    if (this.loginForm.valid) {
      console.log('Es valido')
      this.loading = true;

      const credentials = {
        username: this.loginForm.controls.username.value as string,
        password: this.loginForm.controls.password.value as string,
      };

      this.authenticationService.login(credentials.username, credentials.password).pipe(first()).subscribe({
        next: (result) => {
          console.log('Successful login', result);
          // this.router.navigate(['/home']);
          this.router.navigateByUrl('/home');
          console.log('After navigate');
        },
        error: (error) => {
          console.log(error)
          this.error = "Nombre de usuario o Contraseña incorrecta";
          this.loading = false
          alert(this.error)
        }
      })
    }
  }

}
