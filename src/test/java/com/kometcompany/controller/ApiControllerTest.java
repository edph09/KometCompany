package com.kometcompany.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@WebAppConfiguration
class ApiControllerTest {

    private static final String BASE_URL_FINALFREIGHT = "/api/final-freight";
    private final static String BASE_URL_PRICE = "/api/calculate-price";
    private final static String BASE_URL_CODE = "/api/product-code";
    private final static int STATUS_OK = 200;
    private final static int STATUS_NOTFOUND = 404;
    private final static int STATUS_BADREQUEST = 400;
    private final static String PARAMETER = "1";
    private final static String BAD_PARAMETER = "m";
    private final static String PARAMETER_NOTFOUND = "1000";
    private final static String COMPANY_ID = "companyId";
    private final static String CUSTOMER_ID = "customerId";


    MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;


    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

    }

    @Test
    void companiesFinalFreight() throws Exception {
        MvcResult mockmvcResult = mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL_FINALFREIGHT)
                .accept(MediaType.APPLICATION_JSON)
                        .queryParam(COMPANY_ID,PARAMETER))
                .andReturn();
        assertEquals(STATUS_OK,mockmvcResult.getResponse().getStatus());
    }
    @Test
    void companiesFinalFreightNotFound() throws Exception {
        MvcResult mockmvcResult = mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL_FINALFREIGHT)
                        .accept(MediaType.APPLICATION_JSON)
                        .queryParam(COMPANY_ID,PARAMETER_NOTFOUND))
                .andReturn();
        assertEquals(STATUS_NOTFOUND,mockmvcResult.getResponse().getStatus());
    }
    @Test
    void companiesFinalFreightBadRequest() throws Exception {
        MvcResult mockmvcResult = mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL_FINALFREIGHT)
                        .accept(MediaType.APPLICATION_JSON)
                        .queryParam(COMPANY_ID,BAD_PARAMETER))
                .andReturn();
        assertEquals(STATUS_BADREQUEST,mockmvcResult.getResponse().getStatus());
    }

    @Test
    void calculatePrice() throws Exception {
        MvcResult mockmvcResult = mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL_PRICE)
                        .accept(MediaType.APPLICATION_JSON)
                        .queryParam(CUSTOMER_ID,PARAMETER))
                .andReturn();
        assertEquals(STATUS_OK,mockmvcResult.getResponse().getStatus());
    }
    @Test
    void calculatePriceNotFound() throws Exception {
        MvcResult mockmvcResult = mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL_PRICE)
                        .accept(MediaType.APPLICATION_JSON)
                        .queryParam(CUSTOMER_ID,PARAMETER_NOTFOUND))
                .andReturn();
        assertEquals(STATUS_NOTFOUND,mockmvcResult.getResponse().getStatus());
    }
    @Test
    void calculatePriceBadRequest() throws Exception {
        MvcResult mockmvcResult = mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL_PRICE)
                        .accept(MediaType.APPLICATION_JSON)
                        .queryParam(CUSTOMER_ID,BAD_PARAMETER))
                .andReturn();
        assertEquals(STATUS_BADREQUEST,mockmvcResult.getResponse().getStatus());
    }

    @Test
    void productCodeGenerator() throws Exception {
        MvcResult mockmvcResult = mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL_CODE)
                        .accept(MediaType.APPLICATION_JSON)
                        .queryParam(COMPANY_ID,PARAMETER))
                .andReturn();
        assertEquals(STATUS_OK,mockmvcResult.getResponse().getStatus());
    }
    @Test
    void productCodeGeneratorNotFound() throws Exception {
        MvcResult mockmvcResult = mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL_CODE)
                        .accept(MediaType.APPLICATION_JSON)
                        .queryParam(COMPANY_ID,PARAMETER_NOTFOUND))
                .andReturn();
        assertEquals(STATUS_NOTFOUND,mockmvcResult.getResponse().getStatus());
    }
    @Test
    void productCodeGeneratorBadRequest() throws Exception {
        MvcResult mockmvcResult = mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL_CODE)
                        .accept(MediaType.APPLICATION_JSON)
                        .queryParam(COMPANY_ID,BAD_PARAMETER))
                .andReturn();
        assertEquals(STATUS_BADREQUEST,mockmvcResult.getResponse().getStatus());
    }
}