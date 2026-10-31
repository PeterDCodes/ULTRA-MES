package org.ultra.Domains.OrderStatusCodes;

import java.sql.SQLException;
import java.util.List;

public class OrderStatusCodeHandler {

    public static OrderStatusCode makeOrderStatusCode(String status) {
        OrderStatusCode newOrderStatusCode = new OrderStatusCode(null, status);

        OrderStatusCodeRepository orderStatusCodeRepository = new OrderStatusCodeRepository();

        try {
            return orderStatusCodeRepository.insert(newOrderStatusCode);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<OrderStatusCode> getOrderStatusCodes() {
        OrderStatusCodeRepository orderStatusCodeRepository = new OrderStatusCodeRepository();

        try {
            return orderStatusCodeRepository.getOrderStatusCodes();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static OrderStatusCode getOrderStatusCodeById(int statusId) {
        OrderStatusCodeRepository orderStatusCodeRepository = new OrderStatusCodeRepository();

        try {
            return orderStatusCodeRepository.getOrderStatusCodeById(statusId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void removeOrderStatusCode(int statusId) {
        OrderStatusCodeRepository orderStatusCodeRepository = new OrderStatusCodeRepository();

        try {
            orderStatusCodeRepository.removeOrderStatusCode(statusId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
