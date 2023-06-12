package com.example.sandbox.util.body.store;

import com.example.sandbox.util.swagger.definitions.StoreBody;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.experimental.SuperBuilder;



    @SuperBuilder
    @Getter
    @JsonInclude (JsonInclude.Include.NON_NULL)
    public class PostCreateStore extends StoreJSonBody {

        @JsonProperty
        private StoreBody StoreBody;

    }
