import { Routes } from '@angular/router';
import { IndexComponent } from './components/index/index.component';
import { HomeComponent } from './components/home/home.component';
import { GroupsComponent } from './components/groups/groups.component';
import { SpendingsComponent } from './components/spendings/spendings.component';

export const routes: Routes = [
    { path: '', title: 'Cuentas Claras', component: IndexComponent },
    { path: 'inicio', title: 'Inicio', component: HomeComponent },
    { path: 'grupos', title: 'Mis grupos', component: GroupsComponent },
    { path: 'gastos', title:'Mis gastos', component: SpendingsComponent },
    { path: '**', redirectTo: 'inicio', pathMatch: 'full' }
];
