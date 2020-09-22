package com.psy.csv.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "device_type", schema = "my_schema")
@Data
public class DeviceType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "type_name", nullable = false, length = 20)
    private String typeName;
}
