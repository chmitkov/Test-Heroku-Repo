package com.ch.demo.service.impl;

import com.ch.demo.model.binding.CommentAddBindingModel;
import com.ch.demo.model.entity.CommentEntity;
import com.ch.demo.model.view.CommentViewModel;
import com.ch.demo.repository.CommentRepository;
import com.ch.demo.service.AlbumService;
import com.ch.demo.service.CommentService;
import com.ch.demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final UserService userService;
    private final AlbumService albumService;
    private final ModelMapper modelMapper;

    public CommentServiceImpl(CommentRepository commentRepository, UserService userService, AlbumService albumService, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.albumService = albumService;
        this.modelMapper = modelMapper;
    }


    @Override
    public void add(CommentAddBindingModel commentAddBindingModel, String userEmail) {
        CommentEntity comment = new CommentEntity()
                .setText(commentAddBindingModel.getText())
                .setAddedOn(LocalDateTime.now())
                .setAlbum(albumService.findById(commentAddBindingModel.getAlbumId()))
                .setUser(userService.findByEmail(userEmail));

        commentRepository.save(comment);
    }

    @Override
    public List<CommentViewModel> findAllByAlbumId(Long id) {

        return commentRepository
                .findAllByAlbum_Id(id)
                .stream().map(commentEntity -> modelMapper
                        .map(commentEntity, CommentViewModel.class))
                .collect(Collectors.toList());
    }
}
