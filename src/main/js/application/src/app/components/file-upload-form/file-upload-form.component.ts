import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {FileUploadService} from "../../service/file-upload.service";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-file-upload-form',
  templateUrl: './file-upload-form.component.html',
  styleUrls: ['./file-upload-form.component.css']
})
export class FileUploadFormComponent implements OnInit {

  uploadForm: FormGroup;

  fileToUpload: File = null;

  deviceTypes: string[];
  selectedType: string;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private formBuilder: FormBuilder,
              private fileUploadService: FileUploadService) {

  }

  onSubmit(value: any) {
    this.fileUploadService.postFile(this.fileToUpload, this.selectedType).subscribe(result => {
      // this.fileUploadService.postFile(this.fileToUpload,this.customDivider,String(this.withHeaders),this.selectedDeviceType).subscribe(result => {


      // console.warn('sent data', value);
      // console.warn('received result', result);
      // do something, if upload success
      this.uploadForm.reset();
      // this.gotoFilesList()

    }, error => {
      //todo handle errors and sho message to user
      console.log(error);
    });
  }

  handleFileInput(files: FileList) {
    this.fileToUpload = files.item(0);
  }

  ngOnInit(): void {
    this.deviceTypes = ['network', 'special'];
    this.uploadForm = new FormGroup({
      selectedType: new FormControl(this.deviceTypes[0], [Validators.required]),
      file: new FormControl(null, [Validators.required])
    });
    this.uploadForm.valueChanges.subscribe(value => console.log(value));
    this.uploadForm.get('selectedType').valueChanges.subscribe(value => {
      this.selectedType = value;
      console.log(value)
    });
    this.uploadForm.get('file').valueChanges.subscribe(value => {
      this.fileToUpload = value;
      console.log(value);
    });
  }

}
