package org.ultra.domains.MachineAssignments;

import java.sql.SQLException;
import java.util.List;

public class MachineAssignmentHandler {

    public static MachineAssignment makeMachineAssignment(int areaCode, int machineCode) {
        MachineAssignment newMachineAssignment = new MachineAssignment(null, areaCode, machineCode, null, true);

        MachineAssignmentRepository machineAssignmentRepository = new MachineAssignmentRepository();

        try {
            return machineAssignmentRepository.insert(newMachineAssignment);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<MachineAssignment> getMachineAssignments() {
        MachineAssignmentRepository machineAssignmentRepository = new MachineAssignmentRepository();

        try {
            return machineAssignmentRepository.getMachineAssignments();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static MachineAssignment getMachineAssignmentById(int assignmentCode) {
        MachineAssignmentRepository machineAssignmentRepository = new MachineAssignmentRepository();

        try {
            return machineAssignmentRepository.getMachineAssignmentById(assignmentCode);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void removeMachineAssignment(int assignmentCode) {
        MachineAssignmentRepository machineAssignmentRepository = new MachineAssignmentRepository();

        try {
            machineAssignmentRepository.removeMachineAssignment(assignmentCode);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
