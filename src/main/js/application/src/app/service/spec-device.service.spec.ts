import {TestBed} from '@angular/core/testing';

import {SpecDeviceService} from './spec-device.service';

describe('SpecDeviceService', () => {
  let service: SpecDeviceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SpecDeviceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
