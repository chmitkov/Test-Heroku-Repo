package com.ch.demo.web;

import com.ch.demo.exceptions.ArticleNotFoundException;
import com.ch.demo.model.binding.ArticleAddBindingModel;
import com.ch.demo.model.service.ArticleServiceModel;
import com.ch.demo.service.ArticleService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;
    private final ModelMapper modelMapper;

    public ArticleController(ArticleService articleService, ModelMapper modelMapper) {
        this.articleService = articleService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/all")
    public String all(Model model) {
        model.addAttribute("articles", articleService
                .findAllArticles());
        return "all-articles";
    }

    @GetMapping("/add")
    public String add(Model model) {
        if (!model.containsAttribute("articleAddBindingModel")) {
            model.addAttribute("articleAddBindingModel", new ArticleAddBindingModel());
        }
        return "add-article";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid ArticleAddBindingModel articleAddBindingModel,
                             BindingResult bindingResult, RedirectAttributes redirectAttributes,
                             Principal principal) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("articleAddBindingModel", articleAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.articleAddBindingModel", bindingResult);

            return "redirect:add";
        }

        articleService.add(modelMapper
                        .map(articleAddBindingModel, ArticleServiceModel.class),
                principal.getName());

        return "redirect:all";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) throws ArticleNotFoundException {
        model.addAttribute("article", articleService
                .findById(id));

        return "details-article";
    }
}
