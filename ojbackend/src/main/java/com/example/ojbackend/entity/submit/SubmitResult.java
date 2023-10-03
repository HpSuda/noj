package com.example.ojbackend.entity.submit;

import lombok.Data;

@Data
public class SubmitResult {
    private String date;
    private String status;
    private String author;
    private String problem;
    private String language;
}
