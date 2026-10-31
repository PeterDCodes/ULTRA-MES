package org.ultra.Domains.MachineTypes;

//MachineType POJO
public class MachineType {

    //Uses Integer because can be null until assigned by DB
    private Integer typeCode;
    private String machineType;

    public MachineType(Integer typeCode, String machineType) {
        this.typeCode = typeCode;
        this.machineType = machineType;
    }

    @Override
    public String toString() {
        return "MachineType{" +
                "typeCode=" + typeCode +
                ", machineType=" + machineType +
                '}';
    }

    public Integer getTypeCode() {
        return typeCode;
    }

    public String getMachineType() {
        return machineType;
    }

    public void setTypeCode(int code) {
        this.typeCode = code;
    }
}
