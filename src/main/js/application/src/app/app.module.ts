import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {ModalModule} from './modal';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {FileListComponent} from './components/file-list/file-list.component';
import {FileUploadFormComponent} from './components/file-upload-form/file-upload-form.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {CsvfileServiceService} from "./service/csvfile-service.service";
import {FileUploadService} from "./service/file-upload.service";
import {HttpClientModule} from "@angular/common/http";
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {NetDeviceListComponent} from './components/net-device-list/net-device-list.component';
import {SpecDeviceListComponent} from './components/spec-device-list/spec-device-list.component';

@NgModule({
  declarations: [
    AppComponent,
    FileListComponent,
    FileUploadFormComponent,
    NetDeviceListComponent,
    SpecDeviceListComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ModalModule,
    ReactiveFormsModule,
    BrowserAnimationsModule
  ],
  providers: [CsvfileServiceService, FileUploadService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
