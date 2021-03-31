package com.ch.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/statistics")
public class StatController {

    @GetMapping()
    public String stats() {
        return "stats";
    }

}
