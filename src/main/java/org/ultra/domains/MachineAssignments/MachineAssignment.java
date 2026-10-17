package org.ultra.domains.MachineAssignments;

import java.time.OffsetDateTime;

//MachineAssignment POJO
public class MachineAssignment {

    //Uses Integer because can be null until assigned by DB
    private Integer assignmentCode;
    private int areaCode;
    private int machineCode;
    private OffsetDateTime createdAt;
    private boolean active;

    public MachineAssignment(Integer assignmentCode, int areaCode, int machineCode, OffsetDateTime createdAt, boolean active) {
        this.assignmentCode = assignmentCode;
        this.areaCode = areaCode;
        this.machineCode = machineCode;
        this.createdAt = createdAt;
        this.active = active;
    }

    @Override
    public String toString() {
        return "MachineAssignment{" +
                "assignmentCode=" + assignmentCode +
                ", areaCode=" + areaCode +
                ", machineCode=" + machineCode +
                ", createdAt=" + createdAt +
                ", active=" + active +
                '}';
    }

    public Integer getAssignmentCode() {
        return assignmentCode;
    }

    public int getAreaCode() {
        return areaCode;
    }

    public int getMachineCode() {
        return machineCode;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public boolean isActive() {
        return active;
    }

    public void setAssignmentCode(int code) {
        this.assignmentCode = code;
    }
}
