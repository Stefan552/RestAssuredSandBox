package com.example.sandbox.User.getUserByUsername;

import com.example.sandbox.Common;
import com.example.sandbox.util.Assertions;
import io.restassured.response.Response;

import org.testng.Assert;
import org.testng.annotations.Test;

import static com.example.sandbox.util.constans.Tags.ORDER;

public class getUserByUsername extends Common {

    com.example.sandbox.util.Assertions Assertions= new Assertions();

    @Test (enabled = true,groups = {ORDER},description ="ValidResponse  INVALID Response Case",priority = 1)
    public void TestGetUserByUsernameValidResponse(){
        Assertions.lineSeparatorStartEndLines ( 1 , "TestGetUserByUsernameValidResponse " );
        Response response=getUserByUsername ( "Lionell Hastighy" );
        Assert.assertNotNull(response);
        Assert.assertEquals( response.getStatusCode(),200);

        String id = response.jsonPath().get("id").toString();
        Assert.assertNotNull(id, " ID is  Null");

        String firstname = response.jsonPath ().getString ( "firstName" );
        Assert.assertNotNull(firstname, " Firstname is  Null");
        Assert.assertEquals(firstname, "Lionell", "Invalid  firstname");

        String lastname = response.jsonPath ().getString ( "lastName" );
        Assert.assertNotNull(lastname, " Lastname is  Null");
        Assert.assertEquals(lastname, "Hastighy", "Invalid  lastname");

        String email = response.jsonPath().get("email").toString();
        Assert.assertNotNull(email, " Email is  Null");
        Assert.assertEquals(email, "lionell145@gmail.com", "Invalid  email");

        String password = response.jsonPath().get("password").toString();
        Assert.assertNotNull(password, " Password is  Null");

        String username = response.jsonPath().get("username").toString ();
        Assert.assertNotNull(username, " Username is not Null");
        Assert.assertEquals(username, "Lionell Hastighy", "Invalid username");

        Assertions.assertResponseHasHeader(response, "Content-Type");
        Assertions.assertResponseTimeLessThan(response, 200000000L);

        Assert.assertTrue(response.body().asString().contains("id"), "Order status 'id' not found in response");

        Assert.assertTrue (response.body().asString().contains("email"), "Response body does not contains email");

        Assert.assertFalse(response.body().asString().isEmpty(), "Response body is empty");

        Assertions.assertResponseHasHeader(response, "Server");
        String server = response.getHeader("Server");
        Assert.assertNotNull(server, "Server header is missing");
        Assert.assertEquals(server, "Jetty(9.2.9.v20150224)", "Invalid content server");

        Assertions.assertResponseBodyHasKey(response, "password");
        String contentType = response.getHeader("Content-Type");
        Assert.assertEquals(contentType, "application/json", "Invalid content type");
        Assert.assertNotNull ( contentType, "The Content-Type  header is missing");
        Assertions.lineSeparatorStartEndLines ( 0 , "TestGetUserByUsernameValidResponse " );

    }

    @Test (enabled = true,groups = {ORDER},description ="ValidResponse  INVALID Response Case",priority = 2)
    public void TestGetUserByUsernameInValidResponse(){
        Assertions.lineSeparatorStartEndLines ( 1 , "TestGetUserByUsernameInValidResponse " );
        Response response=getUserByUsername ( "Lionell Hastighyy" );

        Assert.assertNotNull(response);
        Assert.assertEquals( response.getStatusCode(),404);
        String code = response.jsonPath().get("code").toString();
        Assert.assertNotNull(code, "The code field is  Null");
        Assert.assertEquals ( code,"1","The code field has invalid value" );

        String message = response.jsonPath().get("message").toString();
        Assert.assertNotNull(message, " The message field is  Null");
        Assert.assertEquals ( message,"User not found","Inavlid value for 'message' field" );

        String type = response.jsonPath().get("type").toString ();
        Assert.assertEquals(type, "error", "Invalid type text field");
        Assert.assertNotNull(type, " The type field is  Null");

        Assertions.assertResponseHasHeader(response, "Content-Type");
        Assertions.assertResponseTimeLessThan(response, 200000000L);

        Assert.assertTrue(response.body().asString().contains(code), "Code field not found in response");

        Assert.assertTrue(response.body().asString().contains("User not found"), "Text field 'User not found ' not found in response");

        Assert.assertTrue ( response.body().asString().contains("error"), "Response body does not contains error");

        Assert.assertFalse(response.body().asString().isEmpty(), "Response body is empty");

        Assertions.assertResponseHasHeader(response, "Server");
        String server = response.getHeader("Server");
        Assert.assertNotNull(server, "Server header is missing");
        Assert.assertEquals(server, "Jetty(9.2.9.v20150224)", "Invalid content server");

        Assertions.assertResponseBodyContains(response, "code");

        Assertions.assertResponseBodyHasKey(response, "message");
        String contentType = response.getHeader("Content-Type");
        Assert.assertEquals(contentType, "application/json", "Invalid content type");
        Assert.assertNotNull ( contentType, "The Content-Type  header is missing");
        Assertions.lineSeparatorStartEndLines ( 0 , "TestGetUserByUsernameInValidResponse " );

}}