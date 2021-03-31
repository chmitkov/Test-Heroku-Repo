package com.ch.demo.model.view;

import java.time.LocalDateTime;

public class CommentViewModel {
    private String text;
    private LocalDateTime addedOn;
    private UserViewModel user;

    public CommentViewModel() {
    }

    public String getText() {
        return text;
    }

    public CommentViewModel setText(String text) {
        this.text = text;
        return this;
    }

    public UserViewModel getUser() {
        return user;
    }

    public CommentViewModel setUser(UserViewModel user) {
        this.user = user;
        return this;
    }

    public LocalDateTime getAddedOn() {
        return addedOn;
    }

    public CommentViewModel setAddedOn(LocalDateTime addedOn) {
        this.addedOn = addedOn;
        return this;
    }
}
