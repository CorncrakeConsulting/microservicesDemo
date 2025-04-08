package com.corncrakeconsulting.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Schema(
        name = "Error Response",
        description = "Standard error response structure for API errors"
)
public class ErrorResponseDto {

  @Schema(
          description = "The API endpoint where the error occurred",
          example = "/api/v1/accounts"
  )
  private String apiPath;

  @Schema(
          description = "HTTP status code representing the error",
          example = "400"
  )
  private HttpStatus errorCode;

  @Schema(
          description = "Detailed error message",
          example = "Invalid account number provided"
  )
  private String errorMessage;

  @Schema(
          description = "Timestamp when the error occurred",
          example = "2025-04-01T14:30:00"
  )
  private LocalDateTime errorTime;
}