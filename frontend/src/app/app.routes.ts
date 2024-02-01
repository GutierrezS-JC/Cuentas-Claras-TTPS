import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';

import { GroupsComponent } from './components/groups/groups.component';
import { CardsComponent } from './components/cards/cards.component';
import { PaymentsComponent } from './components/payments/payments.component';
import { ContactsComponent } from './components/contacts/contacts.component';
import { SpendingsComponent } from './components/spendings/spendings.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { HomeComponent } from './components/home/home.component';
import { IndexComponent } from './components/index/index.component';
import { AuthGuard } from './guards/auth.guard';

export const routes: Routes = [
    // { path: '', component: IndexComponent }, Index... eventualmente
    { path: 'login', component: LoginComponent },
    { path: 'registro', component: RegisterComponent },
    
    { path: 'home', component: HomeComponent, canActivate: [AuthGuard]},
    { path: 'grupos', component: GroupsComponent, canActivate: [AuthGuard]},
    { path: 'pagos', component: PaymentsComponent, canActivate: [AuthGuard]},
    { path: 'contactos', component: ContactsComponent, canActivate: [AuthGuard]},
    { path: 'gastos', component: SpendingsComponent, canActivate: [AuthGuard]},
    { path: 'index', component: IndexComponent, canActivate: [AuthGuard]},
    
    { path: '**', redirectTo: 'login', pathMatch: 'full' }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule { }
