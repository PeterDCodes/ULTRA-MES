package org.ultra.domains.Machines;

import java.sql.SQLException;
import java.util.List;

public class MachineHandler {

    public static Machine makeMachine(String machineName, int machineType) {
        Machine newMachine = new Machine(null, machineName, machineType, true);

        MachineRepository machineRepository = new MachineRepository();

        try {
            return machineRepository.insert(newMachine);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Machine> getMachines() {
        MachineRepository machineRepository = new MachineRepository();

        try {
            return machineRepository.getMachines();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Machine getMachineById(int machineCode) {
        MachineRepository machineRepository = new MachineRepository();

        try {
            return machineRepository.getMachineById(machineCode);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void removeMachine(int machineCode) {
        MachineRepository machineRepository = new MachineRepository();

        try {
            machineRepository.removeMachine(machineCode);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
