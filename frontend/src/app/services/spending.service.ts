import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Spending } from '../models/spending.model';
import { UserService } from './user/user.service';

@Injectable({
  providedIn: 'root'
})

export class SpendingService {
  private apiUrl = 'http://localhost:9090'; // qué url va a acá??

  constructor(private http: HttpClient, private userService: UserService) { }

  // lista todos los gastos grupales e individuales
  // hay que discriminarlos de alguna forma
  getMySpendingsExtended(userId: number): Observable<any[]> {
  return this.http.get<any[]>(`${this.apiUrl}/users/getMySpendingsExtended?id=${userId}`);
  };

  // crea un nuevo gasto
  createSpending(spending: Spending, userId: number): Observable<any> {
    const url = `${this.apiUrl}/spendings`;
    console.log(spending);
    const spendingObject = {
      name: spending.group.name,
      description: spending.description,
      totalAmount: spending.totalAmount,
      endingDate: spending.endingDate,
      proofOfPayment: spending.proofOfPayment,
      recurrence: spending.recurrence,
      division: spending.division,
      ownerId: userId,
      spendingCategoryId: spending.spendingCategory,
      groupId: spending.group.groupId,
      usersWithAmount: spending.users
    };
    console.log(spendingObject);
    return this.http.post<any>(url, spendingObject);
  }

  // lista los grupos de un usuario
  getGroups(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/groups`);
  }

  // lista las categorías de gastos
  getSpendingCategories(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/spendingCategories`);
  }

  // lista los contactos de un usuario
  //getContacts(): Observable<any[]> {
   // return this.http.get<any[]>(`${this.apiUrl}/contacts`);
  //}

  // editar el gasto que se le manda
  editSpending(spending: Spending): Observable<any> {
    const url = `${this.apiUrl}/spendings/${spending.id}`;
    return this.http.put<any>(url, spending);
  }
}
