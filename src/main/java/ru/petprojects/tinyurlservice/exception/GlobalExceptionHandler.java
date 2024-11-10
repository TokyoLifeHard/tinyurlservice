package ru.petprojects.tinyurlservice.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.petprojects.tinyurlservice.dto.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(TinyUrlAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleTinyUrlAlreadyExistsException(TinyUrlAlreadyExistsException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.CREATED.value(), ex.getMessage());
        logger.error("Request {}",ex.getMessage());
        return ResponseEntity.status(HttpStatus.CREATED).body(errorResponse);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return super.handleMissingServletRequestParameter(ex, headers, status, request);
    }

    @ExceptionHandler(EmptyOriginUrlException.class)
    public ResponseEntity<ErrorResponse> handleTinyUrlAlreadyExistsException(EmptyOriginUrlException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        logger.error("Request {}",ex.getMessage());
        return ResponseEntity.status(HttpStatus.CREATED).body(errorResponse);
    }
}
