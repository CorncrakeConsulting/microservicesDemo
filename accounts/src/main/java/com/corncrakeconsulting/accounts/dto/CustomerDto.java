package com.corncrakeconsulting.accounts.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(name = "Customer", description = "This class contains the details of the customer")
public class CustomerDto {

    @Schema(
            description = "Customer ID",
            example = "1234567890"
    )
    private Long customerId;

    @Schema(
            description = "Customer Name",
            example = "John Doe"
    )
    @NotEmpty(message = "Customer Name is mandatory")
    @Size(min = 3, max = 30, message = "Customer Name should be between 3 and 30 characters")
    private String name;

    @Schema(
            description = "Customer Email",
            example = "johndoe@example.com"
    )
    @NotEmpty(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;

    @Schema(
            description = "Customer Mobile Number",
            example = "9876543210"
    )
    @NotEmpty(message = "Mobile Number is mandatory")
    @Pattern(
            regexp = "^\\d{10}$",
            message = "Mobile Number should be 10 digits"
    )
    private String mobileNumber;

    @Schema(
            description = "Customer's account details"
    )
    private AccountsDto accountsDto;
}