package com.example.exchange_rates.controllers;

import com.example.exchange_rates.dto.Operation;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.Assert;

import java.io.IOException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CurrencyControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testWhenGetCurrency() throws Exception {
    mockMvc.perform(get("/getShortCurrency/USD"))
            .andExpect(status().isOk()).andDo(print());
    }

    @Test
    public void testWhenGetSpendsMoreThen() throws Exception {
        mockMvc.perform(get("/getSpendsMoreThen?user_id=1&amount=200"))
                .andExpect(status().isOk()).andDo(print());

        String response_body = mockMvc.perform(get("/getSpendsMoreThen?user_id=1&amount=200")).
                andReturn().getResponse().getContentAsString();

        boolean isJSON = isJSONValid(response_body);

        Assert.isTrue(isJSON,"Некорректный JSON");

    }

    @Test
    public void testSaveSpend() throws Exception {

        Operation operation = new Operation(1L, "spend", "111.00");

        mockMvc.perform(post("/addOperation").content(objectMapper.writeValueAsString(operation))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andDo(print());

    }

    public static boolean isJSONValid(String jsonInString ) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            mapper.readTree(jsonInString);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
