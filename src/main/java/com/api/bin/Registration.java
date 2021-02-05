package com.api.bin;

import lombok.Builder;

@Builder
public class Registration {
    private String apiVersion;
    private int partnerId;
    private User user;
    private String password;
}
