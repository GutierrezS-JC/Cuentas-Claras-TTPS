import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ContactsInvitationsListComponent } from './contacts-invitations-list.component';

describe('ContactsInvitationsListComponent', () => {
  let component: ContactsInvitationsListComponent;
  let fixture: ComponentFixture<ContactsInvitationsListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ContactsInvitationsListComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ContactsInvitationsListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
