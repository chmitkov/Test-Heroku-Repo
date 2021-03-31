package com.ch.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Missing article")
public class ArticleNotFoundException extends Exception{

    public ArticleNotFoundException(){
    }

    public ArticleNotFoundException(String message){
        super(message);
    }
}
