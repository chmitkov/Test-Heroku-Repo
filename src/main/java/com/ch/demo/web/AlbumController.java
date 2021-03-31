package com.ch.demo.web;

import com.ch.demo.model.binding.AlbumAddBindingModel;
import com.ch.demo.model.service.AlbumServiceModel;
import com.ch.demo.model.view.AlbumViewModel;
import com.ch.demo.service.AlbumService;
import com.ch.demo.service.ArtistService;
import com.ch.demo.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/albums")
public class AlbumController {

    private final AlbumService albumService;
    private final ArtistService artistService;
    private final CommentService commentService;
    private final ModelMapper modelMapper;

    public AlbumController(AlbumService albumService, ArtistService artistService, CommentService commentService, ModelMapper modelMapper) {
        this.albumService = albumService;
        this.artistService = artistService;
        this.commentService = commentService;
        this.modelMapper = modelMapper;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/add")
    public String add(Model model) {
        if (!model.containsAttribute("albumAddBindingModel")) {
            model.addAttribute("albumAddBindingModel", new AlbumAddBindingModel());
        }
        model.addAttribute("artists", artistService.findAllArtistsNames());
        return "add-album";
    }


    @PostMapping("/add")
    public String addConfirm(@Valid AlbumAddBindingModel albumAddBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes,
                             Principal principal) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("albumAddBindingModel", albumAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.albumAddBindingModel", bindingResult);
            return "redirect:add";
        }

        AlbumServiceModel albumServiceModel = modelMapper
                .map(albumAddBindingModel, AlbumServiceModel.class);

        albumService.addAlbum(albumServiceModel, principal.getName());

        return "redirect:/home";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        AlbumViewModel album = albumService.finById(id);

        model.addAttribute("album",album);
        model.addAttribute("comments", commentService
                .findAllByAlbumId(id));
        return "details";
    }

}
