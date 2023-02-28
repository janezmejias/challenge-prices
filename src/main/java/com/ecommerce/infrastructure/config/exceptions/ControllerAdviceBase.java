package com.ecommerce.infrastructure.config.exceptions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.management.AttributeNotFoundException;

@ControllerAdvice
public class ControllerAdviceBase extends ResponseEntityExceptionHandler {

    @ExceptionHandler(JsonMappingException.class)
    public ProblemDetail handleConverterErrors(JsonMappingException ex, HttpServletRequest request) {
        return buildProblemDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler({AttributeNotFoundException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ProblemDetail handleAttributeNotFoundException(Exception ex, HttpServletRequest request) {
        return buildProblemDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler({JsonProcessingException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ProblemDetail handleJsonProcessingException(Exception ex, HttpServletRequest request) {
        return buildProblemDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler({HttpClientErrorException.BadRequest.class})
    public ProblemDetailBase handleBadRequestException(HttpClientErrorException ex) {
        return buildProblemDetail(HttpStatus.valueOf(ex.getStatusCode().value()), ex.getMessage());
    }

    @ExceptionHandler({HttpClientErrorException.TooManyRequests.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ProblemDetail handleTooManyRequestException(HttpClientErrorException ex) {
        return buildProblemDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler({HttpClientErrorException.Conflict.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ProblemDetail handleConflictException(HttpClientErrorException.Conflict ex) {
        return buildProblemDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler({HttpServerErrorException.InternalServerError.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ProblemDetail handleInterServerErrorException(HttpServerErrorException ex) {
        return buildProblemDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler({HttpServerErrorException.ServiceUnavailable.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ProblemDetail unavailableException(HttpServerErrorException.ServiceUnavailable ex) {
        return buildProblemDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler({IllegalStateException.class, IllegalArgumentException.class})
    public ProblemDetail handleCatchErrors(Exception ex) {
        return buildProblemDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler({Exception.class})
    public ProblemDetail generalException(Exception ex, HttpServletRequest request) {
        return buildProblemDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    private ProblemDetailBase buildProblemDetail(HttpStatus httpStatus, String message) {
        ProblemDetailBase problemDetailBase = new ProblemDetailBase();
        problemDetailBase.setStatus(httpStatus);
        problemDetailBase.setDetail(message);

        return problemDetailBase;
    }
}

