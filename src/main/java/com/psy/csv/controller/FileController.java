package com.psy.csv.controller;

import com.psy.csv.repository.FileInfoRepository;
import com.psy.csv.repository.NetDeviceRepository;
import com.psy.csv.repository.SpecDeviceRepository;
import com.psy.csv.service.DTOMapperService;
import com.psy.csv.service.FileUploadService;
import com.psy.csv.service.ParserService;
import lombok.NonNull;
import lombok.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@Value
public class FileController {

    ParserService parserService;
    DTOMapperService mapperService;
    FileInfoRepository fileInfoRepository;
    NetDeviceRepository netDeviceRepository;
    SpecDeviceRepository specDeviceRepository;

    FileUploadService fileUploadService;

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
            //todo return error to front with responseBody
        }


    }

    /**
     * Download generated csv report file
     *
     * @param from - start date of report rang
     * @param to   - end date of report range
     */
    @GetMapping(value = "/report")
    public void getReportFile(@RequestParam(name = "from") String from,
                              @RequestParam(name = "to") String to) {
        //todo return generated file
    }


}
