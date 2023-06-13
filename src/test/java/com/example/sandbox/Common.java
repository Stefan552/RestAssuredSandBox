package com.example.sandbox;

import com.example.sandbox.util.swagger.definitions.UserBody;
import io.restassured.response.Response;

import java.io.File;
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
    public Response postUrl( String endpoint, UserBody[] userBodies){


        return given()
                .relaxedHTTPSValidation()
                .contentType("application/json; charset=UTF-8")
                .body(userBodies)
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
    //----------------------------------ORDER ById GET----------------------------------

    public Response getOrderById (  String id ) {

        return given()
                .relaxedHTTPSValidation()
                .and()
                .log().everything()
                .when()
                .get(baseUrl+orderById+id)
                .then()
                .log()
                .all()
                .extract().response();
    }
    public Response getUserByUsername(  String username ) {

        return given()
                .relaxedHTTPSValidation()
                .and()
                .log().everything()
                .when()
                .get(baseUrl+userByUsername+username)
                .then()
                .log()
                .all()
                .extract().response();
    }

    //----------------------------------ORDER ById DELETE----------------------------------

    public Response deleteOrderById(String id){


        return given()
                .relaxedHTTPSValidation()
                .contentType("application/json; charset=UTF-8")
                .and()
                .log().everything()
                .when()
                .delete (baseUrl+deleteOrder+id)
                .then()
                .log()
                .all()
                .extract().response();
    }

    public Response updatePetImage(  Integer id,File file) {
        return given()
                .relaxedHTTPSValidation()
                .formParam("id",id)
                .multiPart ( file)
                .and()
                .log().everything()
                .when()
                .post(baseUrl+petById+id+uploadImage)
                .then()
                .log()
                .all()
                .extract().response();






    }

    public Response updateUserByName(String userName, String body) {
        return given()
                .relaxedHTTPSValidation()
                .contentType("application/json; charset=UTF-8")
                .body(body)
                .and()
                .log().everything()
                .when()
                .put (baseUrl+userByUsername+userName)
                .then()
                .log()
                .all()
                .extract().response();

    }

    public Response loginUser(  String username,String password ,String endpoint) {

        return given()
                .relaxedHTTPSValidation()
                .queryParam ( "userName",username )
                .queryParam ( "password",password )
                .and()
                .log().everything()
                .when()
                .get(baseUrl+endpoint)
                .then()
                .log()
                .all()
                .extract().response();
    }

    public Response logoutUser( String endpoint ) {

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

    public Response deleteUserByUsername(String username ){


        return given()
                .relaxedHTTPSValidation()
                .contentType("application/json; charset=UTF-8")
                .and()
                .log().everything()
                .when()
                .delete (baseUrl+deleteUser+username)
                .then()
                .log()
                .all()
                .extract().response();
    }

}

