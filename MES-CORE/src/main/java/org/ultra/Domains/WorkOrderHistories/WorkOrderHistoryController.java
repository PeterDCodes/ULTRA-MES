package org.ultra.Domains.WorkOrderHistories;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/work-order-histories")
public class WorkOrderHistoryController {

    @PostMapping
    public WorkOrderHistory makeWorkOrderHistory(@RequestParam("workOrder") Integer workOrder, @RequestParam("prevStatus") Integer prevStatus, @RequestParam("newStatus") Integer newStatus) {
        return WorkOrderHistoryHandler.makeWorkOrderHistory(workOrder, prevStatus, newStatus);
    }

    @GetMapping
    public List<WorkOrderHistory> getWorkOrderHistories() {
        return WorkOrderHistoryHandler.getWorkOrderHistories();
    }

    @GetMapping("/{recordId}")
    public WorkOrderHistory getWorkOrderHistoryById(@PathVariable("recordId") int recordId) {
        return WorkOrderHistoryHandler.getWorkOrderHistoryById(recordId);
    }

    @DeleteMapping("/{recordId}")
    public void removeWorkOrderHistory(@PathVariable("recordId") int recordId) {
        WorkOrderHistoryHandler.removeWorkOrderHistory(recordId);
    }
}
