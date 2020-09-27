import {ComponentFixture, TestBed} from '@angular/core/testing';

import {SpecDeviceListComponent} from './spec-device-list.component';

describe('SpecDeviceListComponent', () => {
  let component: SpecDeviceListComponent;
  let fixture: ComponentFixture<SpecDeviceListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SpecDeviceListComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SpecDeviceListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
