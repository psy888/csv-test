package com.psy.csv.dto;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Builder;
import lombok.Data;

/**
 * DTO Class for Report of uploaded devices for period of time
 * type - device type
 * totalDevicesUploaded - total uploaded devices for period of time
 * totalRepairing - total repairing state devices for period of time
 * totalWorking - total working state devices for period of time
 * totalRecycling - total recycling state devices for period of time
 */
@Data
@Builder
public class ReportDTO implements ReportProjection {

    @CsvBindByPosition(position = 1)
    private String type;
    @CsvBindByPosition(position = 2)
    private Integer totalDevicesUploaded;
    @CsvBindByPosition(position = 3)
    private Integer totalRepairing;
    @CsvBindByPosition(position = 4)
    private Integer totalWorking;
    @CsvBindByPosition(position = 5)
    private Integer totalRecycling;
}
