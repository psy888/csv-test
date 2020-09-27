package com.psy.csv.service;

import com.psy.csv.dto.CsvBean;
import com.psy.csv.entity.CSVFile;
import com.psy.csv.entity.SpecDevice;
import com.psy.csv.repository.SpecDevicePagedSortedRepository;
import com.psy.csv.util.PageAndSortUtil;
import lombok.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Value
public class SpecDeviceService implements DeviceService {

    SpecDevicePagedSortedRepository repository;
    DTOMapperService dtoMapperService;

    /**
     * add new parsed and mapped devices to db
     *
     * @param dtoDevices - list of mapped dto devices
     * @param file       - uploaded file dependency for each device
     */
    public void addNewDevices(List<CsvBean> dtoDevices, CSVFile file) {

        List<SpecDevice> entityList = dtoMapperService.map(dtoDevices, SpecDevice.class); //map dto to entities

        entityList.forEach(o -> o.setFile(file)); //set source file field

        saveAll(entityList);// save all entities
    }

    public void saveAll(List<SpecDevice> list) {
        repository.saveAll(list);
    }

    public Page<SpecDevice> getAllDevicesListByFile(Long id, Integer page, int resultPerPageLimit, String sortBy, String order) {
        return repository.findAllByFileId(id, PageRequest.of(page, resultPerPageLimit, PageAndSortUtil.getSorting(sortBy, order)));

    }

    @Override
    public void updateDevice(CsvBean device) {
        repository.save((SpecDevice) device);
    }
}
