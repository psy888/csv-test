package com.psy.csv.service;

import com.psy.csv.entity.CSVFile;
import com.psy.csv.repository.FileInfoRepository;
import lombok.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@Value
public class FileInfoService {

    FileInfoRepository fileInfoRepository;

    public Page<CSVFile> getFilesAllList(int pageNumber, int pageResultLimit, String sortBy, String sortOrder) {
        return fileInfoRepository.findAll(PageRequest.of(pageNumber, pageResultLimit, getSorting(sortBy, sortOrder)));
    }

    public Page<CSVFile> searchFileByName(String searchRequest, int pageNumber, int pageResultLimit, String sortBy, String sortOrder) {
        return fileInfoRepository.findByFileNameContains(searchRequest, PageRequest.of(pageNumber, pageResultLimit, getSorting(sortBy, sortOrder)));
    }

    private Sort getSorting(String sortBy, String order) {
        Sort s = Sort.by(sortBy);
        if ("dsc".contentEquals(order)) {
            s.descending();
        } else {
            s.ascending();
        }
        return s;
    }
}
