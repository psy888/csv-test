import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {SpecDevice} from "../model/spec-device";

@Injectable({
  providedIn: 'root'
})
export class SpecDeviceService {
  baseUrl: string;

  constructor(private http: HttpClient) {
    this.baseUrl = 'http://localhost:8080/spec-device'
  }

  public getDevicesList(fileId: string, page: number, sortByField: string, sortOrder: string) {
    return this.http.get(this.baseUrl, {
      params: {
        fileId: fileId
      }
    });
  }

  public updateDevice(device: SpecDevice) {
    return this.http.post<SpecDevice>(this.baseUrl, device);
  }
}
