import {Component, OnInit} from '@angular/core';
import {NetDeviceService} from "../../service/net-device.service";
import {NetDevice} from "../../model/net-device";
import {ActivatedRoute} from "@angular/router";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {DeviceValidationServiceService} from "../../service/device-validation-service.service";

@Component({
  selector: 'app-net-device-list',
  templateUrl: './net-device-list.component.html',
  styleUrls: ['./net-device-list.component.css']
})
export class NetDeviceListComponent implements OnInit {


  editForm: FormGroup;

  // devicesFormArray: FormArray = new FormArray([]);

  deviceList: NetDevice[];
  fileId: string;
  editableDevice: NetDevice;


  currentPage: number = 0;
  totalElements: number = 0;
  totalPages: number = 0;
  pageElementLimit: number = 0;
  sortByField: string = '';
  isAscending: boolean = true;

  constructor(private netDeviceService: NetDeviceService,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private validationService: DeviceValidationServiceService) {


  }


  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.fileId = params['id'];
      this.getDevicesData();
    });


  }

  getDevicesData() {
    this.netDeviceService.getDevicesList(this.fileId, 0, this.sortByField, 'asc').subscribe(
      data => {
        this.deviceList = data['content'];
        this.totalElements = data['totalElements'];
        this.totalPages = data['totalPages'];
        this.pageElementLimit = data['size'];
      },
      error => {
        console.log(error.error.message);
      }
    );
  }


  subscribeToChanges(formGroup: FormGroup) {
    // formGroup.valueChanges.subscribe(value => {
    //   for (let name in formGroup.controls) {
    //     if (formGroup.controls[name].invalid) {
    //       console.log('control ' + name + ' is invalid');
    //     }
    //   }
    //   // console.log(value);
    // });
    formGroup.get('name')
      .valueChanges
      .subscribe(value => {
        if (this.editForm.valid) this.editableDevice.name = value
      });
    formGroup.get('address')
      .valueChanges
      .subscribe(value => {
        if (this.editForm.valid) this.editableDevice.address = value
      });
    formGroup.get('curState')
      .valueChanges
      .subscribe(value => {
        if (this.editForm.valid) this.editableDevice.curState = value
      });
    formGroup.get('ipAddress')
      .valueChanges
      .subscribe(value => {
        if (this.editForm.valid) this.editableDevice.ipAddress = value
      });
    formGroup.get('procLoad')
      .valueChanges
      .subscribe(value => {
        if (this.editForm.valid) this.editableDevice.procLoad = value
      });
    formGroup.get('isOn')
      .valueChanges
      .subscribe(value => {
        if (this.editForm.valid) this.editableDevice.isOn = value
      });
  }

  edit(device: NetDevice) {
    this.editableDevice = device;
    this.editForm = this.formBuilder.group({
      name: this.formBuilder.control(device.name, [Validators.required, Validators.maxLength(20)]),
      address: this.formBuilder.control(device.address),
      curState: this.formBuilder.control(device.curState, [Validators.required, this.validationService.stateValidation]),
      ipAddress: this.formBuilder.control(device.ipAddress, [Validators.required, this.validationService.ipAddressCheck]),
      procLoad: this.formBuilder.control(device.procLoad, [Validators.max(100.00)]),
      isOn: this.formBuilder.control(device.isOn, [this.validationService.booleanValidation]),
    });
    this.subscribeToChanges(this.editForm);
  }

  save(value: any) {
    // console.log('is valid :' + this.editForm.valid)
    this.netDeviceService.updateDevice(this.editableDevice).subscribe(value1 => {
      console.log(value1);
      console.log('saved id : ' + this.editableDevice.id);
      this.cancel();
      this.getDevicesData();
    });

  }

  cancel() {
    this.editForm = null;
    this.editableDevice = null;
  }

  changeValue(id: number, property: string, event: any) {
    console.log('id: ' + id + ' property : ' + property + ' event ' + event);
  }
}
