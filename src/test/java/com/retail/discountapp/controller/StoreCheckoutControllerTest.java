package com.retail.discountapp.controller;

import com.retail.discountapp.repository.ItemsRepository;
import com.retail.discountapp.service.implementation.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
public class StoreCheckoutControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CustomerService customerService;

    @Mock
    private ItemsRepository itemsRepository;

    @Before
    public void setUp() {
        this.mockMvc = standaloneSetup(new StoreCheckoutController(customerService, itemsRepository))
                .alwaysExpect(status().isOk()).build();
    }

    @Test
    public void checkCustomerDiscountsBeforeCheckout() throws Exception {
        when(customerService.checkCustomerDiscountsBeforeCheckout(anyLong(), anyList())).thenReturn(BigDecimal.valueOf(50));
        this.mockMvc.perform(get("/checkout/discount/customer/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.totalDiscount", is(50)));
    }
}