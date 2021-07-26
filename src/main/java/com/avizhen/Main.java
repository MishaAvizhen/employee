package com.avizhen;

import com.avizhen.entity.Employee;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;

import java.util.Collections;

@SpringBootApplication
@EnableJms
public class Main {
    @Bean
    public MappingJackson2MessageConverter messageConverter() {
        MappingJackson2MessageConverter messageConverter = new MappingJackson2MessageConverter();
        messageConverter.setTypeIdPropertyName("content-type");
        messageConverter.setTypeIdMappings(Collections.singletonMap("test", Employee.class) );
        return messageConverter;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);

    }
}