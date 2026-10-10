package org.ultra;


import Domains.Sites.Site;
import Domains.Sites.SiteHandler;

public class Main {
    public static void main(String[] args) {

//        Site newSite = SiteHandler.makeSite("Site-2");
//
//        System.out.println(newSite.getSiteCode());
//        System.out.println(newSite.getSiteName());
//        System.out.println(newSite.isActive());

        System.out.println(SiteHandler.getSites());

    }
}