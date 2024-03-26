package com.example.qtome_be.config;

import lombok.AllArgsConstructor;

import java.util.Date;


@AllArgsConstructor
public class ExceptionResponse {

    private Date timestamp;
    private String message;
    private String dtails;

}
