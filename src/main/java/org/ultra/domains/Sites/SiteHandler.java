package org.ultra.domains.Sites;

import java.sql.SQLException;
import java.util.List;

public class SiteHandler {

    //Make a new site
    public static Site makeSite(String siteName){

        //Creates a new db record for the site
        Site newSite = new Site(null, siteName, true);

        SiteRepository siteRepository = new SiteRepository();

        try{
            return siteRepository.insert(newSite);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Get all sites
    public static List<Site> getSites() {
        SiteRepository siteRepository = new SiteRepository();

        try {
            return siteRepository.getSites();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Get a specific site by its code
    public static Site getSiteById(int siteCode) {
        SiteRepository siteRepository = new SiteRepository();

        try {
            return siteRepository.getSiteById(siteCode);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Remove a site by its code
    public static void removeSite(int siteCode) {
        SiteRepository siteRepository = new SiteRepository();

        try {
            siteRepository.removeSite(siteCode);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
