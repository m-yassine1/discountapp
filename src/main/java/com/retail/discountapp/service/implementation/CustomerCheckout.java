package com.retail.discountapp.service.implementation;

import com.retail.discountapp.domain.Customer;
import com.retail.discountapp.domain.Item;
import com.retail.discountapp.repository.CustomerRepository;
import com.retail.discountapp.service.CustomerService;
import com.retail.discountapp.service.DiscountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Component
public class CustomerCheckout implements CustomerService {

    private final CustomerRepository customerRepository;
    private final DiscountService discountService;
    private Logger logger = LoggerFactory.getLogger(CustomerCheckout.class);

    @Autowired
    public CustomerCheckout(CustomerRepository customerRepository,
                            DiscountService discountService) {
        this.customerRepository = customerRepository;
        this.discountService = discountService;
    }

    @Override
    public BigDecimal checkCustomerDiscountsBeforeCheckout(long customerId, List<Item> shoppingCardItems) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (!customer.isPresent()) {
            logger.info("Customer with Id {} does not exists, Check for discount canceled.", customerId);
            return BigDecimal.valueOf(0);
        }
        logger.info("Customer with Id {} was found, Starting discount calculation.", customerId);
        BigDecimal discount = discountService.calculateDiscount(customer.get(), shoppingCardItems);
        logger.info("Customer with Id {} received a total of {} discount.", discount);
        return discount;
    }
}
