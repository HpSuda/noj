package com.example.ojbackend.controller.dto;

import lombok.Data;

@Data
public class ProblemDto {
    private String title;
    private String description;
    private String inputData;
    private String outputData;
    private String level;
}