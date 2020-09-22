package com.psy.csv.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "spec_dev", schema = "my_schema")
@Data
public class SpecDevice implements Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false, length = 20)
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name = "state", nullable = false)
    private String curState;
    @ManyToOne(targetEntity = CSVFile.class, fetch = FetchType.LAZY)
    private CSVFile file;
    @Column(name = "prod_date", nullable = false)
    private LocalDateTime localDateTime;


}