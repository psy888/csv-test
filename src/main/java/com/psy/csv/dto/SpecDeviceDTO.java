package com.psy.csv.dto;

import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;
import com.opencsv.bean.CsvIgnore;
import com.psy.csv.entity.CSVFile;
import lombok.Data;

import java.util.Date;

@Data
public class SpecDeviceDTO implements CsvBean {
    @CsvIgnore
    private Long id;
    @CsvBindByPosition(required = true, position = 0)
    private String name;
    @CsvBindByPosition(position = 2)
    private String address;
    @CsvBindByPosition(position = 3, required = true)
    private String curState;
    @CsvBindByPosition(position = 1, required = true)
    @CsvDate(value = "yyyy-MM-dd")
    private Date productionDate; //todo fix issue with dozer
    @CsvIgnore
    private CSVFile file;
}
