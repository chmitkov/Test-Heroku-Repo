package com.ch.demo.service;

import com.ch.demo.model.binding.CommentAddBindingModel;
import com.ch.demo.model.view.CommentViewModel;

import java.util.List;

public interface CommentService {
    void add(CommentAddBindingModel commentAddBindingModel, String userEmail);

    List<CommentViewModel> findAllByAlbumId(Long id);
}
