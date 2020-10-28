package com.example.auctionapp.customValidator;

import com.example.auctionapp.Util.TimeUtility;
import com.example.auctionapp.exception.BadRequestException;
import com.example.auctionapp.model.Bid;
import com.example.auctionapp.model.Product;
import com.example.auctionapp.repository.ProductRepository;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class BidVerifierValidator implements ConstraintValidator<BidVerifier, Object> {

    String productId;
    String bidTime;
    String bidAmount;
    private final ProductRepository productRepository;

    @Autowired
    public BidVerifierValidator(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void initialize(BidVerifier constraintAnnotation) {
        this.productId = constraintAnnotation.productId();
        this.bidTime = constraintAnnotation.bidTime();
        this.bidAmount = constraintAnnotation.bidAmount();
    }

    public boolean isValid(Object value,
                           ConstraintValidatorContext context) {

        try {
            Long productIdValue = Long.parseLong(new BeanWrapperImpl(value)
                    .getPropertyValue(productId).toString());
            Long bidTimeValue = Long.parseLong(new BeanWrapperImpl(value)
                    .getPropertyValue(bidTime).toString());
            Double bidAmountValue = Double.parseDouble(new BeanWrapperImpl(value)
                    .getPropertyValue(bidAmount).toString());

            Product product = productRepository.findById(productIdValue);
            Double highestBid = product.getBids().stream().mapToDouble(bid -> bid.getBidAmount()).max().orElse(0);

            if (bidAmountValue <= highestBid) {
                return false;
            }

            Long auctionStart = TimeUtility.LocalDateTimeToTimestamp(product.getAuctionStartDate());
            Long auctionEnd = TimeUtility.LocalDateTimeToTimestamp(product.getAuctionEndDate());

            if(bidTimeValue<=auctionStart || bidTimeValue>=auctionEnd) {
                return false;
            }
        }
        catch (Exception ex) {
            return false;
        }

        return true;
    }
}
