package com.example.sandbox.util.swagger.definitions;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.experimental.SuperBuilder;


@SuperBuilder
@Getter
@JsonInclude (JsonInclude.Include.NON_NULL)
public class UserBody {
    @JsonProperty
    private Integer id;

    @JsonProperty
    private String username	;

    @JsonProperty
   private String firstName	;

    @JsonProperty
    private String lastName;

    @JsonProperty
    private String email;

    @JsonProperty
    private String password;

    @JsonProperty
    private String phone;

    @JsonProperty
    private Integer userStatus;



}
