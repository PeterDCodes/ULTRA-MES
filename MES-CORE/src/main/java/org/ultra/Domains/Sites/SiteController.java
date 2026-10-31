package org.ultra.Domains.Sites;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sites")
public class SiteController {

    @PostMapping
    public Site makeSite(@RequestParam("siteName") String siteName) {
        return SiteHandler.makeSite(siteName);
    }

    @GetMapping
    public List<Site> getSites() {
        return SiteHandler.getSites();
    }

    @GetMapping("/{siteCode}")
    public Site getSiteById(@PathVariable("siteCode") int siteCode) {
        return SiteHandler.getSiteById(siteCode);
    }

    @DeleteMapping("/{siteCode}")
    public void removeSite(@PathVariable("siteCode") int siteCode) {
        SiteHandler.removeSite(siteCode);
    }
}
