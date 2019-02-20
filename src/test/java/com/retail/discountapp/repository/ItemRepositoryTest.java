package com.retail.discountapp.repository;

import com.retail.discountapp.domain.Item;
import com.retail.discountapp.domain.enums.ItemType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ItemRepositoryTest {
    @Autowired
    private ItemsRepository itemsRepository;

    @Test
    public void saveTest() {
        List<Item> items = new ArrayList<>();
        items.add(new Item("Item 1", BigDecimal.TEN, ItemType.ANY_OTHER_TYPE_OF_GOODS));
        items.add(new Item("Item 2", BigDecimal.ONE, ItemType.ELECTRONICS));
        itemsRepository.saveAll(items);

        List<Item> itemsInDb = itemsRepository.findAll();
        assertEquals(2, itemsInDb.size());
        assertEquals("Item price must match inserted item price", BigDecimal.TEN, itemsInDb.get(0).getItemPrice());
        assertEquals("Item type must match inserted item type", ItemType.ANY_OTHER_TYPE_OF_GOODS, itemsInDb.get(0).getItemType());
        assertEquals("Item name must match inserted item name", "Item 1", itemsInDb.get(0).getItemName());
    }
}