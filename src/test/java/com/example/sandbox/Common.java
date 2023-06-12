package com.example.sandbox;

import com.example.sandbox.util.body.pet.JsonBody;
import com.example.sandbox.util.body.pet.PostCreatePet;
import com.example.sandbox.util.swagger.definitions.Item;
import com.example.sandbox.util.swagger.definitions.PetBody;
import com.google.gson.JsonObject;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

import static com.example.sandbox.util.Tools.generateRandomNumber;
import static com.example.sandbox.util.constans.TestData.HYDRAIMAGE;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Common extends Endpoints {

    //----------------------------------GET----------------------------------
    public Response getUrlt(String endpoint, String petId) {


        return given()
                .relaxedHTTPSValidation()
                .and()
                .log().everything()
                .pathParam("petId", petId)
                .when()
                .get(baseUrl + endpoint + "/{petId}")
                .then()
                .log()
                .all()
                .extract().response();

    }


    public Response getUrlPetID (  String id ) {

        return given()
                .relaxedHTTPSValidation()
                .and()
                .log().everything()
                .when()
                .body ( "id"==id )
                .get(baseUrl+petById+id)
                .then()
                .log()
                .all()
                .extract().response();
    }
    public Response getUrl(String endpoint){


        return given()
                .relaxedHTTPSValidation()
                .and()
                .log().everything()
                .when()
                .get(baseUrl+endpoint)
                .then()
                .log()
                .all()
                .extract().response();

    }
    public Response getUrl(String endpoint, Map<String, String> queryParam ){


        return given()
                .relaxedHTTPSValidation()
                .params(queryParam)
                .and()
                .log().everything()
                .when()
                .get(baseUrl+endpoint)
                .then()
                .log()
                .all()
                .extract().response();

    }
    public Response getUrl(String endpoint,Map<String, String> headers,Map<String, String> queryParam ){


        return given()
                .relaxedHTTPSValidation()
                .params(queryParam)
                .headers(headers)
                .and()
                .log().all()
                .when()
                .get(baseUrl+endpoint)
                .then()
                .log()
                .all()
                .extract().response();

    }

    //----------------------------------POST----------------------------------
    public Response postUrl(String endpoint,String body){


        return given()
                .relaxedHTTPSValidation()
                .contentType("application/json; charset=UTF-8")
                .body(body)
                .and()
                .log().everything()
                .when()
                .post(baseUrl+endpoint)
                .then()
                .log()
                .all()
                .extract().response();

    }

    //----------------------------------PUT----------------------------------
    public Response updatePet(  String body) {
        return given()
                .relaxedHTTPSValidation()
                .contentType("application/json; charset=UTF-8")
                .body(body)
                .and()
                .log().everything()
                .when()
                .put (baseUrl+newPet)
                .then()
                .log()
                .all()
                .extract().response();

    }

    //----------------------------------DELETE----------------------------------

    public Response deletePet(String id){


        return given()
                .relaxedHTTPSValidation()
                .contentType("application/json; charset=UTF-8")
                .and()
                .log().everything()
                .when()
                .delete (baseUrl+deletePet+id)
                .then()
                .log()
                .all()
                .extract().response();
    }
}

