package com.ch.demo.service.impl;

import com.ch.demo.exceptions.ArticleNotFoundException;
import com.ch.demo.model.entity.ArticleEntity;
import com.ch.demo.model.service.ArticleServiceModel;
import com.ch.demo.model.view.ArticleViewModel;
import com.ch.demo.repository.ArticleRepository;
import com.ch.demo.service.ArticleService;
import com.ch.demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public ArticleServiceImpl(ArticleRepository articleRepository, UserService userService, ModelMapper modelMapper) {
        this.articleRepository = articleRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void add(ArticleServiceModel articleServiceModel, String name) {
        ArticleEntity article = modelMapper
                .map(articleServiceModel, ArticleEntity.class)
                .setAddedOn(LocalDateTime.now())
                .setAuthor(userService.findByEmail(name));

        articleRepository.save(article);
    }

    @Override
    public List<ArticleViewModel> findAllArticles() {
        return articleRepository
                .findAll()
                .stream().map(articleEntity -> modelMapper
                        .map(articleEntity, ArticleViewModel.class)
                        .setAuthor(articleEntity.getAuthor().getUsername()))
                .collect(Collectors.toList());
    }

    @Override
    public ArticleViewModel findById(Long id) throws ArticleNotFoundException {
        return articleRepository
                .findById(id)
                .map(articleEntity -> modelMapper
                        .map(articleEntity, ArticleViewModel.class)
                        .setAuthor(articleEntity.getAuthor().getUsername()))
                .orElseThrow(ArticleNotFoundException::new);

    }
}
