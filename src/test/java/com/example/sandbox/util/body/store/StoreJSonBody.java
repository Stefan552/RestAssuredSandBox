package com.example.sandbox.util.body.store;

import com.example.sandbox.util.body.store.PostCreateStore;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class StoreJSonBody {
    public StoreJSonBody ( ) {
    }

    public static String createJsonBody2( PostCreateStore body){
        try{
            return new ObjectMapper().writeValueAsString(body.getStoreBody ());

        } catch (Throwable e){
            throw new RuntimeException("Nody Generation Failure");
        }
    }
}
