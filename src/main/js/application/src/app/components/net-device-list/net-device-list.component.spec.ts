import {ComponentFixture, TestBed} from '@angular/core/testing';

import {NetDeviceListComponent} from './net-device-list.component';

describe('NetDeviceListComponent', () => {
  let component: NetDeviceListComponent;
  let fixture: ComponentFixture<NetDeviceListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [NetDeviceListComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NetDeviceListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
