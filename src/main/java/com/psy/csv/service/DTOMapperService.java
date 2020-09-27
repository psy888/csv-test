package com.psy.csv.service;

import com.psy.csv.dto.CsvBean;
import lombok.Value;
import org.dozer.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Value
public class DTOMapperService {

    Mapper mapper;

    /**
     * Map DTO to Entity (with DOZER)
     *
     * @param list  - list of parsed objects
     * @param clazz - Entity Class (define map strategy)
     * @return List of Entities
     */
    public List map(List<CsvBean> list, Class clazz) {
        //convert  parsed data to entity list
        return list.stream().map(e -> mapper.map(e, clazz)).collect(Collectors.toList());
    }
}
