package com.retail.discountapp.service.implementation;

import com.retail.discountapp.domain.Item;
import com.retail.discountapp.domain.enums.ItemType;
import com.retail.discountapp.model.ItemDto;
import com.retail.discountapp.repository.ItemsRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ItemsServiceTest {

    @Mock
    private ItemsRepository itemsRepository;

    @InjectMocks
    private ItemsService itemsService;

    @Test
    public void findAll() {
        List<Item> itemList = new ArrayList<>(2);
        itemList.add(new Item("Test 1", BigDecimal.TEN, ItemType.GROCERY));
        itemList.add(new Item("Test 2", BigDecimal.ONE, ItemType.ELECTRONICS));
        when(itemsRepository.findAll()).thenReturn(itemList);
        List<ItemDto> listItems = itemsService.findAll();
        assertEquals(2, listItems.size());
        assertEquals("Test 1", listItems.get(0).getItemName());
        assertEquals(BigDecimal.TEN, listItems.get(0).getItemPrice());
        assertEquals(ItemType.GROCERY, listItems.get(0).getItemType());

    }
}