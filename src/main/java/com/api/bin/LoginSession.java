package com.api.bin;

import lombok.Data;

@Data
public class LoginSession {
    private String objectType;
    private int expiry;
    private String ks;
    private String refreshToken;
}
