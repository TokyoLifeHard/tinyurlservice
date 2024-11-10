package ru.petprojects.tinyurlservice.exception;

import org.springframework.web.bind.annotation.RequestBody;


public class EmptyOriginUrlException extends RuntimeException{
    public EmptyOriginUrlException() {
        super("Origin url is empty");
    }
}
