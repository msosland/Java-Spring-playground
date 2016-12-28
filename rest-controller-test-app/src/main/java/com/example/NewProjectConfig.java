package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.JdkIdGenerator;

/**
 * Created by msosl on 12/28/16.
 */
@Configuration
public class NewProjectConfig {
    @Bean
    public JdkIdGenerator jdkIdGenerator() { return new JdkIdGenerator(); }
}
