package com.psy.csv.entity;

public interface Device {
    String STATE_WORKING = "РАБОЧИЙ";
    String STATE_REPAIRING = "В РЕМОНТЕ";
    String STATE_FOR_RECYCLING = "НА УТИЛИЗАЦИИ";

//    void setId(Long id);
//    Long getId();

    CSVFile getFile();

    void setFile(CSVFile f);
}
