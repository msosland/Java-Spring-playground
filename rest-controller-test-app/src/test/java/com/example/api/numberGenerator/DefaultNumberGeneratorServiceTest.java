package com.example.api.numberGenerator;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.util.JdkIdGenerator;

import java.util.UUID;

import static org.hamcrest.Matchers.isA;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Created by msosl on 12/28/16.
 */
public class DefaultNumberGeneratorServiceTest {
    @Mock
    JdkIdGenerator jdkIdGenerator;

    @InjectMocks
    DefaultNumberGeneratorService defaultNumberGeneratorService;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void generateNumber_generatesNumber() {
        when(jdkIdGenerator.generateId())
                .thenReturn(UUID.randomUUID());

        String number = defaultNumberGeneratorService.generateNumber();

        assertThat(number, isA(String.class));
    }
}