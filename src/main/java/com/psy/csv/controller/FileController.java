package com.psy.csv.controller;

import com.psy.csv.entity.CSVFile;
import lombok.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@Value
public class FileController {


    @PostMapping("/file")
    public void addFile(@RequestBody CSVFile file,
                        @RequestParam String name,
                        @RequestParam(required = false) String divider,
                        @RequestParam(required = false) Boolean withHeaders,
                        @RequestParam String type) {


        //send to parser
    }

}
