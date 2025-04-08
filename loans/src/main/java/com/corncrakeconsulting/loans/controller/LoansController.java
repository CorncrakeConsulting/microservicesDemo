package com.corncrakeconsulting.loans.controller;

import com.corncrakeconsulting.common.dto.ErrorResponseDto;
import com.corncrakeconsulting.loans.constants.LoansConstants;
import com.corncrakeconsulting.loans.dto.LoansDto;
import com.corncrakeconsulting.loans.dto.ResponseDto;
import com.corncrakeconsulting.loans.service.ILoansService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Matt Clarson
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@ApiResponse(responseCode = "200", description = "HTTP Status OK")
@ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error",
        content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
@interface CommonApiResponses {
}

@Tag(name = "CRUD REST APIs for Loans in CrakeBank",
        description = "CRUD REST APIs in CrakeBank to CREATE, UPDATE, FETCH AND DELETE loan details")
@RestController
@RequestMapping(path = "/api/loans", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class LoansController {

    private final ILoansService iLoansService;

    @Operation(summary = "Create Loan REST API", description = "REST API to create new loan inside CrakeBank")
    @ApiResponse(responseCode = "201", description = "HTTP Status CREATED")
    @CommonApiResponses
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createLoan(
            @RequestParam
            @Pattern(regexp = "(^$|\\d{10})", message = "Mobile number must be 10 digits")
            @Length(min = 10, max = 10, message = "Mobile number must be exactly 10 digits")
            String mobileNumber) {
        iLoansService.createLoan(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(LoansConstants.STATUS_201, LoansConstants.MESSAGE_201));
    }

    @Operation(summary = "Fetch Loan Details REST API",
            description = "REST API to fetch loan details based on a mobile number")
    @CommonApiResponses
    @GetMapping("/fetch")
    public ResponseEntity<LoansDto> fetchLoanDetails(
            @RequestParam
            @Pattern(regexp = "(^$|\\d{10})", message = "Mobile number must be 10 digits")
            @Length(min = 10, max = 10, message = "Mobile number must be exactly 10 digits")
            String mobileNumber) {
        LoansDto loansDto = iLoansService.fetchLoan(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(loansDto);
    }

    @Operation(summary = "Update Loan Details REST API",
            description = "REST API to update loan details based on a loan number")
    @CommonApiResponses
    @ApiResponse(responseCode = "417", description = "Expectation Failed")
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateLoanDetails(
            @Valid @RequestBody LoansDto loansDto) {
        boolean isUpdated = iLoansService.updateLoan(loansDto);
        if (isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(LoansConstants.STATUS_200, LoansConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(LoansConstants.STATUS_417, LoansConstants.MESSAGE_417_UPDATE));
        }
    }

    @Operation(summary = "Delete Loan Details REST API",
            description = "REST API to delete Loan details based on a mobile number")
    @CommonApiResponses
    @ApiResponse(responseCode = "417", description = "Expectation Failed")
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteLoanDetails(
            @RequestParam
            @Pattern(regexp = "(^$|\\d{10})", message = "Mobile number must be 10 digits")
            @Length(min = 10, max = 10, message = "Mobile number must be exactly 10 digits")
            String mobileNumber) {
        boolean isDeleted = iLoansService.deleteLoan(mobileNumber);
        if (isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(LoansConstants.STATUS_200, LoansConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(LoansConstants.STATUS_417, LoansConstants.MESSAGE_417_DELETE));
        }
    }
}