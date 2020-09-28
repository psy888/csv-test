package com.psy.csv.repository;

import com.psy.csv.entity.CSVFile;
import com.psy.csv.entity.SpecDevice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecDevicePagedSortedRepository extends PagingAndSortingRepository<SpecDevice, Long>, CrudRepository<SpecDevice, Long> {

    //find all devices by uploaded file
    Page<SpecDevice> findAllByFileId(Long id, Pageable pageable);

    //find all devices by uploaded file
    Page<SpecDevice> findAllByFile(CSVFile file, Pageable pageable);


}
