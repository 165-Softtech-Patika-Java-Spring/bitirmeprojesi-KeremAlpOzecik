package com.example.demo.mgr.controller;

import com.example.demo.BaseTest;
import com.example.demo.mgr.dto.ManagerUpdateRequestDto;
import com.example.demo.prd.dto.ProductUpdateRequestDto;
import com.example.demo.prd.enums.Category;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@ExtendWith(MockitoExtension.class)
@SpringBootTest()
@AutoConfigureMockMvc
class ManagerControllerTest extends BaseTest {
    private static final String BASE_PATH = "/api/v1/managers";

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;



    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        this.objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    }

    @Test
    void update() throws Exception {
        ManagerUpdateRequestDto managerUpdateRequestDto=new ManagerUpdateRequestDto();
            managerUpdateRequestDto.setId(5L);
            managerUpdateRequestDto.setEmail("Test");
            managerUpdateRequestDto.setName("Test");
            managerUpdateRequestDto.setPassword("Test");
            managerUpdateRequestDto.setPhone("Test");
            managerUpdateRequestDto.setSurname("Test");
            managerUpdateRequestDto.setUsername("Test");


            String content = new ObjectMapper().writeValueAsString(managerUpdateRequestDto);

            MvcResult result = mockMvc.perform(
                    put(BASE_PATH).contentType(MediaType.APPLICATION_JSON).content(content)
            ).andExpect(status().isOk()).andReturn();

            boolean isSuccess = isSuccess(result);

            assertTrue(isSuccess);

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
                get(BASE_PATH + "/5").content("5L").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);
    }
    @Test
    void Delete() throws Exception {

        MvcResult result = mockMvc.perform(
                delete(BASE_PATH+"/5").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);
    }
}