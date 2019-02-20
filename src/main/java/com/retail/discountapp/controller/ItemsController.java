package com.retail.discountapp.controller;

import com.retail.discountapp.model.ItemDto;
import com.retail.discountapp.service.implementation.ItemsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@ApiOperation(value = "Item listing")
public class ItemsController {
    private final ItemsService itemsService;

    public ItemsController(ItemsService itemsService) {
        this.itemsService = itemsService;
    }

    @GetMapping(value = "/items", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "View a list of product items")
    public List<ItemDto> get() {
        return itemsService.findAll();
    }
}
