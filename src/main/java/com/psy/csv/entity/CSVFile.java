package com.psy.csv.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "file", schema = "my_schema")
@Data
public class CSVFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "file_name", nullable = false)
    private String fileName;

    @Column(name = "upload_date_time", nullable = false)
    private Date uploadDateTime;

    @Column(name = "device_type", nullable = false)
    private String devType;
}
