import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {NetDevice} from "../model/net-device";

@Injectable({
  providedIn: 'root'
})
export class NetDeviceService {
  baseUrl: string;

  constructor(private http: HttpClient) {
    this.baseUrl = 'http://localhost:8080/net-device'
  }

  public getDevicesList(fileId: string, page: number, sortByField: string, sortOrder: string) {
    return this.http.get(this.baseUrl, {
      params: {
        fileId: fileId
      }
    });
  }

  public updateDevice(device: NetDevice) {
    return this.http.post<NetDevice>(this.baseUrl, device);
  }

}
