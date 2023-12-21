import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Spending } from '../models/spending.model';

@Injectable({
  providedIn: 'root'
})

export class SpendingService {
  private apiUrl = 'http://localhost:9090'; // qué url va a acá??

  constructor(private http: HttpClient) { }

  // lista todos los gastos grupales e individuales
  // hay que discriminarlos de alguna forma
  getAllSpendings(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/spendings`);
  };

  // crea un nuevo gasto
  createSpending(spending: Spending): Observable<any> {
    const url = `${this.apiUrl}`; // qué url va a acá??
    return this.http.post<any>(url, spending);
  }

  // lista los grupos de un usuario
  getGroups(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/groups`);
  }

  // lista los miembros de un grupo
  // getMembers(): Observable<any[]> {
  //   return this.http.get<any[]>(`${this.apiUrl}/members`);
  // }

  // lista las categorías de gastos
  getSpendingCategories(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/spendingCategories`);
  }

  // lista los contactos de un usuario
  // getContacts(): Observable<any[]> {
  //   return this.http.get<any[]>(`${this.apiUrl}/contacts`);
  // }

}
