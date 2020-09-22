package com.psy.csv.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    private LocalDateTime uploadDateTime;

    @ManyToOne(targetEntity = DeviceType.class, fetch = FetchType.EAGER)
    private DeviceType devType;
}
