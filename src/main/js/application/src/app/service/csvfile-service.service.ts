import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Csvfile} from "../model/csvfile";
import {NetDeviceService} from "./net-device.service";
import {SpecDeviceService} from "./spec-device.service";

@Injectable({
  providedIn: 'root'
})
export class CsvfileServiceService {

  private baseUrl: string;

  constructor(private http: HttpClient,
              netDeviceService: NetDeviceService,
              specDeviceService: SpecDeviceService) {
    this.baseUrl = 'http://localhost:8080';
  }

  public findByNameContainsPaged(searchRequest: string, page: number, sortByField: string, sortOrder: string) {
    return this.http.get<Csvfile>(this.baseUrl + '/search', {
      params: {
        search: searchRequest,
        page: page.toString(),
        sortBy: sortByField,
        sortOrder: sortOrder
      }
    });
  }

  public getFileDetails(file: Csvfile) {

    return this.http.post(this.baseUrl + '/details', {
      params: {
        fileId: file.id
      }
    });
  }

}


