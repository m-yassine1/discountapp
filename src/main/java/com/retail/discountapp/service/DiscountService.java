package com.retail.discountapp.service;


import com.retail.discountapp.domain.Customer;
import com.retail.discountapp.domain.Item;

import java.math.BigDecimal;
import java.util.List;

public interface DiscountService {
    BigDecimal calculateDiscount(Customer customer, List<Item> shoppingCardItems);
}
