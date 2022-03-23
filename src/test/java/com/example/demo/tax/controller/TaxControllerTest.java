package com.example.demo.tax.controller;

import com.example.demo.BaseTest;
import com.example.demo.prd.enums.Category;
import com.example.demo.tax.dto.TaxSaveRequestDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@SpringBootTest()
@AutoConfigureMockMvc
class TaxControllerTest extends BaseTest {
    private static final String BASE_PATH = "/api/v1/taxes";

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;



    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        this.objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    }

    @Test
    void save() throws Exception {
        TaxSaveRequestDto taxSaveRequestDto=new TaxSaveRequestDto();
        taxSaveRequestDto.setTaxrate(10);
        taxSaveRequestDto.setCategory(Category.FOOD);
        String content = objectMapper.writeValueAsString(taxSaveRequestDto);
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.post(BASE_PATH).content(content).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);
    }

    @Test
    void findByCategory() throws Exception{
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get(BASE_PATH + "/categories?category=FOOD").content("").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);
    }

    @Test
    void updateTaxRate() throws Exception {
        MvcResult result = mockMvc.perform(
                patch(BASE_PATH + "/?category=FOOD&taxrate=10").content("").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);

    }
}