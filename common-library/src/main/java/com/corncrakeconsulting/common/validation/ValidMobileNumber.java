package com.corncrakeconsulting.common.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Pattern;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Pattern(regexp = "(^$|\\d{10})", message = "Mobile number must be 10 digits")
@Target({PARAMETER})
@Retention(RUNTIME)
@Constraint(validatedBy = {})
public @interface ValidMobileNumber {
    String message() default "Mobile number must be 10 digits";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}