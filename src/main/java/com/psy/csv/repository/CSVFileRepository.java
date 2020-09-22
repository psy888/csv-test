package com.psy.csv.repository;

import com.psy.csv.entity.CSVFile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CSVFileRepository extends CrudRepository<CSVFile,Long> {
    List<CSVFile> findAll();
}
