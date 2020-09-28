package com.psy.csv.service;

import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.psy.csv.dto.ReportDTO;
import com.psy.csv.dto.ReportProjection;
import com.psy.csv.repository.FileInfoPagedSortedRepository;
import com.psy.csv.util.DateUtil;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Value
@Slf4j
public class ReportService {

    FileInfoPagedSortedRepository repository;

    public Path generateReport(String start, String end) {
        List<ReportDTO> report = getReport(DateUtil.getDateFromString(start), DateUtil.getDateFromString(end));
        return writeToFile(report);
    }

    private Path writeToFile(List<ReportDTO> reportList) {
        Path reportFilePath = Path.of("report_" + Date.from(Instant.now()).getTime() + ".csv");
        try (Writer writer = Files.newBufferedWriter(reportFilePath)) {
            ColumnPositionMappingStrategy<ReportDTO> ms = new ColumnPositionMappingStrategy<>();
            ms.setType(ReportDTO.class);

            StatefulBeanToCsv<ReportDTO> beanToCsv = new StatefulBeanToCsvBuilder(writer)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .withMappingStrategy(ms)
                    .build();
            beanToCsv.write(reportList);
            return reportFilePath;
        } catch (IOException e) {
            log.error(" Can't create file : " + reportFilePath.getFileName());
            e.printStackTrace();
        } catch (CsvRequiredFieldEmptyException e) {
            e.printStackTrace();
        } catch (CsvDataTypeMismatchException e) {
            log.error("Error while mapping report object");
            e.printStackTrace();
        }
        return null;
    }


    private List<ReportDTO> getReport(Date start, Date end) {

        return castToDto(repository.makeReportForRange(start, end));
    }

    private List<ReportDTO> castToDto(List<ReportProjection> list) {
        return list.stream().map(re -> {
            return ReportDTO.builder()
                    .type(re.getType())
                    .totalDevicesUploaded(re.getTotalDevicesUploaded())
                    .totalWorking(re.getTotalWorking())
                    .totalRepairing(re.getTotalRepairing())
                    .totalRecycling(re.getTotalRecycling())
                    .build();
        }).collect(Collectors.toList());
    }

}
