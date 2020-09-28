import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute} from "@angular/router";
import {DeviceValidationServiceService} from "../../service/validators/device-validation-service.service";
import {SpecDevice} from "../../model/spec-device";
import {SpecDeviceService} from "../../service/spec-device.service";

@Component({
  selector: 'app-spec-device-list',
  templateUrl: './spec-device-list.component.html',
  styleUrls: ['./spec-device-list.component.css']
})
export class SpecDeviceListComponent implements OnInit {

  editForm: FormGroup;

  deviceList: SpecDevice[];
  fileId: string;
  editableDevice: SpecDevice;


  currentPage: number = 0;
  totalElements: number = 0;
  totalPages: number = 0;
  pageElementLimit: number = 0;
  sortByField: string = '';
  isAscending: boolean = true;

  constructor(private specDeviceService: SpecDeviceService,
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
    this.specDeviceService.getDevicesList(this.fileId, 0, this.sortByField, 'asc').subscribe(
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
    formGroup.get('productionDate')
      .valueChanges
      .subscribe(value => {
        if (this.editForm.valid) this.editableDevice.productionDate = value
      });
  }

  edit(device: SpecDevice) {
    this.editableDevice = device;
    this.editForm = this.formBuilder.group({
      name: this.formBuilder.control(device.name, [Validators.required, Validators.maxLength(20)]),
      address: this.formBuilder.control(device.address),
      curState: this.formBuilder.control(device.curState, [Validators.required, this.validationService.stateValidation]),
      productionDate: this.formBuilder.control(this.getFormattedDate(device.productionDate), [this.validationService.dateValidation]),
    });
    this.subscribeToChanges(this.editForm);
  }

  save(value: any) {
    // console.log('is valid :' + this.editForm.valid)
    this.specDeviceService.updateDevice(this.editableDevice).subscribe(value1 => {

      this.cancel();
      this.getDevicesData();
    });

  }

  cancel() {
    this.editForm = null;
    this.editableDevice = null;
  }

  getFormattedDate(dateMillis: number) {
    let date = new Date(dateMillis);
    return date.getFullYear() + '-' + ('0' + (date.getMonth() + 1)).slice(-2) + '-' + ('0' + date.getDate()).slice(-2);
  }
}
