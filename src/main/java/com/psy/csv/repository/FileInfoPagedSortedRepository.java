package com.psy.csv.repository;

import com.psy.csv.entity.CSVFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

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

}
