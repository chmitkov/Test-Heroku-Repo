package ch.exam.web;

import ch.exam.model.binding.ProductAddBindingModel;
import ch.exam.model.service.ProductServiceModel;
import ch.exam.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductController {


    private final ProductService productService;
    private final ModelMapper modelMapper;

    public ProductController(ProductService productService, ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public String add(Model model) {

        if (!model.containsAttribute("productAddBindingModel")) {
            model.addAttribute("productAddBindingModel", new ProductAddBindingModel());
        }
        return "product-add";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid @ModelAttribute("productAddBindingModel") ProductAddBindingModel productAddBindingModel,
                             BindingResult bindingResult, RedirectAttributes redirectAttributes,
                             HttpServletRequest httpServletRequest) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("productAddBindingModel", productAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.productAddBindingModel", bindingResult);

            return "redirect:add";
        }

        this.productService.addProduct(this.modelMapper
                .map(productAddBindingModel, ProductServiceModel.class));

        return "redirect:/";

    }

    @GetMapping("/buy/{id}")
    public String buy(@PathVariable("id") String id) {
        if (id.equals("all")) {
            this.productService.buyAll();
        } else {
            this.productService.buyProductById(id);
        }

        return "redirect:/";
    }
}
