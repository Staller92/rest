package com.api.bin;

import lombok.Data;

@Data
public class Error {
    private String code;
    private String message;
    private String objectType;
}
