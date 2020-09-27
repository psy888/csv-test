import {TestBed} from '@angular/core/testing';

import {DeviceValidationServiceService} from './device-validation-service.service';

describe('DeviceValidationServiceService', () => {
  let service: DeviceValidationServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DeviceValidationServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
