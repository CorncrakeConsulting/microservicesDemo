package com.corncrakeconsulting.cards.mapper;

import com.corncrakeconsulting.cards.dto.CardsDto;
import com.corncrakeconsulting.cards.entity.Cards;

public class CardsMapper {
    private CardsMapper() {
        // restrict instantiation
    }

    public static CardsDto mapToCardsDto(Cards cards, CardsDto cardsDto) {
        cardsDto.setCardNumber(cards.getCardNumber());
        cardsDto.setCardType(cards.getCardType());
        cardsDto.setMobileNumber(cards.getMobileNumber());
        cardsDto.setTotalLimit(cards.getTotalLimit());
        cardsDto.setAvailableAmount(cards.getAvailableAmount());
        cardsDto.setAmountUsed(cards.getAmountUsed());
        return cardsDto;
    }

    public static void mapToCards(com.corncrakeconsulting.cards.dto.CardsDto cardsDto, com.corncrakeconsulting.cards.entity.Cards cards) {
        cards.setCardNumber(cardsDto.getCardNumber());
        cards.setCardType(cardsDto.getCardType());
        cards.setMobileNumber(cardsDto.getMobileNumber());
        cards.setTotalLimit(cardsDto.getTotalLimit());
        cards.setAvailableAmount(cardsDto.getAvailableAmount());
        cards.setAmountUsed(cardsDto.getAmountUsed());
    }

}
