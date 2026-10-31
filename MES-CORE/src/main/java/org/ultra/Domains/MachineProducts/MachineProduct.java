package org.ultra.Domains.MachineProducts;

//MachineProduct POJO
public class MachineProduct {

    //Uses Integer because can be null until assigned by DB
    private Integer machineProductCode;
    private int typeCode;
    private int productCode;

    public MachineProduct(Integer machineProductCode, int typeCode, int productCode) {
        this.machineProductCode = machineProductCode;
        this.typeCode = typeCode;
        this.productCode = productCode;
    }

    @Override
    public String toString() {
        return "MachineProduct{" +
                "machineProductCode=" + machineProductCode +
                ", typeCode=" + typeCode +
                ", productCode=" + productCode +
                '}';
    }

    public Integer getMachineProductCode() {
        return machineProductCode;
    }

    public int getTypeCode() {
        return typeCode;
    }

    public int getProductCode() {
        return productCode;
    }

    public void setMachineProductCode(int code) {
        this.machineProductCode = code;
    }
}
