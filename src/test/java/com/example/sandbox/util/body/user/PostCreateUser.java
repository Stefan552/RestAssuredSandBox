package com.example.sandbox.util.body.user;

import com.example.sandbox.util.swagger.definitions.PetBody;
import com.example.sandbox.util.swagger.definitions.UserBody;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@JsonInclude (JsonInclude.Include.NON_NULL)
public class PostCreateUser extends UserJsonBody{
    @JsonProperty
    private com.example.sandbox.util.swagger.definitions.UserBody UserBody;
}
