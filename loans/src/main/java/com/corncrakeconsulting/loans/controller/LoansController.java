package com.corncrakeconsulting.loans.controller;

import com.corncrakeconsulting.common.annotations.CommonApiResponses;
import com.corncrakeconsulting.common.controller.BaseController;
import com.corncrakeconsulting.common.dto.ResponseDto;
import com.corncrakeconsulting.common.validation.ValidMobileNumber;
import com.corncrakeconsulting.loans.constants.LoansConstants;
import com.corncrakeconsulting.loans.dto.LoansDto;
import com.corncrakeconsulting.loans.service.ILoansService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "CRUD REST APIs for Loans in CrakeBank", description = "CRUD REST APIs in CrakeBank to CREATE, UPDATE, FETCH AND DELETE loan details")
@RequestMapping(path = "/api/loans")
public class LoansController extends BaseController<LoansDto, ILoansService> {

    public LoansController(ILoansService iLoansService) {
        super(iLoansService);
    }

    @Override
    @Operation(summary = "Create Loan REST API", description = "REST API to create new loan")
    @ApiResponse(responseCode = "201", description = "HTTP Status CREATED")
    @CommonApiResponses
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> create(@Valid @RequestBody LoansDto dto) {
        service.createLoan(dto.getMobileNumber()); // Adjust ILoansService to handle LoansDto if needed
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(LoansConstants.STATUS_201, LoansConstants.MESSAGE_201));
    }
    @Override
    @Operation(summary = "Fetch Loan Details REST API", description = "REST API to fetch loan details")
    @CommonApiResponses
    @GetMapping("/fetch")
    public ResponseEntity<LoansDto> fetch(@RequestParam @ValidMobileNumber String mobileNumber) {
        LoansDto loansDto = service.fetchLoan(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(loansDto);
    }

    @Override
    @Operation(summary = "Update Loan Details REST API", description = "REST API to update loan details")
    @ApiResponse(responseCode = "417", description = "Expectation Failed")
    @CommonApiResponses
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> update(@Valid @RequestBody LoansDto loansDto) {
        boolean isUpdated = service.updateLoan(loansDto);
        return buildResponse(isUpdated, LoansConstants.STATUS_200, LoansConstants.MESSAGE_200,
                LoansConstants.STATUS_417, LoansConstants.MESSAGE_417_UPDATE);
    }

    @Override
    @Operation(summary = "Delete Loan Details REST API", description = "REST API to delete loan details")
    @ApiResponse(responseCode = "417", description = "Expectation Failed")
    @CommonApiResponses
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> delete(@RequestParam @ValidMobileNumber String mobileNumber) {
        boolean isDeleted = service.deleteLoan(mobileNumber);
        return buildResponse(isDeleted, LoansConstants.STATUS_200, LoansConstants.MESSAGE_200,
                LoansConstants.STATUS_417, LoansConstants.MESSAGE_417_DELETE);
    }
}