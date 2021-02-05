package com.api.bin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    private String objectType;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String city;
    private Integer countryId;
    private String externalId;
    private String id;
    private String affiliateCode;
    private Country country;
    private Integer createDate;
    private DynamicData dynamicData;
    private String facebookId;
    private String facebookImage;
    private String facebookToken;
    private Integer failedLoginCount;
    private Integer householdId;
    private Boolean isHouseholdMaster;
    private Integer lastLoginDate;
    private String phone;
    private String roleIds;
    private String suspensionState;
    private String suspentionState;
    private Integer updateDate;
    private String userState;
    private UserType userType;
    private String zip;
}
