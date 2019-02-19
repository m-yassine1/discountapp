package com.retail.discountapp.service.implementation;

import com.retail.discountapp.domain.Customer;
import com.retail.discountapp.domain.Item;
import com.retail.discountapp.domain.enums.DiscountTypes;
import com.retail.discountapp.domain.enums.ItemType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StoreDiscountsTest {

    @MockBean
    Customer mockCustomer;

    private List<Item> shoppingCardList;

    @Autowired
    private StoreDiscounts storeDiscounts;

    @Before
    public void setUp() {
        this.shoppingCardList = new ArrayList<>(4);
        shoppingCardList.add(new Item("G-1", BigDecimal.valueOf(100.23), ItemType.GROCERY));
        shoppingCardList.add(new Item("G-2", BigDecimal.valueOf(33.28), ItemType.ANYTYPEOFGOODS));
        shoppingCardList.add(new Item("G-3", BigDecimal.valueOf(50.23), ItemType.GROCERY));
        shoppingCardList.add(new Item("G-4", BigDecimal.valueOf(200.45), ItemType.ANYTYPEOFGOODS));
    }

    @Test
    public void calculateDiscountForEmployee() {
        when(mockCustomer.getDiscountTypes()).thenReturn(DiscountTypes.EMPLOYEE);
        BigDecimal discountValue = this.storeDiscounts.calculateDiscount(mockCustomer, shoppingCardList);
        Assert.assertEquals(BigDecimal.valueOf(70.11), discountValue);
    }

    @Test
    public void calculateDiscountForAffiliate() {
        when(mockCustomer.getDiscountTypes()).thenReturn(DiscountTypes.AFFILIATE);
        BigDecimal discountValue = this.storeDiscounts.calculateDiscount(mockCustomer, shoppingCardList);
        Assert.assertEquals(BigDecimal.valueOf(23.37), discountValue);
    }

    @Test
    public void calculateDiscountForCustomerThatJoinedMoreThan2YearsAgo() {
        when(mockCustomer.getDiscountTypes()).thenReturn(DiscountTypes.CUSTOMER);
        when(mockCustomer.getStartDate()).thenReturn(LocalDateTime.now().minusYears(3));
        BigDecimal discountValue = this.storeDiscounts.calculateDiscount(mockCustomer, shoppingCardList);
        Assert.assertEquals(BigDecimal.valueOf(11.68), discountValue);
    }

    @Test
    public void calculateDiscountForEvery100Spent() {
        when(mockCustomer.getDiscountTypes()).thenReturn(DiscountTypes.CUSTOMER);
        when(mockCustomer.getStartDate()).thenReturn(LocalDateTime.now().minusDays(1));
        BigDecimal discountValue = this.storeDiscounts.calculateDiscount(mockCustomer, shoppingCardList);
        Assert.assertEquals(BigDecimal.valueOf(10), discountValue);
    }

    @Test
    public void calculateDiscountForLessThan100AndNewCustomer() {
        when(mockCustomer.getDiscountTypes()).thenReturn(DiscountTypes.CUSTOMER);
        when(mockCustomer.getStartDate()).thenReturn(LocalDateTime.now().minusDays(1));
        List<Item> shoppingCardList = new ArrayList<>(2);
        shoppingCardList.add(new Item("G-1", BigDecimal.valueOf(100.23), ItemType.GROCERY));
        shoppingCardList.add(new Item("G-2", BigDecimal.valueOf(33.28), ItemType.ANYTYPEOFGOODS));
        BigDecimal discountValue = this.storeDiscounts.calculateDiscount(mockCustomer, shoppingCardList);
        Assert.assertEquals(BigDecimal.valueOf(0), discountValue);
    }

    @Test
    public void calculateDiscountForJustGrocery() {
        when(mockCustomer.getDiscountTypes()).thenReturn(DiscountTypes.EMPLOYEE);
        List<Item> shoppingCardList = new ArrayList<>(2);
        shoppingCardList.add(new Item("G-1", BigDecimal.valueOf(100.23), ItemType.GROCERY));
        shoppingCardList.add(new Item("G-2", BigDecimal.valueOf(33.28), ItemType.GROCERY));
        BigDecimal discountValue = this.storeDiscounts.calculateDiscount(mockCustomer, shoppingCardList);
        Assert.assertEquals(BigDecimal.valueOf(0), discountValue);
    }

    @Test
    public void calculateDiscountForLessThan100() {
        when(mockCustomer.getDiscountTypes()).thenReturn(DiscountTypes.CUSTOMER);
        when(mockCustomer.getStartDate()).thenReturn(LocalDateTime.now().minusDays(1));
        List<Item> shoppingCardList = new ArrayList<>(1);
        shoppingCardList.add(new Item("G-2", BigDecimal.valueOf(33.28), ItemType.ANYTYPEOFGOODS));
        BigDecimal discountValue = this.storeDiscounts.calculateDiscount(mockCustomer, shoppingCardList);
        Assert.assertEquals(BigDecimal.valueOf(0), discountValue);
    }

    @Test
    public void calculateDiscountForNewCustomerWithHighAmount() {
        when(mockCustomer.getDiscountTypes()).thenReturn(DiscountTypes.CUSTOMER);
        when(mockCustomer.getStartDate()).thenReturn(LocalDateTime.now().minusDays(1));
        List<Item> shoppingCardList = new ArrayList<>(1);
        shoppingCardList.add(new Item("G-2", BigDecimal.valueOf(990), ItemType.ANYTYPEOFGOODS));
        BigDecimal discountValue = this.storeDiscounts.calculateDiscount(mockCustomer, shoppingCardList);
        Assert.assertEquals(BigDecimal.valueOf(45), discountValue);
    }
}