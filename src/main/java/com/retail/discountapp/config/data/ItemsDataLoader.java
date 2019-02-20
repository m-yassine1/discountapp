package com.retail.discountapp.config.data;

import com.retail.discountapp.domain.Item;
import com.retail.discountapp.domain.enums.ItemType;
import com.retail.discountapp.repository.ItemsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Order(2)
public class ItemsDataLoader implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(ItemsDataLoader.class);

    private final ItemsRepository itemsRepository;

    public ItemsDataLoader(ItemsRepository itemsRepository) {
        this.itemsRepository = itemsRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("Loading initial data to the database...");
        itemsRepository.save(new Item("Item 1", BigDecimal.TEN, ItemType.ANY_OTHER_TYPE_OF_GOODS));
        itemsRepository.save(new Item("Item 2", BigDecimal.ONE, ItemType.GROCERY));
        itemsRepository.save(new Item("Item 3", BigDecimal.valueOf(400), ItemType.ANY_OTHER_TYPE_OF_GOODS));
        itemsRepository.save(new Item("Item 4", BigDecimal.valueOf(580), ItemType.ELECTRONICS));
        logger.info("Loading Items in the database");
    }
}
