package com.api.bin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Result {
    private Error error;
    private String objectType;
    private String firstName;
    private String id;
    private String lastName;
    private String username;
    private String address;
    private String city;
    private Country country;
    private int countryId;
    private int createDate;
    private DynamicData dynamicData;
    private String email;
    private String externalId;
    private int failedLoginCount;
    private int householdId;
    private String isHouseholdMaster;
    private Long lastLoginDate;
    private String roleIds;
    private String suspensionState;
    private String suspentionState;
    private int updateDate;
    private String userState;
    private UserType userType;
    private LoginSession loginSession;
    private User user;
}
