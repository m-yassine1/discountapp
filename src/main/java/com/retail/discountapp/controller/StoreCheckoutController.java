package com.retail.discountapp.controller;

import com.retail.discountapp.domain.Item;
import com.retail.discountapp.model.CheckoutResponse;
import com.retail.discountapp.repository.ItemsRepository;
import com.retail.discountapp.service.implementation.CustomerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@ApiOperation(value = "Shopping card checkout")
public class StoreCheckoutController {
    private final CustomerService customerService;
    private final ItemsRepository itemsRepository;

    public StoreCheckoutController(CustomerService customerService,
                                   ItemsRepository itemsRepository) {
        this.customerService = customerService;
        this.itemsRepository = itemsRepository;
    }

    @GetMapping(value = "/checkout/discount/customer/{customerId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Retrieve discount for customer, customerId 1 = Employee, 2 = Affiliate, 3 = Customer, 4 = Over 100$ bill Customer ")
    public CheckoutResponse checkCustomerDiscountsBeforeCheckout(@PathVariable long customerId) {
        //In Real the customer ID would be represented as the logged in user token or this API will be a backend API.
        return new CheckoutResponse(customerService.checkCustomerDiscountsBeforeCheckout(customerId, GetMockedShoppingCard()));
    }

    //This should be a list of data retrieved from frontend systems of the client shopping card
    private List<Item> GetMockedShoppingCard() {
        return itemsRepository.findAll();
    }
}
