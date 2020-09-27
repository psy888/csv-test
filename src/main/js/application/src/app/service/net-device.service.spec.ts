import {TestBed} from '@angular/core/testing';

import {NetDeviceService} from './net-device.service';

describe('NetDeviceService', () => {
  let service: NetDeviceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(NetDeviceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
