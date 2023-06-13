package com.example.sandbox.User.userLogout;

import com.example.sandbox.Common;
import com.example.sandbox.util.Assertions;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.example.sandbox.util.constans.Tags.ORDER;
import static com.example.sandbox.util.constans.Tags.USER;

public class userLogout extends Common {

Assertions Assertions=new Assertions ();

    @Test (enabled = true,groups = {USER},description ="testUserLogoutValidResponse  VALID Response Case",priority = 1)
    public void testUserLogoutValidResponse() {
        Assertions.lineSeparatorStartEndLines ( 1 , "testUserLogoutValidResponse " );

        Response response=logoutUser ( logout);

        Assert.assertNotNull(response);
        Assert.assertEquals( response.getStatusCode(),200);

        String code = response.jsonPath().get("code").toString();
        Assert.assertNotNull(code, "The code field is  Null");
        Assert.assertEquals ( code,"200","The code field has invalid value" );

        String message = response.jsonPath().get("message").toString();
        Assert.assertNotNull(message, " The message field is  Null");
        Assert.assertEquals ( message,"ok","Invalid value for 'message' field" );

        String type = response.jsonPath().get("type").toString ();
        Assert.assertEquals(type, "unknown", "Invalid type text field");
        Assert.assertNotNull(type, " The type field is  Null");

        Assertions.assertResponseHasHeader(response, "Content-Type");
        Assertions.assertResponseTimeLessThan(response, 200000000L);

        Assert.assertTrue(response.body().asString().contains(code), "Code field not found in response");

        Assert.assertTrue(response.body().asString().contains("ok"), "Text field 'User not found ' not found in response");

        Assert.assertTrue ( response.body().asString().contains("type"), "Response body does not contains error");

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
        Assertions.lineSeparatorStartEndLines ( 0 , "testUserLogoutValidResponse " );

    }

    @Test (enabled = true,groups = {USER},description ="testUserLogoutInValidResponse  INVALID Response Case",priority = 2)
    public void testUserLogoutINValidResponse() {
        Assertions.lineSeparatorStartEndLines ( 1 , "testUserLogoutINValidResponse " );

        Response response=logoutUser (logout+1 );

        Assert.assertNotNull(response);
        Assert.assertEquals( response.getStatusCode(),404);

        String code = response.jsonPath().get("code").toString();
        Assert.assertNotNull(code, "The code field is  Null");
        Assert.assertEquals ( code,"1","The code field has invalid value" );

        String message = response.jsonPath().get("message").toString();
        Assert.assertNotNull(message, " The message field is  Null");
        Assert.assertEquals ( message,"User not found","Invalid value for 'message' field" );

        String type = response.jsonPath().get("type").toString ();
        Assert.assertEquals(type, "error", "Invalid type text field");
        Assert.assertNotNull(type, " The type field is  Null");

        Assertions.assertResponseHasHeader(response, "Content-Type");
        Assertions.assertResponseTimeLessThan(response, 200000000L);

        Assert.assertTrue(response.body().asString().contains(code), "Code field not found in response");

        Assert.assertTrue(response.body().asString().contains("User not found"), "Text field 'User not found ' not found in response");

        Assert.assertTrue ( response.body().asString().contains("type"), "Response body does not contains error");

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
        Assertions.lineSeparatorStartEndLines ( 0 , "testUserLogoutINValidResponse " );

    }

    }
