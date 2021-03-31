package com.ch.demo.web;

import com.ch.demo.model.binding.CommentAddBindingModel;
import com.ch.demo.model.service.CommentServiceModel;
import com.ch.demo.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;
    private final ModelMapper modelMapper;

    public CommentController(CommentService commentService, ModelMapper modelMapper) {
        this.commentService = commentService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/add")
    public String add(CommentAddBindingModel commentAddBindingModel,
                      Principal principal) {

        commentService.add(commentAddBindingModel, principal.getName());

        return "redirect:/albums/details/"
                + commentAddBindingModel.getAlbumId();

    }
}
