import {Component, OnInit} from '@angular/core';
import {Csvfile} from "../../model/csvfile";
import {CsvfileServiceService} from "../../service/csvfile-service.service";
import {FormControl, FormGroup} from "@angular/forms";
import {debounceTime, distinctUntilChanged} from "rxjs/operators";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-file-list',
  templateUrl: './file-list.component.html',
  styleUrls: ['./file-list.component.css']
})
export class FileListComponent implements OnInit {

  searchForm: FormGroup;
  files: Csvfile[];
  searchRequest: string = '';
  currentPage: number = 0;
  totalElements: number = 0;
  totalPages: number;
  totalPagesArr: number[] = [];
  pageElementLimit: number = 0;
  sortByField: string = '';
  isAscending: boolean = true;


  constructor(private csvFileService: CsvfileServiceService,
              private route: ActivatedRoute,
              private router: Router) {
  }


  onThClick(clicked: string) {
    if (this.sortByField == clicked) {
      this.isAscending = !this.isAscending;
    } else {
      this.sortByField = clicked;
    }
    this.search();
  }


  getOrderString(isAscending: boolean) {
    if (isAscending) {
      return 'asc';
    } else {
      return 'desc'
    }
  }

  ngOnInit(): void {

    this.searchForm = new FormGroup({
      searchField: new FormControl()
    });

    this.searchForm.get('searchField')
      .valueChanges
      .pipe(debounceTime(600), distinctUntilChanged()) //timeout for search and check is value changed
      .subscribe(value => {
        this.searchRequest = value;
        this.search();
      });

    this.search();
  }


  search() {
    this.csvFileService.findByNameContainsPaged(this.searchRequest, this.currentPage, this.sortByField, this.getOrderString(this.isAscending)).subscribe(
      data => {
        this.files = data['content'];
        this.totalElements = data['totalElements'];
        this.totalPages = data['totalPages'];
        this.pageElementLimit = data['size'];
        console.log(this.totalElements);
        console.log(this.pageElementLimit);
        console.log('totalPages' + this.totalPages);
        this.fillPageIndexArr(this.totalPages);
      },
      error => {
        console.log(error.error.message);
      }
    );
  }

  // goToDetails(fileId: string) {
  goToDetails(file: Csvfile) {
    // console.log('file id' + file.id);
    if (file.devType == 'network') {
      this.router.navigate(['netDevices'], {queryParams: {id: file.id}});
    }
    if (file.devType == 'special') {
      this.router.navigate(['specDevices'], {queryParams: {id: file.id}});
    }

  }


  public getPage(pageNum: number) {
    this.currentPage = pageNum;
    this.search()
  }

  public getNextPage(): void {
    if (this.currentPage < this.totalPages - 1) {
      this.currentPage++;
      this.search();
    }
  }

  public getPreviousPage(): void {
    if (this.currentPage > 0) {
      this.currentPage--;
      this.search();
    }
  }

  public fillPageIndexArr(totalPages: any) {
    console.log(totalPages);
    // this.fillPageIndexArr([]);
    this.totalPagesArr = new Array(totalPages).fill(0);
  }
}
