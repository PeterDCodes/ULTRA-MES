package org.ultra.domains.Sites;

//Site POJO
public class Site {

    //Uses Integer because can be null until assigned by DB
    private Integer siteCode;
    private String siteName;
    private boolean active;

    public Site(Integer siteCode, String siteName, boolean active) {
        this.siteCode = siteCode;
        this.siteName = siteName;
        this.active = active;
    }

    @Override
    public String toString() {
        return "Site{" +
                "siteCode=" + siteCode +
                ", siteName='" + siteName + '\'' +
                ", active=" + active +
                '}';
    }

    public Integer getSiteCode() {
        return siteCode;
    }

    public String getSiteName() {
        return siteName;
    }

    public boolean isActive() {
        return active;
    }

    public void  setSiteCode(int code){
        this.siteCode = code;
    }
}