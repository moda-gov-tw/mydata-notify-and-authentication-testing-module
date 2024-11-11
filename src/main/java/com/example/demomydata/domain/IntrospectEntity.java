package com.example.demomydata.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 反查 access_token 的結果
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class IntrospectEntity {
    //OPTIONAL. Integer timestamp, measured in the number of seconds since January 1 1970 UTC,
    //indicating when this token is not to be used before, as defined in JWT [RFC7519]
    @JsonProperty("nbf")
    private Long nbf;

    //OPTIONAL.  Integer timestamp, measured in the number of seconds since January 1 1970 UTC,
    //indicating when this token will expire, as defined in JWT [RFC7519].
    @JsonProperty("exp")
    private Long exp;

    //OPTIONAL.  String representing the issuer of this token, as defined in JWT [RFC7519].
    @JsonProperty("iss")
    private String iss;

    //OPTIONAL.  Service-specific string identifier or list of string
    //identifiers representing the intended audience for this token, as defined in JWT [RFC7519].
    @JsonProperty("aud")
    private List<String> aud;

    //OPTIONAL.  Client identifier for the OAuth 2.0 client that requested this token.
    @JsonProperty("client_id")
    private String clientId;

    //OPTIONAL.  Subject of the token, as defined in JWT [RFC7519].
    //Usually a machine-readable identifier of the resource owner who authorized this token.
    @JsonProperty("sub")
    private String sub;

    @JsonProperty("auth_time")
    private Long authTime;

    @JsonProperty("idp")
    private String idp;

    //OPTIONAL.  Human-readable identifier for the resource owner who authorized this token.
    @JsonProperty("name")
    private String name;

    @JsonProperty("amr")
    private String amr;

    //OPTIONAL.  A JSON string containing a space-separated list of scopes associated with this token,
    //in the format described in Section 3.3 of OAuth 2.0 [RFC6749].
    @JsonProperty("scope")
    private String scope;

    //REQUIRED.  Boolean indicator of whether or not the presented token is currently active.
    @JsonProperty("active")
    private Boolean active;

    @JsonProperty("verification")
    private String verification;

    @JsonProperty("X509type")
    private String x509type;

    /**
     *
     * @param active Boolean
     */
    public IntrospectEntity(final Boolean active) {
        this.active = active;
    }

    /**
     *
     * @param active Boolean
     * @param verification String
     */
    public IntrospectEntity(final Boolean active, final String verification) {
        this.active = active;
        this.verification = verification;
    }
}
