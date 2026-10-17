package org.ultra.domains.Machines;

//Machine POJO
public class Machine {

    //Uses Integer because can be null until assigned by DB
    private Integer machineCode;
    private String machineName;
    private int machineType;
    private boolean active;

    public Machine(Integer machineCode, String machineName, int machineType, boolean active) {
        this.machineCode = machineCode;
        this.machineName = machineName;
        this.machineType = machineType;
        this.active = active;
    }

    @Override
    public String toString() {
        return "Machine{" +
                "machineCode=" + machineCode +
                ", machineName='" + machineName + '\'' +
                ", machineType=" + machineType +
                ", active=" + active +
                '}';
    }

    public Integer getMachineCode() {
        return machineCode;
    }

    public String getMachineName() {
        return machineName;
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
