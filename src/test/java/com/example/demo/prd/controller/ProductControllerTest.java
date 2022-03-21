package com.example.demo.prd.controller;

import com.example.demo.BaseTest;
import com.example.demo.DemoApplication;
import com.example.demo.prd.dto.ProductSaveRequestDto;
import com.example.demo.prd.dto.ProductUpdateRequestDto;
import com.example.demo.prd.enums.Category;
import com.example.demo.prd.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.persistence.Enumerated;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@SpringBootTest()
@AutoConfigureMockMvc

class ProductControllerTest extends BaseTest {
    private static final String BASE_PATH = "/api/v1/products";

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;



    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        this.objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    }


    @Test
    void findAll() throws Exception {
        MvcResult result = mockMvc.perform(
                get(BASE_PATH).content("").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);

    }

    @Test
    void findById() throws Exception{
        MvcResult result = mockMvc.perform(
                get(BASE_PATH + "/15").content("15L").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);
    }

    @Test
    void findByCategory() throws Exception{
        MvcResult result = mockMvc.perform(
                get(BASE_PATH + "/categories?category=FOOD").content("").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);
    }

    @Test
    void infoByCategory() throws Exception {
        MvcResult result = mockMvc.perform(
                get(BASE_PATH + "/categories/info?category=FOOD").content("").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);

    }
   /* {
        "productName": "havyar",
            "category": "FOOD",
            "noTaxPrice": 120,
            "taxPrice": 0,
            "lastPrice": 0
    }*/
    @Test
    void save() throws Exception {
        ProductSaveRequestDto productSaveRequestDto = ProductSaveRequestDto.builder()
                .productName("TEST")
                .category(Category.FOOD)
                .noTaxPrice(120)
                .lastPrice(0)
                .taxPrice(0)
                .build();


        String content = objectMapper.writeValueAsString(productSaveRequestDto);

        MvcResult result = mockMvc.perform(
                post(BASE_PATH).content(content).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);
    }

    @Test
    void update() throws Exception{

        ProductUpdateRequestDto productUpdateRequestDto=new ProductUpdateRequestDto();
        productUpdateRequestDto.setId(23L);
        productUpdateRequestDto.setProductName("Test");
        productUpdateRequestDto.setCategory(Category.FOOD);
        productUpdateRequestDto.setNoTaxPrice(1200);
        productUpdateRequestDto.setTaxPrice(0);
        productUpdateRequestDto.setLastPrice(0);
        String content = new ObjectMapper().writeValueAsString(productUpdateRequestDto);

        MvcResult result = mockMvc.perform(
                put(BASE_PATH).contentType(MediaType.APPLICATION_JSON).content(content)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);
    }

    @Test
    void updatePrice() throws Exception {
        MvcResult result = mockMvc.perform(
                patch(BASE_PATH + "/?id=15&price=31313131").content("").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);

    }

    @Test
    void DeleteTest() throws Exception {

        MvcResult result = mockMvc.perform(
                delete(BASE_PATH+"/16").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);
    }
}