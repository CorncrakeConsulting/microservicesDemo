package com.corncrakeconsulting.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(
        name = "Accounts",
        description = "Account details of a customer"
)
public class AccountsDto {

    @NotNull(message = "Account Number cannot be null")
    @Schema(
            description = "Account Number of the bank account",
            example = "3454433243"
    )
    private Long accountNumber;

    @NotEmpty(message = "Account Type cannot be null or empty")
    @Schema(
            description = "Type of bank account",
            example = "Savings"
    )
    private String accountType;

    @NotEmpty(message = "Branch Address cannot be null or empty")
    @Schema(
            description = "Bank branch address",
            example = "123 Las Vegas, Nevada"
    )
    private String branchAddress;
}