package com.example.ojbackend.entity.submit;

import lombok.Data;

@Data
public class SourceCode {
    private String userId;
    private String problemId;
    private String code;
    private String language;
}