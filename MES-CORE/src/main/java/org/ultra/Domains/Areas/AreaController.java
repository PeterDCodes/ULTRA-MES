package org.ultra.Domains.Areas;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/areas")
public class AreaController {

    @PostMapping
    public Area makeArea(@RequestParam("siteCode") int siteCode, @RequestParam("areaName") String areaName) {
        return AreaHandler.makeArea(siteCode, areaName);
    }

    @GetMapping
    public List<Area> getAreas() {
        return AreaHandler.getAreas();
    }

    @GetMapping("/{areaCode}")
    public Area getAreaById(@PathVariable("areaCode") int areaCode) {
        return AreaHandler.getAreaById(areaCode);
    }

    @DeleteMapping("/{areaCode}")
    public void removeArea(@PathVariable("areaCode") int areaCode) {
        AreaHandler.removeArea(areaCode);
    }
}
