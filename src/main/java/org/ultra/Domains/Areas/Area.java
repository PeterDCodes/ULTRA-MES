package org.ultra.Domains.Areas;

//Area POJO
public class Area {

    //Uses Integer because can be null until assigned by DB
    private Integer areaCode;
    private int siteCode;
    private String areaName;
    private boolean active;

    public Area(Integer areaCode, int siteCode, String areaName, boolean active) {
        this.areaCode = areaCode;
        this.siteCode = siteCode;
        this.areaName = areaName;
        this.active = active;
    }

    @Override
    public String toString() {
        return "Area{" +
                "areaCode=" + areaCode +
                ", siteCode=" + siteCode +
                ", areaName=" + areaName +
                ", active=" + active +
                '}';
    }

    public Integer getAreaCode() {
        return areaCode;
    }

    public int getSiteCode() {
        return siteCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public boolean isActive() {
        return active;
    }

    public void setAreaCode(int code) {
        this.areaCode = code;
    }
}
