import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {FileUploadFormComponent} from "./components/file-upload-form/file-upload-form.component";
import {FileListComponent} from "./components/file-list/file-list.component";
import {NetDeviceListComponent} from "./components/net-device-list/net-device-list.component";
import {SpecDeviceListComponent} from "./components/spec-device-list/spec-device-list.component";

const routes: Routes = [
  {path: 'files', component: FileListComponent},
  {path: 'netDevices', component: NetDeviceListComponent},
  {path: 'specDevices', component: SpecDeviceListComponent},
  {path: 'addfile', component: FileUploadFormComponent},
  {path: 'file-get-types', component: FileUploadFormComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
