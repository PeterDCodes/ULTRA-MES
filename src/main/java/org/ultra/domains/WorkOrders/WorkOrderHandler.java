package org.ultra.domains.WorkOrders;

import java.sql.SQLException;
import java.util.List;

public class WorkOrderHandler {

    public static WorkOrder makeWorkOrder(int productId, int quantity, int statusCode) {
        WorkOrder newWorkOrder = new WorkOrder(null, productId, quantity, null, statusCode);

        WorkOrderRepository workOrderRepository = new WorkOrderRepository();

        try {
            return workOrderRepository.insert(newWorkOrder);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<WorkOrder> getWorkOrders() {
        WorkOrderRepository workOrderRepository = new WorkOrderRepository();

        try {
            return workOrderRepository.getWorkOrders();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static WorkOrder getWorkOrderById(int orderId) {
        WorkOrderRepository workOrderRepository = new WorkOrderRepository();

        try {
            return workOrderRepository.getWorkOrderById(orderId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static WorkOrder changeOrderStatus(int orderId, int statusCode) {
        WorkOrderRepository repository = new WorkOrderRepository();

        try {
            return repository.changeOrderStatus(orderId, statusCode);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void removeWorkOrder(int orderId) {
        WorkOrderRepository workOrderRepository = new WorkOrderRepository();

        try {
            workOrderRepository.removeWorkOrder(orderId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
