package com.example.sandbox.util.body.user;

import com.example.sandbox.util.body.pet.PostCreatePet;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class UserJsonBody {
    public UserJsonBody ( ) {
    }

    public static String createJsonBody3( PostCreateUser body){
        try{
            return new ObjectMapper ().writeValueAsString(body.getUserBody ());

        } catch (Throwable e){
            throw new RuntimeException("Nody Generation Failure");
        }
    }

}
