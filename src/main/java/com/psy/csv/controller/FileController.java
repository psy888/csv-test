package com.psy.csv.controller;

import com.psy.csv.service.FileUploadService;
import com.psy.csv.service.ReportService;
import lombok.NonNull;
import lombok.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@Value
public class FileController {

    FileUploadService fileUploadService;
    ReportService reportService;

    /**
     * Upload file too system
     *
     * @param file - csv file
     *             //     * @param divider - custom divider type
     *             //     * @param withHeaders - csv file with headers in first line
     *             //     * @param devType - type of devices listed in file
     */
    @PostMapping(value = "/file/{type}")
    public void addFile(@NonNull @RequestPart(name = "file") MultipartFile file,
                        @PathVariable(name = "type") String type) {
        try {
            fileUploadService.addNewFile(file, type, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Download generated csv report file
     *
     * @param start - start date of report rang
     * @param end   - end date of report range
     */
    @GetMapping(value = "/report")
    public ResponseEntity<Resource> getReportFile(@RequestParam(name = "start") String start,
                                                  @RequestParam(name = "end") String end) {
        File file = reportService.generateReport(start, end).toFile();// Initialize this to the File path you want to serve.

        try {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"")
                    .contentLength(file.length())
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;

    }


}
