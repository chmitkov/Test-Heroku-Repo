package com.ch.demo.web;

import com.ch.demo.service.CarouselService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final CarouselService carouselService;

    public HomeController(CarouselService carouselService) {
        this.carouselService = carouselService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/home")
    public String home(Model model){
        model.addAttribute("firstImage", carouselService.getFirst());
        model.addAttribute("secondImage", carouselService.getSecond());
        model.addAttribute("thirdImage", carouselService.getThird());
        return "home";
    }
}
