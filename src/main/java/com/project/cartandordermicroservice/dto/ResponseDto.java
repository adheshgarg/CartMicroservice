package com.project.cartandordermicroservice.dto;

import lombok.Data;

@Data
public class ResponseDto<T> {

    private Boolean success;
    private String message;
    private Iterable<T> data;

}