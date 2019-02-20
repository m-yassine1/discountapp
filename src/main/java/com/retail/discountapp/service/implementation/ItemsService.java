package com.retail.discountapp.service.implementation;

import com.retail.discountapp.model.ItemDto;
import com.retail.discountapp.repository.ItemsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemsService {
    private final ItemsRepository itemsRepository;

    public ItemsService(ItemsRepository itemsRepository) {
        this.itemsRepository = itemsRepository;
    }

    public List<ItemDto> findAll() {
        return itemsRepository.findAll()
                .stream()
                .map(item -> new ItemDto(item.getId(), item.getItemName(), item.getItemPrice(), item.getItemType()))
                .collect(Collectors.toList());
    }
}
