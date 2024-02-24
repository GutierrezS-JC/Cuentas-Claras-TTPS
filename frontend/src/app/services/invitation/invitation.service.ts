import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { GroupDetails } from '../../models/groups/groupDetails.model';

@Injectable({
  providedIn: 'root'
})
export class InvitationService {
  private apiUrl = 'http://localhost:9090/invitations';

  constructor(private http: HttpClient) { }

  cancelInvitation(invitationId: number, userId: number) {
    return this.http.post<boolean>(`${this.apiUrl}/cancel/${invitationId}/${userId}`, null);
  }

  sendInvitations(groupId: number, senderId: number, userList: number[]){
    let payload = {
      groupId: groupId,
      senderId: senderId,
      receiverListId: userList
    }
    return this.http.post<GroupDetails>(`${this.apiUrl}/send`, payload);
  }

}
