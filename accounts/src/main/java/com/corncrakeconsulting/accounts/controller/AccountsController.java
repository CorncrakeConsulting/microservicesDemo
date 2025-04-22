package com.corncrakeconsulting.accounts.controller;

import com.corncrakeconsulting.accounts.constants.AccountsConstants;
import com.corncrakeconsulting.accounts.dto.CustomerDto;
import com.corncrakeconsulting.accounts.service.IAccountsService;
import com.corncrakeconsulting.common.annotations.CommonApiResponses;
import com.corncrakeconsulting.common.controller.BaseController;
import com.corncrakeconsulting.common.dto.ResponseDto;
import com.corncrakeconsulting.common.validation.ValidMobileNumber;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "CRUD REST APIs for Accounts in Bank", description = "CRUD REST APIs in Bank to CREATE, UPDATE, FETCH AND DELETE account details")
@RequestMapping(path = "/api/accounts")
@RestController
public class AccountsController extends BaseController<CustomerDto, IAccountsService> {

    public AccountsController(IAccountsService iAccountsService) {
        super(iAccountsService);
    }

    @Override
    @Operation(summary = "Create Account REST API", description = "REST API to create new Customer & Account")
    @ApiResponse(responseCode = "201", description = "HTTP Status CREATED")
    @CommonApiResponses
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> create(@Valid @RequestBody CustomerDto customerDto) {
        String accountNumber = service.createAccount(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201 + " " + accountNumber));
    }

    @Override
    @Operation(summary = "Fetch Account Details REST API", description = "REST API to fetch Customer & Account details")
    @CommonApiResponses
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetch(@RequestParam @ValidMobileNumber String mobileNumber) {
        CustomerDto customerDto = service.fetchAccount(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }

    @Override
    @Operation(summary = "Update Account Details REST API", description = "REST API to update Customer & Account details")
    @ApiResponse(responseCode = "417", description = "Expectation Failed")
    @CommonApiResponses
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> update(@Valid @RequestBody CustomerDto customerDto) {
        boolean isUpdated = service.updateAccount(customerDto);
        return buildResponse(isUpdated, AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200, AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_UPDATE);
    }

    @Override
    @Operation(summary = "Delete Account & Customer Details REST API", description = "REST API to delete Customer & Account details")
    @ApiResponse(responseCode = "417", description = "Expectation Failed")
    @CommonApiResponses
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> delete(@RequestParam @ValidMobileNumber String mobileNumber) {
        boolean isDeleted = service.deleteAccount(mobileNumber);
        return buildResponse(isDeleted, AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200, AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_DELETE);
    }
}