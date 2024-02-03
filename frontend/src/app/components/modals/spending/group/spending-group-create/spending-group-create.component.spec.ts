import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SpendingGroupCreateComponent } from './spending-group-create.component';

describe('SpendingGroupCreateComponent', () => {
  let component: SpendingGroupCreateComponent;
  let fixture: ComponentFixture<SpendingGroupCreateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SpendingGroupCreateComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(SpendingGroupCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
