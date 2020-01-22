package com.project.cartandordermicroservice.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.cartandordermicroservice.dto.EmailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmailService {

    @Autowired
    KafkaTemplate<String,String> productDtoKafkaTemplate;
    private String TopicName="email";
    public void produce(EmailDto emailDto) throws IOException
    {
        ObjectMapper objectMapper = new ObjectMapper();
        String productDto=objectMapper.writeValueAsString(emailDto);
        this.productDtoKafkaTemplate.send(TopicName,productDto);
    }
}
