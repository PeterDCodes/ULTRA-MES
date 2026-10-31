package org.ultra.Domains.WorkOrderHistories;

import java.time.OffsetDateTime;

public class WorkOrderHistory {

    private Integer recordId;
    private Integer workOrder;
    private Integer prevStatus;
    private Integer newStatus;
    private OffsetDateTime dtRecord;

    public WorkOrderHistory(Integer recordId, Integer workOrder, Integer prevStatus,
                            Integer newStatus, OffsetDateTime dtRecord) {
        this.recordId = recordId;
        this.workOrder = workOrder;
        this.prevStatus = prevStatus;
        this.newStatus = newStatus;
        this.dtRecord = dtRecord;
    }

    @Override
    public String toString() {
        return "WorkOrderHistory{" +
                "recordId=" + recordId +
                ", workOrder=" + workOrder +
                ", prevStatus=" + prevStatus +
                ", newStatus=" + newStatus +
                ", dtRecord=" + dtRecord +
                '}';
    }

    public Integer getRecordId() {
        return recordId;
    }

    public Integer getWorkOrder() {
        return workOrder;
    }

    public Integer getPrevStatus() {
        return prevStatus;
    }

    public Integer getNewStatus() {
        return newStatus;
    }

    public OffsetDateTime getDtRecord() {
        return dtRecord;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }
}
