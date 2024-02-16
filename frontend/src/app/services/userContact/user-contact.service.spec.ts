import { TestBed } from '@angular/core/testing';

import { UserContactService } from './user-contact.service';

describe('UserContactService', () => {
  let service: UserContactService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UserContactService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
