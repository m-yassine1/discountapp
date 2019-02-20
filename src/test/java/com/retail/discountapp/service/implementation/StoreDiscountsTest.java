package com.retail.discountapp.service.implementation;

import com.retail.discountapp.domain.Customer;
import com.retail.discountapp.domain.Item;
import com.retail.discountapp.domain.enums.DiscountTypes;
import com.retail.discountapp.domain.enums.ItemType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(MockitoJUnitRunner.class)
public class StoreDiscountsTest {

    @Mock
    Customer mockCustomer;

    private List<Item> shoppingCardList;

    @InjectMocks
    private StoreDiscounts storeDiscounts;

    @Before
    public void setUp() {
        this.shoppingCardList = new ArrayList<>(4);
        shoppingCardList.add(new Item("G-1", BigDecimal.valueOf(100.23), ItemType.GROCERY));
        shoppingCardList.add(new Item("G-2", BigDecimal.valueOf(33.28), ItemType.ANY_OTHER_TYPE_OF_GOODS));
        shoppingCardList.add(new Item("G-3", BigDecimal.valueOf(50.23), ItemType.GROCERY));
        shoppingCardList.add(new Item("G-4", BigDecimal.valueOf(200.45), ItemType.ANY_OTHER_TYPE_OF_GOODS));
    }

    @Test
    public void calculateDiscountForEmployee() {
        when(mockCustomer.getDiscountTypes()).thenReturn(DiscountTypes.EMPLOYEE);
        BigDecimal discountValue = this.storeDiscounts.calculateDiscount(mockCustomer, shoppingCardList);
        Assert.assertEquals(BigDecimal.valueOf(70.119).stripTrailingZeros(), discountValue.stripTrailingZeros());
    }

    @Test
    public void calculateDiscountForAffiliate() {
        when(mockCustomer.getDiscountTypes()).thenReturn(DiscountTypes.AFFILIATE);
        BigDecimal discountValue = this.storeDiscounts.calculateDiscount(mockCustomer, shoppingCardList);
        Assert.assertEquals(BigDecimal.valueOf(23.373).stripTrailingZeros(), discountValue.stripTrailingZeros());
    }

    @Test
    public void calculateDiscountForCustomerThatJoinedMoreThan2YearsAgo() {
        when(mockCustomer.getDiscountTypes()).thenReturn(DiscountTypes.CUSTOMER);
        when(mockCustomer.getStartDate()).thenReturn(LocalDateTime.now().minusYears(3));
        BigDecimal discountValue = this.storeDiscounts.calculateDiscount(mockCustomer, shoppingCardList);
        Assert.assertEquals(BigDecimal.valueOf(11.6865).stripTrailingZeros(), discountValue.stripTrailingZeros());
    }

    @Test
    public void calculateDiscountForLessThan100AndNewCustomer() {
        when(mockCustomer.getDiscountTypes()).thenReturn(DiscountTypes.CUSTOMER);
        when(mockCustomer.getStartDate()).thenReturn(LocalDateTime.now().minusDays(1));
        List<Item> shoppingCardList = new ArrayList<>(2);
        shoppingCardList.add(new Item("G-1", BigDecimal.valueOf(100.23), ItemType.GROCERY));
        shoppingCardList.add(new Item("G-2", BigDecimal.valueOf(33.28), ItemType.ANY_OTHER_TYPE_OF_GOODS));
        BigDecimal discountValue = this.storeDiscounts.calculateDiscount(mockCustomer, shoppingCardList);
        Assert.assertEquals(BigDecimal.ZERO, discountValue);
    }

    @Test
    public void calculateDiscountForJustGrocery() {
        List<Item> shoppingCardList = new ArrayList<>(2);
        shoppingCardList.add(new Item("G-1", BigDecimal.valueOf(100.23), ItemType.GROCERY));
        shoppingCardList.add(new Item("G-2", BigDecimal.valueOf(33.28), ItemType.GROCERY));
        BigDecimal discountValue = this.storeDiscounts.calculateDiscount(mockCustomer, shoppingCardList);
        Assert.assertEquals(BigDecimal.ZERO, discountValue);
        verify(mockCustomer, times(0)).getDiscountTypes();
    }

    @Test
    public void calculateDiscountForLessThan100() {
        when(mockCustomer.getDiscountTypes()).thenReturn(DiscountTypes.CUSTOMER);
        when(mockCustomer.getStartDate()).thenReturn(LocalDateTime.now().minusDays(1));
        List<Item> shoppingCardList = new ArrayList<>(1);
        shoppingCardList.add(new Item("G-2", BigDecimal.valueOf(33.28), ItemType.ANY_OTHER_TYPE_OF_GOODS));
        BigDecimal discountValue = this.storeDiscounts.calculateDiscount(mockCustomer, shoppingCardList);
        Assert.assertEquals(BigDecimal.ZERO, discountValue);
    }

    @Test
    public void calculateDiscountForNewCustomerWithHighAmount() {
        when(mockCustomer.getDiscountTypes()).thenReturn(DiscountTypes.CUSTOMER);
        when(mockCustomer.getStartDate()).thenReturn(LocalDateTime.now().minusDays(1));
        List<Item> shoppingCardList = new ArrayList<>(1);
        shoppingCardList.add(new Item("G-2", BigDecimal.valueOf(990), ItemType.ANY_OTHER_TYPE_OF_GOODS));
        BigDecimal discountValue = this.storeDiscounts.calculateDiscount(mockCustomer, shoppingCardList);
        Assert.assertEquals(BigDecimal.valueOf(45).stripTrailingZeros(), discountValue.stripTrailingZeros());
    }
}