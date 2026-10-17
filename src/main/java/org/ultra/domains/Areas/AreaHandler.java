package org.ultra.domains.Areas;

import java.sql.SQLException;
import java.util.List;

public class AreaHandler {

    public static Area makeArea(int siteCode, String areaName) {
        Area newArea = new Area(null, siteCode, areaName, true);

        AreaRepository areaRepository = new AreaRepository();

        try {
            return areaRepository.insert(newArea);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Area> getAreas() {
        AreaRepository areaRepository = new AreaRepository();

        try {
            return areaRepository.getAreas();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Area getAreaById(int areaCode) {
        AreaRepository areaRepository = new AreaRepository();

        try {
            return areaRepository.getAreaById(areaCode);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void removeArea(int areaCode) {
        AreaRepository areaRepository = new AreaRepository();

        try {
            areaRepository.removeArea(areaCode);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
