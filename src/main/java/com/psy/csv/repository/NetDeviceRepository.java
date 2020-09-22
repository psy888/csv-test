package com.psy.csv.repository;

import com.psy.csv.entity.NetDevice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NetDeviceRepository extends CrudRepository<NetDevice,Long> {

}
