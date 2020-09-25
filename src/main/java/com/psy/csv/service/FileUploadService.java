package com.psy.csv.service;

import com.psy.csv.dto.CsvBean;
import com.psy.csv.entity.CSVFile;
import com.psy.csv.util.DeviceTypeUtil;
import lombok.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static java.util.Objects.isNull;

@Service
@Value
public class FileUploadService {
    private static final String RIGHT_EXTENSION = ".csv";

    ParserService parserService;
    FileInfoService fileInfoService;
    NetDeviceService netDeviceService;
    SpecDeviceService specDeviceService;

    /**
     * get uploaded Multipart file extension
     *
     * @param f - multipart file
     * @return - extension with dot ex: ".csv"
     * @throws Exception - if wrong extension or null file name
     */
    private static String getExtension(MultipartFile f) throws Exception {
        String name = f.getOriginalFilename();
        if (isNull(name) || name.lastIndexOf('.') == -1) throw new Exception("File has no extension");
        return name.substring(name.lastIndexOf('.'));
    }

    /**
     * Add (parse, map and save) uploaded file to system
     *
     * @param file      - multipart file
     * @param type      - devices type
     * @param separator - custom csv data separator
     * @param quoteChar - custom csv data quotes
     */
    public void addNewFile(MultipartFile file, String type, String separator, String quoteChar) throws Exception {

        if (!getExtension(file).toLowerCase().contentEquals(RIGHT_EXTENSION))
            throw new Exception("File has wrong extension");

        //parsing
        List<CsvBean> list = parseFile(file, DeviceTypeUtil.getDTOClass(type), separator, quoteChar);


        //if list not empty create and save CSVFile entity
        CSVFile csvFile = fileInfoService.addNewFile(file, type);

        //save all parsed data
        addParsedDevices(list, csvFile, type);

    }

    /**
     * Save all parsed devices to DB
     *
     * @param list - parsed DTO obj list
     * @param file - uploaded File data object
     * @param type - devices type contained in file
     */
    private void addParsedDevices(List<CsvBean> list, CSVFile file, String type) {
        if (DeviceTypeUtil.isNetDevType(type)) {
            netDeviceService.addNewDevices(list, file);
        } else if (DeviceTypeUtil.isSpecDevType(type)) {
            specDeviceService.addNewDevices(list, file);
        }
    }

    /**
     * Parsing csv file to list DTO objects
     *
     * @param file      - csv file
     * @param dtoClazz  - to define parsing strategy
     * @param separator - custom separator char
     * @param quoteChar - custom quote char
     * @return - List of CsvBean Parent class of DTO
     * @throws Exception - if parsing fail
     */
    public List<CsvBean> parseFile(MultipartFile file, Class dtoClazz, String separator, String quoteChar) throws Exception {
        //parsing
        return parserService.parse(file, dtoClazz, separator, quoteChar);
    }


}
