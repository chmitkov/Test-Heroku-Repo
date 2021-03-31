package com.ch.demo.service;

import com.ch.demo.exceptions.ArticleNotFoundException;
import com.ch.demo.model.service.ArticleServiceModel;
import com.ch.demo.model.view.ArticleViewModel;

import java.util.List;

public interface ArticleService {
    void add(ArticleServiceModel articleServiceModel, String name);

    List<ArticleViewModel> findAllArticles();

    ArticleViewModel findById(Long id) throws ArticleNotFoundException;
}
