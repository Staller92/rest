package com.api.data;

import com.api.bin.Login;
import com.api.bin.Registration;
import com.api.bin.User;

public class TestData {

    public static Registration createRegistrationData(String username, String password, String externalId) {
        User user = User.builder()
                .objectType("KalturaOTTUser")
                .username(username)
                .firstName("ott_user_lWkiwzTJJGYI")
                .lastName("1585130417330")
                .email("QATest_1585130417313@mailinator.com")
                .address("ott_user_lWkiwzTJJGYI fake address")
                .city("ott_user_lWkiwzTJJGYI fake city")
                .countryId(5).externalId(externalId)
                .build();

        return Registration.builder()
                .apiVersion("5.3.0")
                .partnerId(3197)
                .user(user)
                .password(password)
                .build();
    }

    public static Login createLoginData(String username, String password) {
        return Login.builder()
                .apiVersion("5.3.0")
                .partnerId(3197)
                .username(username)
                .password(password)
                .build();
    }
}
