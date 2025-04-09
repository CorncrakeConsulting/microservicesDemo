package com.corncrakeconsulting.common.controller;

import com.corncrakeconsulting.common.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public abstract class BaseController<T, S> {

    protected final S service;

    protected BaseController(S service) {
        this.service = service;
    }

    protected ResponseEntity<ResponseDto> buildResponse(boolean success, String successCode, String successMsg, String failureCode, String failureMsg) {
        if (success) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(successCode, successMsg));
        } else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto(failureCode, failureMsg));
        }
    }

    // Abstract methods for specific implementations
    public abstract ResponseEntity<ResponseDto> create(T dto);
    public abstract ResponseEntity<T> fetch(String identifier);
    public abstract ResponseEntity<ResponseDto> update(T dto);
    public abstract ResponseEntity<ResponseDto> delete(String identifier);
}