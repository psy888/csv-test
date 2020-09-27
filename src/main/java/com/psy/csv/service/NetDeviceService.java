package com.psy.csv.service;

import com.psy.csv.dto.CsvBean;
import com.psy.csv.entity.CSVFile;
import com.psy.csv.entity.NetDevice;
import com.psy.csv.repository.FileInfoRepository;
import com.psy.csv.repository.NetDevicePagedSortedRepository;
import com.psy.csv.util.PageAndSortUtil;
import lombok.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.nonNull;

@Service
@Value
public class NetDeviceService implements DeviceService {

    NetDevicePagedSortedRepository repository;
    FileInfoRepository fileRepository;
    DTOMapperService dtoMapperService;

    /**
     * add new parsed and mapped devices to db
     *
     * @param dtoDevices - list of mapped dto devices
     * @param file       - uploaded file dependency for each device
     */
    public void addNewDevices(List<CsvBean> dtoDevices, CSVFile file) {

        List<NetDevice> entityList = dtoMapperService.map(dtoDevices, NetDevice.class); //map dto to entities

        entityList.forEach(o -> o.setFile(file)); //set source file field

        saveAll(entityList);// save all entities
    }

    public void saveAll(List<NetDevice> list) {
        repository.saveAll(list);
    }


    /**
     * Find all devices uploaded in file
     *
     * @param id                 - file id
     * @param page               - current page of results
     * @param resultPerPageLimit - max rows of result
     * @param sortBy             - sort bby field_name
     * @param order              - order "asc"/"desc"
     * @return Page<NetDevice>
     */
    public Page<NetDevice> getAllDevicesListByFile(Long id, int page, int resultPerPageLimit, String sortBy, String order) throws Exception {
        CSVFile file = fileRepository.findById(id).orElseThrow(() -> new Exception("File with id : " + id + " not found"));
        return repository.findAllByFile(file, PageRequest.of(page, resultPerPageLimit, PageAndSortUtil.getSorting(sortBy, order)));

    }

    /**
     * map DTO to entity and save to DB
     *
     * @param device - CsvBean impl dto
     */
    @Override
    public void updateDevice(CsvBean device) {
        NetDevice entity = dtoMapperService.getMapper().map(device, NetDevice.class);
        if (nonNull(entity)) {
            repository.save(entity);
        }

    }


}
