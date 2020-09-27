import {Component, OnInit} from '@angular/core';
import {NetDeviceService} from "../../service/net-device.service";
import {NetDevice} from "../../model/net-device";
import {ActivatedRoute} from "@angular/router";
import {FormArray, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {DeviceValidationServiceService} from "../../service/device-validation-service.service";

@Component({
  selector: 'app-net-device-list',
  templateUrl: './net-device-list.component.html',
  styleUrls: ['./net-device-list.component.css']
})
export class NetDeviceListComponent implements OnInit {


  deviceFormGroup: FormGroup;

  devicesFormArray: FormArray = new FormArray([]);

  deviceList: NetDevice[];
  deviceId: string;

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
      this.deviceId = params['id'];
      this.getDevicesData();
    });


  }

  getDevicesData() {
    this.netDeviceService.getDevicesList(this.deviceId, 0, this.sortByField, 'asc').subscribe(
      data => {
        this.deviceList = data['content'];
        this.totalElements = data['totalElements'];
        this.totalPages = data['totalPages'];
        this.pageElementLimit = data['size'];
        this.buildControls();
      },
      error => {
        console.log(error.error.message);
      }
    );
  }

  buildControls() {
    this.deviceFormGroup = this.formBuilder.group({
      devices: this.formBuilder.array([])
    });
    this.fillArray(this.deviceList);
    console.log(this.deviceFormGroup);
  }

  fillArray(devList: NetDevice[]) {
    devList.forEach((dev) => this.addDevice(dev));
  }

  createDevice(device: NetDevice): FormGroup {
    return this.formBuilder.group({
      name: this.formBuilder.control(device.name, [Validators.required, Validators.maxLength(20)]),
      address: this.formBuilder.control(device.address),
      curState: this.formBuilder.control(device.curState, [Validators.required, this.validationService.stateValidation]),
      ipAddress: this.formBuilder.control(device.ipAddress, [Validators.required, Validators.pattern('^(\d|[1-9]\d|1\d\d|2([0-4]\d|5[0-5]))\.(\d|[1-9]\d|1\d\d|2([0-4]\d|5[0-5]))\.(\d|[1-9]\d|1\d\d|2([0-4]\d|5[0-5]))\.(\d|[1-9]\d|1\d\d|2([0-4]\d|5[0-5]))$')]),
      procLoad: this.formBuilder.control(device.procLoad, [Validators.max(100.00)]),
      isOn: this.formBuilder.control(device.isOn, [this.validationService.booleanValidation])
    });
  }

  addDevice(device: NetDevice): void {
    this.devicesFormArray = this.deviceFormGroup.get('devices') as FormArray;
    let fomGroup = this.createDevice(device);
    this.subscribeToChanges(fomGroup);
    this.devicesFormArray.push(fomGroup);
  }

  subscribeToChanges(formGroup: FormGroup) {
    formGroup.valueChanges.subscribe(value => {
      for (let name in formGroup.controls) {
        if (formGroup.controls[name].invalid) {
          console.log('control ' + name + ' is invalid');
        }
      }
      console.log(value);
    });
    formGroup.statusChanges.subscribe(value => console.log(value));
  }

  remove(id) {
    console.log('remove id : ' + id);
  }

  save(id) {
    console.log('save id : ' + id);
  }

  changeValue(id: number, property: string, event: any) {
    console.log('id: ' + id + ' property : ' + property + ' event ' + event);
  }
}
