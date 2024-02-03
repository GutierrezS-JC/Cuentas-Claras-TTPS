import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SpendingCreateComponent } from './spending-create.component';

describe('SpendingCreateComponent', () => {
  let component: SpendingCreateComponent;
  let fixture: ComponentFixture<SpendingCreateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SpendingCreateComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(SpendingCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
