import {Component, OnInit} from '@angular/core';
import {ModalService} from "./modal";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ReportService} from "./service/report.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title: string;
  reportForm: FormGroup;

  startDate: Date;
  endDate: Date;

  constructor(private modalService: ModalService,
              private formBuilder: FormBuilder,
              private reportService: ReportService) {
    this.title = 'Реестр устройств';
  }

  ngOnInit(): void {
    this.initFormGroup();
  }


  openModal(id: string) {
    this.modalService.open(id);
  }

  closeModal(id: string) {
    if (id == 'report-modal') {
      this.destroyFormGroup();
    }
    this.modalService.close(id);
  }

  onSubmit(val: any) {
    this.reportService.getReport(this.startDate, this.endDate);
  }

  private initFormGroup() {
    this.reportForm = this.formBuilder.group({
      startDate: this.formBuilder.control('', [Validators.required]),
      endDate: this.formBuilder.control('', [Validators.required])
    });
    this.subscribeOnChanges()
  }

  private subscribeOnChanges() {
    this.reportForm.get('startDate').valueChanges.subscribe(value => {
      this.startDate = value;
      // console.log('start date: ' + this.startDate)
    });
    this.reportForm.get('endDate').valueChanges.subscribe(value => {
      this.endDate = value;
      // console.log('end date: ' + this.endDate)
    });
  }

  private destroyFormGroup() {
    this.reportForm.reset();
  }


}
