package com.example.api.numberGenerator;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.JdkIdGenerator;

import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

/**
 * Created by msosl on 12/28/16.
 */

public class NumberGeneratorControllerTest {
    @Mock
    JdkIdGenerator jdkIdGenerator;

    @Mock
    DefaultNumberGeneratorService defaultNumberGeneratorService;

    @InjectMocks
    NumberGeneratorController numberGeneratorController;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    MockMvc mockMvc;

    @Before
    public void setupMockMvc() {
        mockMvc = standaloneSetup(numberGeneratorController)
                .build();
    }

    @Test
    public void getNumbers_returnsTwoNumbers() throws Exception {
        when(jdkIdGenerator.generateId())
                .thenReturn(UUID.randomUUID());

        when(defaultNumberGeneratorService.generateNumber())
                .thenReturn(UUID.randomUUID().toString());

        mockMvc.perform(get("/numbers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numberOne", isA(String.class)))
                .andExpect(jsonPath("$.numberTwo", isA(String.class)));

    }

    @Test
    public void getNumberByNumber_returnsNumber() throws Exception {
        mockMvc.perform(get("/numbers/{number}", "5"))
                .andExpect(status().isOk())
                .andExpect(content().string("Your number is 5"));
    }

    @Test
    public void postNumberGetNumber_returnsNumber() throws Exception {
        mockMvc.perform(post("/numbers")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"number\":\"6\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Your number is 6"));
    }
}