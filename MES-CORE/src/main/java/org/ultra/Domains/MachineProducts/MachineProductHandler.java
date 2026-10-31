package org.ultra.Domains.MachineProducts;

import java.sql.SQLException;
import java.util.List;

public class MachineProductHandler {

    public static MachineProduct makeMachineProduct(int typeCode, int productCode) {
        MachineProduct newMachineProduct = new MachineProduct(null, typeCode, productCode);

        MachineProductRepository machineProductRepository = new MachineProductRepository();

        try {
            return machineProductRepository.insert(newMachineProduct);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<MachineProduct> getMachineProducts() {
        MachineProductRepository machineProductRepository = new MachineProductRepository();

        try {
            return machineProductRepository.getMachineProducts();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static MachineProduct getMachineProductById(int machineProductCode) {
        MachineProductRepository machineProductRepository = new MachineProductRepository();

        try {
            return machineProductRepository.getMachineProductById(machineProductCode);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void removeMachineProduct(int machineProductCode) {
        MachineProductRepository machineProductRepository = new MachineProductRepository();

        try {
            machineProductRepository.removeMachineProduct(machineProductCode);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
