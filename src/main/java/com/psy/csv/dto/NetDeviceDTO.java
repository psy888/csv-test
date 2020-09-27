package com.psy.csv.dto;

import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvIgnore;
import com.opencsv.bean.CsvNumber;
import com.psy.csv.entity.CSVFile;
import lombok.Data;

@Data
public class NetDeviceDTO implements CsvBean {
    @CsvIgnore
    private Long id;
    @CsvBindByPosition(required = true, position = 0)
    private String name;
    @CsvBindByPosition(position = 3)
    private String address;
    @CsvBindByPosition(position = 4, required = true)
    private String curState;
    @CsvBindByPosition(position = 1, required = true)
    private String ipAddress;
    @CsvBindByPosition(position = 2)
    @CsvNumber("###.##")
    private Double procLoad;
    @CsvBindByPosition(position = 5)
    private Boolean isOn;
    @CsvIgnore
    private CSVFile file;
}
