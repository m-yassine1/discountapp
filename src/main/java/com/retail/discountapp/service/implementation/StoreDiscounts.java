package com.retail.discountapp.service.implementation;

import com.retail.discountapp.domain.Customer;
import com.retail.discountapp.domain.Item;
import com.retail.discountapp.domain.enums.ItemType;
import com.retail.discountapp.service.DiscountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class StoreDiscounts implements DiscountService {
    private static final BigDecimal HUNDRED = BigDecimal.valueOf(100);
    private static final BigDecimal EMPLOYEE_DISCOUNT = BigDecimal.valueOf(30);
    private static final BigDecimal AFFILIATE_DISCOUNT = BigDecimal.valueOf(10);
    private static final BigDecimal CUSTOMER_OVER_TWO_YEARS = BigDecimal.valueOf(5);
    private static final BigDecimal SPENDING_OVER_100 = BigDecimal.valueOf(5);
    private Logger logger = LoggerFactory.getLogger(StoreDiscounts.class);

    @Override
    public BigDecimal calculateDiscount(Customer customer, List<Item> shoppingCardItems) {

        final BigDecimal totalPurchaseAmount = calculateDiscountableItems(shoppingCardItems);
        if (totalPurchaseAmount.equals(BigDecimal.ZERO)) {
            logger.info("Total discountable items is 0");
            return totalPurchaseAmount;
        }

        switch (customer.getDiscountTypes()) {
            case EMPLOYEE:
                return calculatePercentage(EMPLOYEE_DISCOUNT, totalPurchaseAmount);
            case AFFILIATE:
                return calculatePercentage(AFFILIATE_DISCOUNT, totalPurchaseAmount);
            default:
                return calculateCustomerDiscounts(customer, totalPurchaseAmount);
        }
    }

    private BigDecimal calculateCustomerDiscounts(Customer customer, BigDecimal totalPurchaseAmount) {
        if (customer.getStartDate().isBefore(LocalDateTime.now().minusYears(2))) {
            return calculatePercentage(CUSTOMER_OVER_TWO_YEARS, totalPurchaseAmount);
        }
        if (totalPurchaseAmount.compareTo(HUNDRED) > 0) {
            return totalPurchaseAmount.divide(HUNDRED, 0, RoundingMode.DOWN).multiply(SPENDING_OVER_100);
        }
        return BigDecimal.ZERO;
    }

    private BigDecimal calculateDiscountableItems(List<Item> purchasedItems) {
        return purchasedItems.stream()
                .filter(i -> i.getItemType() != ItemType.GROCERY)
                .map(Item::getItemPrice)
                .reduce(BigDecimal::add).orElse(BigDecimal.valueOf(0));
    }

    private BigDecimal calculatePercentage(BigDecimal obtained, BigDecimal total) {
        return total.multiply(obtained).divide(HUNDRED, 10, RoundingMode.FLOOR);
    }
}
