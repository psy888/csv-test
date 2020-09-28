package com.psy.csv.dto;

public interface ReportProjection {
    String getType();

    Integer getTotalDevicesUploaded();

    Integer getTotalRepairing();

    Integer getTotalWorking();

    Integer getTotalRecycling();
}