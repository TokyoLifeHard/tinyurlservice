package ru.petprojects.tinyurlservice.exception;

public class TinyUrlAlreadyExistsException extends RuntimeException{

    public TinyUrlAlreadyExistsException(String originUrl) {
        super("Tiny URL for ".concat(originUrl).concat(" is already exists"));
    }
}
