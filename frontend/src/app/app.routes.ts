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

export const routes: Routes = [
    { path: 'home', component: HomeComponent },
    { path: 'grupos', component: GroupsComponent},
    { path: 'pagos', component: PaymentsComponent},
    { path: 'contactos', component: ContactsComponent },
    { path: 'gastos', component: SpendingsComponent },
    { path: 'login', component: LoginComponent },
    { path: 'registro', component: RegisterComponent },
    { path: 'index', component: IndexComponent },
    { path: '**', redirectTo: 'index', pathMatch: 'full' }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule { }
