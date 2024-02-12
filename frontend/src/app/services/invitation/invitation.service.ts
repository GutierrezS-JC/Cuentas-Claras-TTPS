import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class InvitationService {
  private apiUrl = 'http://localhost:9090/invitations';

  constructor(private http: HttpClient) { }

  cancelInvitation(invitationId: number, userId: number) {
    return this.http.post<boolean>(`${this.apiUrl}/cancel/${invitationId}/${userId}`, null);
  }

}
