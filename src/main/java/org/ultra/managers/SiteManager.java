package org.ultra.managers;

import org.ultra.domains.Areas.Area;
import org.ultra.domains.Areas.AreaHandler;
import org.ultra.domains.Sites.Site;
import org.ultra.domains.Sites.SiteHandler;

import java.util.Scanner;

public class SiteManager {

    //Creates a new site
    public static Site createSite() {
        Scanner scanner = new Scanner(System.in);
        String siteName = promptSiteName(scanner);
        return SiteHandler.makeSite(siteName);
    }

    //Creates an area within an existing site
    public static Area createArea() {
        Scanner scanner = new Scanner(System.in);
        int siteCode;

        while (true) {
            siteCode = promptSite(scanner);
            if (SiteHandler.getSiteById(siteCode) != null) {
                break;
            } else {
                System.out.println("Invalid Site ID");
            }
        }

        String areaName = promptAreaName(scanner);
        return AreaHandler.makeArea(siteCode, areaName);
    }

    private static String promptSiteName(Scanner scanner) {
        System.out.print("Enter site name: ");
        return scanner.nextLine();
    }

    private static int promptSite(Scanner scanner) {
        System.out.print("Enter site id: ");
        return Integer.parseInt(scanner.nextLine());
    }

    private static String promptAreaName(Scanner scanner) {
        System.out.print("Enter area name: ");
        return scanner.nextLine();
    }
}
