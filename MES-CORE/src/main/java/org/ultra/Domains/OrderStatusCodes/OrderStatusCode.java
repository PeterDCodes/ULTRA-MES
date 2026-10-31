package org.ultra.Domains.OrderStatusCodes;

//OrderStatusCode POJO
public class OrderStatusCode {

    //Uses Integer because can be null until assigned by DB
    private Integer statusId;
    private String status;

    public OrderStatusCode(Integer statusId, String status) {
        this.statusId = statusId;
        this.status = status;
    }

    @Override
    public String toString() {
        return "OrderStatusCode{" +
                "statusId=" + statusId +
                ", status=" + status +
                '}';
    }

    public Integer getStatusId() {
        return statusId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatusId(int code) {
        this.statusId = code;
    }
}
