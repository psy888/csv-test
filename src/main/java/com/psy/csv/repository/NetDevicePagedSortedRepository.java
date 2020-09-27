package com.psy.csv.repository;

import com.psy.csv.entity.CSVFile;
import com.psy.csv.entity.NetDevice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NetDevicePagedSortedRepository extends PagingAndSortingRepository<NetDevice, Long>, CrudRepository<NetDevice, Long> {

    //find all devices by uploaded file
    Page<NetDevice> findAllByFile(CSVFile file, Pageable pageable);


}
