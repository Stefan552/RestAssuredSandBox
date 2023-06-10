package com.example.sandbox;

import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

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


    public Response getUrl ( String petById , String id ) {
        return given()
                .relaxedHTTPSValidation()
                .and()
                .log().everything()
                .when()
                .body ( "id"==id )
                .get(baseUrl+petById)
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

    //----------------------------------DELETE----------------------------------

    public Response deleteUrl(String endpoint,String body){


        return given()
                .relaxedHTTPSValidation()
                .contentType("application/json; charset=UTF-8")
                .body(body)
                .and()
                .log().everything()
                .when()
                .delete (baseUrl+endpoint)
                .then()
                .log()
                .all()
                .extract().response();

    }
}

