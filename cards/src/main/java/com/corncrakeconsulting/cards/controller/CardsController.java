package com.corncrakeconsulting.cards.controller;

import com.corncrakeconsulting.cards.constants.CardsConstants;
import com.corncrakeconsulting.cards.dto.CardsDto;
import com.corncrakeconsulting.cards.service.ICardsService;
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
import org.springframework.web.bind.annotation.*;

@Tag(name = "CRUD REST APIs for Cards in CrakeBank", description = "CRUD REST APIs in CrakeBank to CREATE, UPDATE, FETCH AND DELETE card details")
@RequestMapping(path = "/api")
public class CardsController extends BaseController<CardsDto, ICardsService> {

    public CardsController(ICardsService iCardsService) {
        super(iCardsService);
    }

    @Override
    @Operation(summary = "Create Card REST API", description = "REST API to create new Card")
    @ApiResponse(responseCode = "201", description = "HTTP Status CREATED")
    @CommonApiResponses
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> create(@Valid @RequestBody CardsDto dto) {
        service.createCard(dto.getMobileNumber());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(CardsConstants.STATUS_201, CardsConstants.MESSAGE_201));
    }

    @Override
    @Operation(summary = "Fetch Card Details REST API", description = "REST API to fetch card details")
    @CommonApiResponses
    @GetMapping("/fetch")
    public ResponseEntity<CardsDto> fetch(@RequestParam @ValidMobileNumber String mobileNumber) {
        CardsDto cardsDto = service.fetchCard(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(cardsDto);
    }

    @Override
    @Operation(summary = "Update Card Details REST API", description = "REST API to update card details")
    @ApiResponse(responseCode = "417", description = "Expectation Failed")
    @CommonApiResponses
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> update(@Valid @RequestBody CardsDto cardsDto) {
        boolean isUpdated = service.updateCard(cardsDto);
        return buildResponse(isUpdated, CardsConstants.STATUS_200, CardsConstants.MESSAGE_200,
                CardsConstants.STATUS_417, CardsConstants.MESSAGE_417_UPDATE);
    }

    @Override
    @Operation(summary = "Delete Card Details REST API", description = "REST API to delete card details")
    @ApiResponse(responseCode = "417", description = "Expectation Failed")
    @CommonApiResponses
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> delete(@RequestParam @ValidMobileNumber String mobileNumber) {
        boolean isDeleted = service.deleteCard(mobileNumber);
        return buildResponse(isDeleted, CardsConstants.STATUS_200, CardsConstants.MESSAGE_200,
                CardsConstants.STATUS_417, CardsConstants.MESSAGE_417_DELETE);
    }
}