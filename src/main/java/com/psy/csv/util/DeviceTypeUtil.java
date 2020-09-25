package com.psy.csv.util;

import com.psy.csv.dto.NetDeviceDTO;
import com.psy.csv.dto.SpecDeviceDTO;
import com.psy.csv.entity.NetDevice;
import com.psy.csv.entity.SpecDevice;

import static java.util.Objects.isNull;

public class DeviceTypeUtil {

    /**
     * Get DTO Class equals to type of devices
     *
     * @param type - device type
     * @return - DTO Class or null
     */
    public static Class getDTOClass(String type) {
        if (type.contentEquals(Types.network.toString())) {
            return NetDeviceDTO.class;
        } else if (type.contentEquals(Types.special.toString())) {
            return SpecDeviceDTO.class;
        }
        return null;
    }

    /**
     * Get Entity Class equals to type of devices
     *
     * @param type - device type
     * @return - Entity Class or null
     */
    public static Class getEntityClass(String type) {
        if (isNetDevType(type)) {
            return NetDevice.class;
        } else if (type.contentEquals(Types.special.toString())) {
            return SpecDevice.class;
        }
        return null;
    }

    /**
     * Get string representation of Device type by Entity class or DTO class assigned to this type
     *
     * @param clazz - entity or DTO class
     * @return String type_name
     */
    public static String getDeviceTypeFromClass(Class clazz) {
        if (isNull(clazz)) return null;
        if (clazz.equals(NetDevice.class) || clazz.equals(NetDeviceDTO.class)) {
            return Types.network.toString();
        } else if (clazz.equals(SpecDevice.class) || clazz.equals(SpecDeviceDTO.class)) {
            return Types.special.toString();
        }
        return null;
    }

    /**
     * Check is string equals to valid type of Networking device
     *
     * @param type - string containing type
     * @return true/false
     */
    public static boolean isNetDevType(String type) {
        return type.trim().equalsIgnoreCase(Types.network.toString());
    }

    /**
     * Check is string equals to valid type of Networking device
     *
     * @param type - string containing type
     * @return true/false
     */
    public static boolean isSpecDevType(String type) {
        return type.trim().equalsIgnoreCase(Types.special.toString());
    }

    /**
     * Valid types of devices
     */
    private enum Types {
        network, special
    }


}
