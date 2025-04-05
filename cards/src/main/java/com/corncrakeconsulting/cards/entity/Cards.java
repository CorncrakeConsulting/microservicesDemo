package com.corncrakeconsulting.cards.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Cards extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cardId;

	private String mobileNumber;

	private String cardNumber;

	private String cardType;

	private int totalLimit;

	private int amountUsed;

	private int availableAmount;

	// Constructor to easily create a Cards object from CardsDto
	public Cards(String cardNumber, String cardType, String mobileNumber,
				 int totalLimit, int availableAmount, int amountUsed) {
		this.cardNumber = cardNumber;
		this.cardType = cardType;
		this.mobileNumber = mobileNumber;
		this.totalLimit = totalLimit;
		this.availableAmount = availableAmount;
		this.amountUsed = amountUsed;
	}
	
}
