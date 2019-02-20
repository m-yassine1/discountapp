package com.retail.discountapp.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class HomeControllerTest {
    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = standaloneSetup(new HomeController())
                .alwaysExpect(status().isFound()).build();
    }

    @Test
    public void testHomePageRedirection() throws Exception {
        this.mockMvc.perform(get("/"))
                .andExpect(redirectedUrl("/swagger-ui.html"));
    }
}