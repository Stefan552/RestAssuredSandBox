package com.example.sandbox.util.swagger.definitions;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.springframework.test.context.event.annotation.PrepareTestInstance;

@SuperBuilder
@Getter
@JsonInclude (JsonInclude.Include.NON_NULL)
public class StoreBody {
   @JsonProperty
   private Integer id;

   @JsonProperty
   private Integer petId;

   @JsonProperty
   private  Integer  quantity;

   @JsonProperty
   private String  shipDate;

   @JsonProperty
           private String  status;

   @JsonProperty
    private Boolean complete;

}
