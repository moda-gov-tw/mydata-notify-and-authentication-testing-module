package com.example.demomydata.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 呼叫UserInfo Endpoint回傳的資料
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserInfoEntity {

    //對應至egov登入token1
    @JsonProperty("sub")
    private String sub;

    @JsonProperty("cn")
    private String name;

    @JsonProperty("preferred_username")
    private String preferredUsername;

    @JsonProperty("name")
    private String accountName;

    //代表身份證字號
    @JsonProperty("uid")
    private String uid;

    @JsonProperty("uid_verified")
    private String isValidUid;

    @JsonProperty("birthdate")
    private String birthdate;

    @JsonProperty("gender")
    private String gender;

    @JsonProperty("email")
    private String email;

    @JsonProperty("email_verified")
    private Boolean emailVerified;

    //代表eGov帳號
    @JsonProperty("account")
    private String account;

    @JsonProperty("profile")
    private String profile;

    @JsonProperty("idp")
    private String idp;

    @JsonProperty("x509type")
    private String x509type;

    @JsonProperty("amr")
    private String amr;

    //綁定電話號碼？
    @JsonProperty("phone_number")
    private Object phoneNumber;

    private boolean boxcheck;
}
