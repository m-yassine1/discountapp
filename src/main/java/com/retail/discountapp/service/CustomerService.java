package com.retail.discountapp.service;

import com.retail.discountapp.domain.Item;

import java.math.BigDecimal;
import java.util.List;

public interface CustomerService {
    BigDecimal checkCustomerDiscountsBeforeCheckout(long customerId, List<Item> shoppingCardItems);
}
