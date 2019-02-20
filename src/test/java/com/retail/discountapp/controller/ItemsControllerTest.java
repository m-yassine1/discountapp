package com.retail.discountapp.controller;

import com.retail.discountapp.model.ItemDto;
import com.retail.discountapp.service.implementation.ItemsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
public class ItemsControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ItemsService itemsService;

    @Before
    public void setUp() throws Exception {
        this.mockMvc = standaloneSetup(new ItemsController(itemsService))
                .alwaysExpect(status().isOk()).build();
    }

    @Test
    public void getItems() throws Exception {
        List<ItemDto> itemDtoList = new ArrayList<>(2);
        itemDtoList.add(new ItemDto());
        itemDtoList.add(new ItemDto());

        when(itemsService.findAll()).thenReturn(itemDtoList);
        this.mockMvc.perform(get("/items"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(2)));
    }
}