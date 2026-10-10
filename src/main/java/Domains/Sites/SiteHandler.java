package Domains.Sites;

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

    //Get a specific site

    //Remove a site

}
