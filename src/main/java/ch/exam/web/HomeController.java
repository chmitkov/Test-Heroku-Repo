package ch.exam.web;

import ch.exam.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    private final ProductService productService;

    public HomeController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public ModelAndView index(HttpSession httpSession, ModelAndView modelAndView) {
        if (httpSession.getAttribute("user") == null) {
            modelAndView.setViewName("index");
        } else {
            modelAndView.addObject("food", this.productService.getAllProductsByCategory("FOOD"));
            modelAndView.addObject("household", this.productService.getAllProductsByCategory("HOUSEHOLD"));
            modelAndView.addObject("drink", this.productService.getAllProductsByCategory("DRINK"));
            modelAndView.addObject("other", this.productService.getAllProductsByCategory("OTHER"));
            modelAndView.addObject("price", this.productService.getTotalSum());
            modelAndView.setViewName("home");
        }
        return modelAndView;
    }
}
