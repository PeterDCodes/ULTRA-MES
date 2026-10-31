package org.ultra.Domains.OrderStatusCodes;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order-status-codes")
public class OrderStatusCodeController {

    @PostMapping
    public OrderStatusCode makeOrderStatusCode(@RequestParam("status") String status) {
        return OrderStatusCodeHandler.makeOrderStatusCode(status);
    }

    @GetMapping
    public List<OrderStatusCode> getOrderStatusCodes() {
        return OrderStatusCodeHandler.getOrderStatusCodes();
    }

    @GetMapping("/{statusId}")
    public OrderStatusCode getOrderStatusCodeById(@PathVariable("statusId") int statusId) {
        return OrderStatusCodeHandler.getOrderStatusCodeById(statusId);
    }

    @DeleteMapping("/{statusId}")
    public void removeOrderStatusCode(@PathVariable("statusId") int statusId) {
        OrderStatusCodeHandler.removeOrderStatusCode(statusId);
    }
}
