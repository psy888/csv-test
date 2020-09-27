package com.psy.csv.controller;

import com.psy.csv.dto.NetDeviceDTO;
import com.psy.csv.entity.NetDevice;
import com.psy.csv.entity.SpecDevice;
import com.psy.csv.service.FileInfoService;
import com.psy.csv.service.NetDeviceService;
import com.psy.csv.service.SpecDeviceService;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@Value
@Slf4j
public class DeviceController {

    public static final int ROWS_PER_PAGE = 20;

    FileInfoService fileInfoService;
    NetDeviceService netDeviceService;
    SpecDeviceService specDeviceService;

    @GetMapping("/net-device")
    public Page<NetDevice> getNetDevicesByFile(
            @RequestParam(name = "fileId") Long id,
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer p,
            @RequestParam(name = "sortBy", required = false, defaultValue = "name") String sortBy,
            @RequestParam(name = "sortOrder", required = false, defaultValue = "asc") String sortOrder) {

        try {
            return netDeviceService.getAllDevicesListByFile(id, p, ROWS_PER_PAGE, sortBy, sortOrder);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    @PostMapping("/net-device")
    public void updateDevice(@RequestBody NetDeviceDTO device) {
        log.info(device.toString());
        netDeviceService.updateDevice(device);
    }

    @GetMapping("/spec-device")
    public Page<SpecDevice> getSpecDevicesByFile(
            @RequestParam(name = "fileId") Long id,
            @RequestParam(name = "page", required = false) Integer p,
            @RequestParam(name = "sortBy", required = false) String sortBy,
            @RequestParam(name = "sortOrder", required = false) String sortOrder) {

        return specDeviceService.getAllDevicesListByFile(id, p, ROWS_PER_PAGE, sortBy, sortOrder);
    }


//    @PostMapping("/net-device")
//    public void editDevice(@RequestBody Device device) {
//        log.info(device.toString());
//        String type = device.getFile().getDevType();
//        if (isNetDevType(type)) {
//            netDeviceService.updateDevice(device);
//        } else if (isSpecDevType(type)) {
//            specDeviceService.updateDevice(device);
//        }
//    }

}
