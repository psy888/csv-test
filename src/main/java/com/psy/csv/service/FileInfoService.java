package com.psy.csv.service;

import com.psy.csv.entity.CSVFile;
import com.psy.csv.repository.FileInfoPagedSortedRepository;
import com.psy.csv.util.PageAndSortUtil;
import lombok.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import static java.util.Objects.isNull;

@Service
@Value
public class FileInfoService {

    FileInfoPagedSortedRepository fileInfoPagedSortedRepository;

    public Page<CSVFile> getFilesAllList(int pageNumber, int pageResultLimit, String sortBy, String sortOrder) {
        return fileInfoPagedSortedRepository.findAll(PageRequest.of(pageNumber, pageResultLimit, PageAndSortUtil.getSorting(sortBy, sortOrder)));
    }

    public Page<CSVFile> searchFileByName(String searchRequest, int pageNumber, int pageResultLimit, String sortBy, String sortOrder) {
        return fileInfoPagedSortedRepository.findByFileNameContains(searchRequest, PageRequest.of(pageNumber, pageResultLimit, PageAndSortUtil.getSorting(sortBy, sortOrder)));
    }

    public CSVFile addNewFile(MultipartFile f, String deviceType) throws Exception {
        return fileInfoPagedSortedRepository.save(createEntityForFile(f, deviceType));
    }

    /**
     * Create new  CSVFile entity instance for file
     *
     * @param f - multipart file
     * @return CSVFile entity
     */
    private CSVFile createEntityForFile(MultipartFile f, String deviceType) throws Exception {
        if (isNull(f)) throw new Exception("File is corrupted");
        if (f.getSize() == 0) throw new Exception("File is empty");

        CSVFile csvFile = new CSVFile();
        csvFile.setFileName(f.getOriginalFilename());
        csvFile.setDevType(deviceType);
        csvFile.setUploadDateTime(Date.from(LocalDateTime.now().toInstant(ZoneOffset.ofHours(0)))); //todo get offset
        return csvFile;
    }


}
