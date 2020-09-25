package com.psy.csv.repository;

import com.psy.csv.entity.CSVFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
public interface FileInfoRepository extends PagingAndSortingRepository<CSVFile, Long> {

    Page<CSVFile> findAll(Pageable pageable); //list of all files with pager usage : PageRequest.of(int pageIndex, int cntPerPage)

    Page<CSVFile> findByFileNameContains(String name, Pageable pageable); //find by name with pager


}
