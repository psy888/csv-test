package com.psy.csv.controller;

import com.psy.csv.entity.CSVFile;
import com.psy.csv.service.FileInfoService;
import lombok.Value;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@Value
public class FileInfoController {

    public static final int ROWS_PER_PAGE = 10;
    FileInfoService fileInfoService;


    @GetMapping("/list")
    public Page<CSVFile> getPagedSortedList(
            @RequestParam(name = "page", required = false, defaultValue = "1") Integer pageNumber,
            @RequestParam(name = "sortBy", required = false, defaultValue = "file_name") String sortBy,
            @RequestParam(name = "sortOrder", required = false, defaultValue = "asc") String sortOrder) {
        return fileInfoService.getFilesAllList(pageNumber, ROWS_PER_PAGE, sortBy, sortOrder);
    }

    @GetMapping("/search")
    public Page<CSVFile> searchFilesByNameSorted(
            @RequestParam(name = "search") String filename,
            @RequestParam(name = "page", required = false, defaultValue = "1") Integer pageNumber,
            @RequestParam(name = "sortBy", required = false, defaultValue = "fileName") String sortBy,
            @RequestParam(name = "sortOrder", required = false, defaultValue = "asc") String sortOrder) {
        return fileInfoService.searchFileByName(filename, pageNumber, ROWS_PER_PAGE, sortBy, sortOrder);
    }


}
