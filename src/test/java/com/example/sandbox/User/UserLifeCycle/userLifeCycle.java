package com.example.sandbox.User.UserLifeCycle;

import com.example.sandbox.Common;
import com.example.sandbox.util.Assertions;
import com.example.sandbox.util.body.user.PostCreateUser;
import com.example.sandbox.util.body.user.UserJsonBody;
import com.example.sandbox.util.swagger.definitions.UserBody;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.example.sandbox.util.Tools.generateRandomNumber;
import static com.example.sandbox.util.constans.Tags.ORDER;
import static com.example.sandbox.util.constans.Tags.USER;

public class userLifeCycle extends Common {
    Assertions Assertions=new Assertions ();

    @Test (enabled = true, groups = {USER}, description = "testCreateUserValidResponse Test VALID Response Case",priority = 1)
    public void testCreateUser() {
        Assertions.lineSeparatorStartEndLines ( 1 , " testCreateUserValidResponse " );
        UserJsonBody UserJsonBody=new UserJsonBody ();
        Integer id =generateRandomNumber ();
        PostCreateUser body = PostCreateUser.builder()
                .UserBody ( UserBody.builder()
                        .id (id)
                        .username ( "Lionell Hastighy" )
                        .firstName ( "Lionell" )
                        .lastName ( "Hastighy" )
                        .email ( "lionell145@gmail.com" )
                        .password ( "lion147" )
                        .phone ( "+1208945712" )
                        .userStatus ( 1 )
                        .build ()).build ();

        Response response=postUrl ( createUserSimple, UserJsonBody.createJsonBody3(body) );
        Assert.assertNotNull(response);
        Assert.assertEquals( response.getStatusCode(),200);

        String code = response.jsonPath().get("code").toString();
        Assert.assertNotNull(code, " Code is not Null");
        Assert.assertEquals(code,"200" , "Invalid message");

        String type = response.jsonPath().get("type").toString();
        Assert.assertNotNull(type, " Type is not Null");
        Assert.assertEquals(type,"unknown", "Invalid message");

        String message = response.jsonPath().get("message").toString ();
        Assert.assertNotNull(message, " Message is not Null");
        Assert.assertEquals(message,id.toString () , "Invalid message");

        Assertions.assertResponseHasHeader(response, "Content-Type");
        Assertions.assertResponseTimeLessThan(response, 200000000L);

        Assert.assertTrue(response.body().asString().contains("code"), "Response field 'code' not found in response");

        Assert.assertTrue(response.body().asString().contains("type"), "Response field 'type' not found in response");
        Assert.assertTrue(response.body().asString().contains("message"), "Response field 'message' not found in response");

        Assert.assertFalse(response.body().asString().contains("id"), "Response body contains error");

        Assert.assertFalse(response.body().asString().isEmpty(), "Response body is empty");

        Assertions.assertResponseHasHeader(response, "Server");
        String server = response.getHeader("Server");
        Assert.assertNotNull(server, "Server header is missing");
        Assert.assertEquals(server, "Jetty(9.2.9.v20150224)", "Invalid content server");

        String contentType = response.getHeader("Content-Type");
        Assert.assertEquals(contentType, "application/json", "Invalid content type");
        Assert.assertNotNull ( contentType, "The Content-Type  header is missing");
        Assertions.lineSeparatorStartEndLines ( 0 , " testCreateUserValidResponse " );

    }

    @Test (enabled = true,groups = {ORDER},description ="ValidResponse  INVALID Response Case",priority = 2)
    public void TestGetUserByUsername(){
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

    @Test (enabled = true,groups = {ORDER},description ="TestUpdateByUsernameValidResponse",priority = 3)
    public void TestUpdateByUsernameValidResponse(){
        Assertions.lineSeparatorStartEndLines ( 1 , "TestUpdateByUsernameValidResponse " );

        PostCreateUser body = PostCreateUser.builder()
                .UserBody ( UserBody.builder()
                        .username ( "Lionell Hastighy" )
                        .firstName ( "Lionell" )
                        .lastName ( "Hastighy" )
                        .email ( "lionell145@gmail.com" )
                        .password ("noAllowance147" )
                        .phone ( "+40233166145" )
                        .userStatus ( 1 )
                        .build ()).build ();
        UserJsonBody userJsonBody=new UserJsonBody ();

        Response response=updateUserByName ("Lionell Hastighy",userJsonBody.createJsonBody3(body) );
        Assert.assertNotNull(response);
        Assert.assertEquals( response.getStatusCode(),200);
        Assertions.assertResponseHasHeader(response, "Server");
        String server = response.getHeader("Server");
        Assert.assertNotNull(server, "Server header is missing");
        Assert.assertEquals(server, "Jetty(9.2.9.v20150224)", "Invalid content server");

        Assertions.assertResponseBodyHasKey(response, "message");
        String contentType = response.getHeader("Content-Type");
        Assert.assertEquals(contentType, "application/json", "Invalid content type");
        Assert.assertNotNull ( contentType, "The Content-Type  header is missing");

        String responseBody = response.getBody().asString();
        Assert.assertTrue ( responseBody.contains ( "code"));
        Assertions.assertResponseTimeLessThan (response, 20000l );
        String headers = response.getHeader("Accept=*/*");
        Assert.assertEquals (  headers,null,"'Headers' field is not null!");
        Assert.assertFalse(responseBody.isEmpty(), "Response body is empty");

        Assert.assertTrue(response.body().asString().contains("code"), "Response body does  not contains 'code'");
        Assert.assertTrue(response.body().asString().contains("type"), "Response body does not contains 'type'");
        Assert.assertTrue(response.body().asString().contains("message"), "Response body does  not contains 'message'");
        String code=response.jsonPath ().getString ( "code" );
        Assert.assertEquals ( code,"200","The 'code'  field is not 200" );

        String type=response.jsonPath ().getString ( "type" );
        Assert.assertEquals ( type,"unknown","The 'type'  field is not unknown" );
        Assertions.assertResponseTimeLessThan(response, 200000000L);

        Assertions.lineSeparatorStartEndLines ( 0 , "TestUpdateByUsernameValidResponse " );

    }

    @Test (enabled = true, groups = {USER}, description = "testUserLoginValidResponse  VALID Response Case",priority = 4)
    public void testUserLogin() {
        Assertions.lineSeparatorStartEndLines ( 1,"testUserLoginValidResponse" );
        Response response=loginUser ( "Lionell Hastighy","noAllowance147" ,login);

        Assert.assertEquals( response.getStatusCode(),200);
        Assertions.assertResponseHasHeader(response, "Server");
        String server = response.getHeader("Server");
        Assert.assertNotNull(server, "Server header is missing");
        Assert.assertEquals(server, "Jetty(9.2.9.v20150224)", "Invalid content server");

        Assertions.assertResponseBodyHasKey(response, "message");
        String contentType = response.getHeader("Content-Type");
        Assert.assertEquals(contentType, "application/json", "Invalid content type");
        Assert.assertNotNull ( contentType, "The Content-Type  header is missing");

        String responseBody = response.getBody().asString();
        Assert.assertTrue ( responseBody.contains ( "code"));
        Assertions.assertResponseTimeLessThan (response, 20000l );
        String headers = response.getHeader("Accept=*/*");
        Assert.assertEquals (  headers,null,"'Headers' field is not null!");
        Assert.assertFalse(responseBody.isEmpty(), "Response body is empty");

        Assert.assertTrue(response.body().asString().contains("code"), "Response body does  not contains 'code'");
        Assert.assertTrue(response.body().asString().contains("type"), "Response body does not contains 'type'");
        Assert.assertTrue(response.body().asString().contains("message"), "Response body does  not contains 'message'");
        String code=response.jsonPath ().getString ( "code" );
        Assert.assertEquals ( code,"200","The 'code'  field is not 200" );

        String type=response.jsonPath ().getString ( "type" );
        Assert.assertEquals ( type,"unknown","The 'type'  field is not unknown" );


        String message=response.jsonPath ().getString ( "message" );
        Assert.assertTrue ( message.contains ( "logged in user session:" ),"The message field does not contains 'logged in user session:'" );

        Assertions.assertResponseTimeLessThan(response, 200000000L);

        Assertions.lineSeparatorStartEndLines ( 0,"testUserLoginValidResponse" );
    }

    @Test (enabled = true,groups = {USER},description ="testUserLogoutValidResponse  VALID Response Case",priority = 5)
    public void testUserLogout() {
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

    @Test (enabled = true,groups = {USER},description ="Deleting User  VALID Response Case",priority = 6)
    public void TestDeletingUser(){
        Assertions.lineSeparatorStartEndLines ( 1,"TestDeletingUserValidResponse  " );
        Response response = deleteUserByUsername ( "Lionell Hastighy" );
        Assert.assertEquals(response.getStatusCode(), 200, "Invalid response code");

        String contentType = response.getHeader("Content-Type");
        Assert.assertEquals(contentType, "application/json", "Invalid content type");
        Assert.assertNotNull ( contentType, "The Content-Type  header is missing");

        String server = response.getHeader("Server");
        Assert.assertNotNull(server, "Server header is missing");
        Assert.assertEquals(server, "Jetty(9.2.9.v20150224)", "Invalid content server");
        String responseBody = response.getBody().asString();
        Assert.assertTrue ( responseBody.contains ( "code"));
        Assertions.assertResponseTimeLessThan (response, 20000l );
        String headers = response.getHeader("Accept=*/*");
        Assert.assertEquals (  headers,null,"'Headers' field is not null!");
        Assert.assertFalse(responseBody.isEmpty(), "Response body is empty");

        Assert.assertTrue(response.body().asString().contains("code"), "Response body does  not contains 'code'");

        Assert.assertTrue(response.body().asString().contains("type"), "Response body does not contains 'type'");
        Assert.assertTrue(response.body().asString().contains("message"), "Response body does  not contains 'message'");

        String code=response.jsonPath ().getString ( "code" );
        Assert.assertEquals ( code,"200","The 'code'  field is not 200" );

        String type=response.jsonPath ().getString ( "type" );
        Assert.assertEquals ( type,"unknown","The 'type'  field is not unknown" );

        String message=response.jsonPath ().getString ( "message" );
        Assert.assertEquals ( message,"Lionell Hastighy","The message  field is not 'Lionell Hastighy' " );


        Assertions.lineSeparatorStartEndLines ( 0," TestDeletingUserValidResponse " );

    }




}
