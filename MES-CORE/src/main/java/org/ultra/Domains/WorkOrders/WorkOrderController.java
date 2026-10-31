package org.ultra.Domains.WorkOrders;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/work-orders")
public class WorkOrderController {

    @PostMapping
    public WorkOrder makeWorkOrder(@RequestParam("productId") int productId, @RequestParam("quantity") int quantity, @RequestParam("statusCode") int statusCode) {
        return WorkOrderHandler.makeWorkOrder(productId, quantity, statusCode);
    }

    @GetMapping
    public List<WorkOrder> getWorkOrders() {
        return WorkOrderHandler.getWorkOrders();
    }

    @GetMapping("/{orderId}")
    public WorkOrder getWorkOrderById(@PathVariable("orderId") int orderId) {
        return WorkOrderHandler.getWorkOrderById(orderId);
    }

    @PatchMapping("/{orderId}/status")
    public WorkOrder changeOrderStatus(@PathVariable("orderId") int orderId, @RequestParam("statusCode") int statusCode) {
        return WorkOrderHandler.changeOrderStatus(orderId, statusCode);
    }

    @DeleteMapping("/{orderId}")
    public void removeWorkOrder(@PathVariable("orderId") int orderId) {
        WorkOrderHandler.removeWorkOrder(orderId);
    }
}
