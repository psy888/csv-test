package com.psy.csv.controller;

import com.psy.csv.entity.CSVFile;
import com.psy.csv.repository.CSVFileRepository;
import lombok.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Value
public class FileInfoController {

    CSVFileRepository fileRepository;

    @GetMapping("/file")
    public List<CSVFile> getFileList() {
        return fileRepository.findAll();
    }

}
