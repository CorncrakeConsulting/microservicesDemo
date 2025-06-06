package com.corncrakeconsulting.common.annotations;

import com.corncrakeconsulting.common.dto.ErrorResponseDto;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@ApiResponse(responseCode = "200", description = "HTTP Status OK")
@ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error",
        content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
public @interface CommonApiResponses {
}