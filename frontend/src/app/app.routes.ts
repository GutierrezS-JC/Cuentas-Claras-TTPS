import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';

import { GroupsComponent } from './components/groups/groups.component';
import { CardsComponent } from './components/cards/cards.component';
import { PaymentsComponent } from './components/payments/payments.component';
import { ContactsComponent } from './components/contacts/contacts.component';
import { SpendingsComponent } from './components/spendings/spendings.component';

export const routes: Routes = [
    { path: 'home', component: CardsComponent },
    { path: 'groups', component: GroupsComponent},
    { path: 'payments', component: PaymentsComponent},
    { path: 'contacts', component: ContactsComponent },
    { path: 'spendings', component: SpendingsComponent },
    { path: '**', redirectTo: 'home', pathMatch: 'full' }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule { }
