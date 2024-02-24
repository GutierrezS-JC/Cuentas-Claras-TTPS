import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GroupsInvitationsListComponent } from './groups-invitations-list.component';

describe('GroupsInvitationsListComponent', () => {
  let component: GroupsInvitationsListComponent;
  let fixture: ComponentFixture<GroupsInvitationsListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GroupsInvitationsListComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(GroupsInvitationsListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
