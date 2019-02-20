package com.retail.discountapp.config.data;

import com.retail.discountapp.domain.Customer;
import com.retail.discountapp.domain.enums.DiscountTypes;
import com.retail.discountapp.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
@Order(1)
public class CustomerDataLoader implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(CustomerDataLoader.class);
    private final CustomerRepository customerRepository;

    public CustomerDataLoader(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        logger.info("Loading Customer data to the database...");
        customerRepository.save(new Customer("Employee", DiscountTypes.EMPLOYEE));
        customerRepository.save(new Customer("Affiliate", DiscountTypes.AFFILIATE));
        Customer customer = new Customer("Customer More Than 2 years", DiscountTypes.CUSTOMER);
        customer.setStartDate(LocalDateTime.now().minusYears(3));
        customerRepository.save(customer);
        customerRepository.save(new Customer("Customer", DiscountTypes.CUSTOMER));
        logger.info("Loading Customers in the database");
    }
}
