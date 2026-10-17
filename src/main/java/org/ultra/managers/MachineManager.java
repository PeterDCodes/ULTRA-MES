package org.ultra.managers;

import org.ultra.domains.Areas.AreaHandler;
import org.ultra.domains.MachineAssignments.MachineAssignment;
import org.ultra.domains.MachineAssignments.MachineAssignmentHandler;
import org.ultra.domains.Machines.Machine;
import org.ultra.domains.Machines.MachineHandler;
import org.ultra.domains.MachineTypes.MachineType;
import org.ultra.domains.MachineTypes.MachineTypeHandler;

import java.util.Scanner;

public class MachineManager {

    //Creates a new machine type
    public static MachineType createMachineType() {
        Scanner scanner = new Scanner(System.in);
        String machineTypeName = promptMachineTypeName(scanner);
        return MachineTypeHandler.makeMachineType(machineTypeName);
    }

    //Creates a new machine
    public static Machine createMachine() {
        Scanner scanner = new Scanner(System.in);
        String machineName = promptMachineName(scanner);
        int machineType;

        while (true) {
            machineType = promptMachineType(scanner);
            if (MachineTypeHandler.getMachineTypeById(machineType) != null) {
                break;
            } else {
                System.out.println("Invalid Machine Type ID");
            }
        }

        return MachineHandler.makeMachine(machineName, machineType);
    }

    //Assigns a machine to an area
    public static MachineAssignment assignMachineToArea() {
        Scanner scanner = new Scanner(System.in);
        int machineCode;
        int areaCode;

        while (true) {
            machineCode = promptMachine(scanner);
            if (MachineHandler.getMachineById(machineCode) != null) {
                break;
            } else {
                System.out.println("Invalid Machine ID");
            }
        }

        while (true) {
            areaCode = promptArea(scanner);
            if (AreaHandler.getAreaById(areaCode) != null) {
                break;
            } else {
                System.out.println("Invalid Area ID");
            }
        }

        return MachineAssignmentHandler.makeMachineAssignment(areaCode, machineCode);
    }

    private static String promptMachineName(Scanner scanner) {
        System.out.print("Enter machine name: ");
        return scanner.nextLine();
    }

    private static String promptMachineTypeName(Scanner scanner) {
        System.out.print("Enter machine type name: ");
        return scanner.nextLine();
    }

    private static int promptMachineType(Scanner scanner) {
        System.out.print("Enter machine type id: ");
        return Integer.parseInt(scanner.nextLine());
    }

    private static int promptMachine(Scanner scanner) {
        System.out.print("Enter machine id: ");
        return Integer.parseInt(scanner.nextLine());
    }

    private static int promptArea(Scanner scanner) {
        System.out.print("Enter area id: ");
        return Integer.parseInt(scanner.nextLine());
    }
}
