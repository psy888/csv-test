package com.psy.csv.repository;

import com.psy.csv.dto.ReportProjection;
import com.psy.csv.entity.CSVFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * List of all uploaded files with sorting and search
 * .findAll(Sort.by("column_name1").ascending().and(Sort.by("column_name2").descending());
 * <p>
 * Pager and sorting together
 * PageRequest.of(0, 20, Sort.by("column_name1"));
 */
@Repository
public interface FileInfoPagedSortedRepository extends PagingAndSortingRepository<CSVFile, Long>, CrudRepository<CSVFile, Long> {

    Page<CSVFile> findByFileNameContains(String name, Pageable pageable); //find by name with pager

    @Query(value = "Select my_schema.file.device_type AS type, " +
            "count(my_schema.file.device_type) AS totalDevicesUploaded, " +
            "sum(case when my_schema.net_dev.state = 'РАБОЧИЙ' or my_schema.spec_dev.state = 'РАБОЧИЙ' then 1 else 0 end) as totalWorking, " +
            "sum(case when my_schema.net_dev.state = 'В РЕМОНТЕ' or my_schema.spec_dev.state = 'В РЕМОНТЕ' then 1 else 0 end) as totalRepairing, " +
            "sum(case when my_schema.net_dev.state = 'НА УТИЛИЗАЦИИ' or my_schema.spec_dev.state = 'НА УТИЛИЗАЦИИ' then 1 else 0 end) as totalRecycling " +
            "from my_schema.file " +
            "left join my_schema.net_dev " +
            "on my_schema.net_dev.file_id = my_schema.file.id " +
            "left join my_schema.spec_dev " +
            "on my_schema.spec_dev.file_id = my_schema.file.id " +
            "where my_schema.file.upload_date_time > ?1 " +
            "and my_schema.file.upload_date_time < ?2 " +
            "group by my_schema.file.device_type;", nativeQuery = true)
    List<ReportProjection> makeReportForRange(@Param("start") Date start, @Param("end") Date end);
}
