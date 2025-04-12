package com.corncrakeconsulting.common.controller;

import com.corncrakeconsulting.common.dto.ResponseDto;
import com.corncrakeconsulting.common.validation.ValidMobileNumber;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public abstract class BaseController<T, S> {

    protected final S service;

    protected BaseController(S service) {
        this.service = service;
    }

    protected ResponseEntity<ResponseDto> buildResponse(boolean success, String successCode, String successMsg,
                                                        String failureCode, String failureMsg) {
        if (success) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(successCode, successMsg));
        } else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto(failureCode, failureMsg));
        }
    }

    // Abstract methods for subclasses to implement with their own mappings
    public abstract ResponseEntity<ResponseDto> create(@Valid @RequestBody T dto);

    public abstract ResponseEntity<T> fetch(@RequestParam @ValidMobileNumber String identifier);

    public abstract ResponseEntity<ResponseDto> update(@Valid @RequestBody T dto);

    public abstract ResponseEntity<ResponseDto> delete(@RequestParam @ValidMobileNumber String identifier);
}