import {Injectable} from '@angular/core';
import {HttpClient, HttpRequest} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class FileUploadService {

  private uploadFileUrl: string;

  constructor(private http: HttpClient) {
    this.uploadFileUrl = "http://localhost:8080/file/"
  }

  public postFile(file: File, type: string) {

    const formData: FormData = new FormData();

    formData.append('file', file);
    const req = new HttpRequest('POST', `${this.uploadFileUrl + type}`, formData, {
      // headers: new HttpHeaders({
      //   'Content-type': 'application/octet-stream'
      // }),
      reportProgress: true,
      responseType: 'json'
    });

    return this.http.request(req);

  }


}
