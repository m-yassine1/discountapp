package com.retail.discountapp.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.retail.discountapp.domain.Customer;
import com.retail.discountapp.domain.enums.DiscountTypes;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerRepositoryTest {
    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void saveTest() {
        final String name = "Gabriel";
        Customer customer = new Customer(name, DiscountTypes.CUSTOMER);
        customerRepository.save(customer);
        Optional<Customer> insertedCustomer = customerRepository.findById(customer.getId());
        assertTrue(insertedCustomer.isPresent());
        assertEquals("Customer discount type must match inserted type", DiscountTypes.CUSTOMER, insertedCustomer.get().getDiscountTypes());
        assertEquals("Name must match", name, insertedCustomer.get().getFullName());
    }
}