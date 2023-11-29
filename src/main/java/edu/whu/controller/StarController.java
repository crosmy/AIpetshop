package edu.whu.controller;

import edu.whu.service.IPetService;
import edu.whu.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class StarController {


    @Autowired
    private IPostService postService;

    @PutMapping("/ratePost")
    public String ratePost(@RequestParam Long id, @RequestParam Integer star) {
        postService.ratePost(id, star);
        return "Rating updated successfully";
    }
}
