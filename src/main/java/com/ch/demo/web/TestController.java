package com.ch.demo.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/test")
public class TestController {

    //Here we testing post with multipart file
    @GetMapping("/multipart")
    public String multipartForm(){
        return "multipart-demo";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/multipart")
    public String multipartFormConfirm(@RequestParam("file") MultipartFile file,
                                       @RequestParam("name") String name){
        //Add some logic here :)
//        MultipartFile file1 = file;
//        String name1 = name;

        if(file.isEmpty()){
            throw new IllegalArgumentException();
        }



        return "redirect:/home";
    }
}
