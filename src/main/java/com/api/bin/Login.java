package com.api.bin;

import lombok.Builder;

@Builder
public class Login {
    private String apiVersion;
    private int partnerId;
    private String username;
    private String password;
    private ExtraParams extraParams;
}
