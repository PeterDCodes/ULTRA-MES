package org.ultra.Domains.MachineTypes;

import java.sql.SQLException;
import java.util.List;

public class MachineTypeHandler {

    public static MachineType makeMachineType(String machineType) {
        MachineType newMachineType = new MachineType(null, machineType);

        MachineTypeRepository machineTypeRepository = new MachineTypeRepository();

        try {
            return machineTypeRepository.insert(newMachineType);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<MachineType> getMachineTypes() {
        MachineTypeRepository machineTypeRepository = new MachineTypeRepository();

        try {
            return machineTypeRepository.getMachineTypes();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static MachineType getMachineTypeById(int typeCode) {
        MachineTypeRepository machineTypeRepository = new MachineTypeRepository();

        try {
            return machineTypeRepository.getMachineTypeById(typeCode);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void removeMachineType(int typeCode) {
        MachineTypeRepository machineTypeRepository = new MachineTypeRepository();

        try {
            machineTypeRepository.removeMachineType(typeCode);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
