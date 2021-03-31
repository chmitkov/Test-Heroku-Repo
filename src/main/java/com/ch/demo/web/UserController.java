package com.ch.demo.web;

import com.ch.demo.model.binding.UserRegisterBindingModel;
import com.ch.demo.model.service.RegistrationServiceModel;
import com.ch.demo.service.UserService;
import java.util.Arrays;
import java.util.Objects;
import org.modelmapper.ModelMapper;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute("registrationModel")
    public UserRegisterBindingModel userModel(){
        return new UserRegisterBindingModel();
    }

    @PostMapping("/login-error")
    public String onLoginError(
        @ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
            String user,
        RedirectAttributes attributes) {


        attributes.addFlashAttribute("error", "bad_credentials");
        attributes.addFlashAttribute("username", user);

        return "redirect:/users/login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid UserRegisterBindingModel registrationModel,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("registrationModel", registrationModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registrationModel", bindingResult);

            return "redirect:/users/register";
        }

        if (userService.existsByName(registrationModel.getUsername())) {
            redirectAttributes.addFlashAttribute("registrationModel", registrationModel);
            redirectAttributes.addFlashAttribute("notUnique", true);
            return "redirect:/users/register";
        }

        var serviceModel = modelMapper
            .map(registrationModel, RegistrationServiceModel.class);

        userService
                .register(serviceModel);


        return "redirect:/";
    }
}
