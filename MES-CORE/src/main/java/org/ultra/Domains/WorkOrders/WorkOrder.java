package org.ultra.Domains.WorkOrders;

import java.time.OffsetDateTime;

//WorkOrder POJO
public class WorkOrder {

    //Uses Integer because can be null until assigned by DB
    private Integer orderId;
    private int productId;
    private int quantity;
    private OffsetDateTime createdAt;
    private int statusCode;

    public WorkOrder(Integer orderId, int productId, int quantity, OffsetDateTime createdAt, int statusCode) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.createdAt = createdAt;
        this.statusCode = statusCode;
    }

    @Override
    public String toString() {
        return "WorkOrder{" +
                "orderId=" + orderId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ", createdAt=" + createdAt +
                ", statusCode=" + statusCode +
                '}';
    }

    public Integer getOrderId() {
        return orderId;
    }

    public int getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setOrderId(int code) {
        this.orderId = code;
    }
}
