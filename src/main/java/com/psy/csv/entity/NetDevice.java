package com.psy.csv.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "net_dev", schema = "my_schema")
@Data
public class NetDevice implements Device {
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
    @Column(name = "ip_address", nullable = false, length = 15)
    private String ipAddress;
    @Column(name = "proc_load")
    private Double procLoad;
    @Column(name = "status", nullable = false)
    private Boolean isOn;
}
