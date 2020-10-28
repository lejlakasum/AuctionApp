package com.example.auctionapp.customValidator;

import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ProductVerifierValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ProductVerifier {
    String message() default "Bid is not valid";
    Class<?>[] groups() default {};
    Class<?>[] payload() default {};
}
