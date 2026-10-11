package org.ultra.Domains.Machines;

//Machine POJO
public class Machine {

    //Uses Integer because can be null until assigned by DB
    private Integer machineCode;
    private Integer machineId;
    private int machineType;
    private boolean active;

    public Machine(Integer machineCode, Integer machineId, int machineType, boolean active) {
        this.machineCode = machineCode;
        this.machineId = machineId;
        this.machineType = machineType;
        this.active = active;
    }

    @Override
    public String toString() {
        return "Machine{" +
                "machineCode=" + machineCode +
                ", machineId=" + machineId +
                ", machineType=" + machineType +
                ", active=" + active +
                '}';
    }

    public Integer getMachineCode() {
        return machineCode;
    }

    public Integer getMachineId() {
        return machineId;
    }

    public int getMachineType() {
        return machineType;
    }

    public boolean isActive() {
        return active;
    }

    public void setMachineCode(int code) {
        this.machineCode = code;
    }
}
