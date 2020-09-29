import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Csvfile} from "../model/csvfile";

@Injectable({
  providedIn: 'root'
})
export class CsvfileServiceService {

  private baseUrl: string;

  constructor(private http: HttpClient) {
    this.baseUrl = 'http://localhost:8080/search';
  }

  public findByNameContainsPaged(searchRequest: string, page: number, sortByField: string, sortOrder: string) {
    return this.http.get<Csvfile>(this.baseUrl, {
      params: {
        search: searchRequest,
        page: page.toString(),
        sortBy: sortByField,
        sortOrder: sortOrder
      }
    });
  }


}


