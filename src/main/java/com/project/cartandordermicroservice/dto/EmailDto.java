package com.project.cartandordermicroservice.dto;

import lombok.Data;

@Data
public class EmailDto {
    private String email;
    private String subject;
    private String content;
}
