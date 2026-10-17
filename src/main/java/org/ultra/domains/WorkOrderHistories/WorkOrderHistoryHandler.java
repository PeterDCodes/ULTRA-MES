package org.ultra.domains.WorkOrderHistories;

import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.util.List;

public class WorkOrderHistoryHandler {

    public static WorkOrderHistory makeWorkOrderHistory(Integer workOrder, Integer prevStatus,
                                                        Integer newStatus) {
        WorkOrderHistory history = new WorkOrderHistory(
                null, workOrder, prevStatus, newStatus, OffsetDateTime.now()
        );
        WorkOrderHistoryRepository repository = new WorkOrderHistoryRepository();

        try {
            return repository.insert(history);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<WorkOrderHistory> getWorkOrderHistories() {
        WorkOrderHistoryRepository repository = new WorkOrderHistoryRepository();

        try {
            return repository.getWorkOrderHistories();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static WorkOrderHistory getWorkOrderHistoryById(int recordId) {
        WorkOrderHistoryRepository repository = new WorkOrderHistoryRepository();

        try {
            return repository.getWorkOrderHistoryById(recordId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void removeWorkOrderHistory(int recordId) {
        WorkOrderHistoryRepository repository = new WorkOrderHistoryRepository();

        try {
            repository.removeWorkOrderHistory(recordId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
