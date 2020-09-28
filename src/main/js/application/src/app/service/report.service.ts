import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import * as fileSaver from 'file-saver';

@Injectable({
  providedIn: 'root'
})
export class ReportService {

  baseUrl: string;

  constructor(private http: HttpClient) {
    this.baseUrl = 'http://localhost:8080/report'
  }

  /**
   * get report file from server
   * @param start - start report date
   * @param end - end report date
   */
  public getReport(start: Date, end: Date) {
    return this.http.get(this.baseUrl, {
      params: {
        start: start.toString(),
        end: end.toString()
      },
      responseType: 'blob'
    }).subscribe(response => this.downLoadFile(response, "application/ms-excel"));
  }

  /**
   * Method is use to download file.
   * @param data - Array Buffer data
   * @param type - type of the document.
   */
  downLoadFile(data: any, type: string) {
    console.log(type);
    let blob = new Blob([data], {type: type});
    let url = window.URL.createObjectURL(blob);
    fileSaver.saveAs(blob, 'report.csv')
  }
}
