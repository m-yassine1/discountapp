package com.retail.discountapp.service.implementation;

import com.retail.discountapp.domain.Customer;
import com.retail.discountapp.domain.Item;
import com.retail.discountapp.domain.enums.DiscountTypes;
import com.retail.discountapp.domain.enums.ItemType;
import com.retail.discountapp.repository.CustomerRepository;
import com.retail.discountapp.service.DiscountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(MockitoJUnitRunner.class)
public class CustomerCheckoutTest {

    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private DiscountService discountService;

    @InjectMocks
    private CustomerCheckout customerCheckout;

    @Test
    public void checkCustomerDiscountsBeforeCheckoutWithoutFindingCustomer() {
        long customerId = 1L;
        List<Item> shoppingCardList = new ArrayList<>(2);
        shoppingCardList.add(new Item("G-1", BigDecimal.valueOf(100.23), ItemType.GROCERY));
        shoppingCardList.add(new Item("G-2", BigDecimal.valueOf(33.28), ItemType.ANYTYPEOFGOODS));
        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());
        customerCheckout.checkCustomerDiscountsBeforeCheckout(customerId, shoppingCardList);
        verify(customerRepository, times(1)).findById(customerId);
        verify(discountService, times(0)).calculateDiscount(any(), any());
    }

    @Test
    public void checkCustomerDiscountsBeforeCheckoutWithFindingCustomer() {
        long customerId = 1L;
        List<Item> shoppingCardList = new ArrayList<>(2);
        shoppingCardList.add(new Item("G-1", BigDecimal.valueOf(100.23), ItemType.GROCERY));
        shoppingCardList.add(new Item("G-2", BigDecimal.valueOf(33.28), ItemType.ANYTYPEOFGOODS));
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(new Customer("G", DiscountTypes.CUSTOMER)));
        customerCheckout.checkCustomerDiscountsBeforeCheckout(customerId, shoppingCardList);
        verify(customerRepository, times(1)).findById(customerId);
        verify(discountService, times(1)).calculateDiscount(any(Customer.class), anyList());
    }
}