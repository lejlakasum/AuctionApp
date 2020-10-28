package com.example.auctionapp.customValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = BidVerifierValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface BidVerifier {
    String message() default "Bid is not valid";
    Class<?>[] groups() default {};
    Class<?>[] payload() default {};

    String productId();
    String bidTime();
    String bidAmount();

    @Target({ ElementType.TYPE })
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
        BidVerifier[] value();
    }
}
