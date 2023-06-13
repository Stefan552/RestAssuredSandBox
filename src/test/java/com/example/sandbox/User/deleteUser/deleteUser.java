package com.example.sandbox.User.deleteUser;

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

public class deleteUser extends Common {
Assertions Assertions=new Assertions ();
    @Test (enabled = true, groups = {USER}, description = "testCreateUserValidResponse Test VALID Response Case",priority = 1)
    public void testCreateUserValidResponse() {
        Assertions.lineSeparatorStartEndLines ( 1 , " testCreateUserValidResponse " );
        UserJsonBody UserJsonBody=new UserJsonBody ();
        Integer id =generateRandomNumber ();
        PostCreateUser body = PostCreateUser.builder()
                .UserBody ( UserBody.builder()
                        .id (id)
                        .username ( "Lionell Allmighty" )
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

    @Test (enabled = true,groups = {USER},description ="Deleting User  VALID Response Case",priority = 2)
    public void TestDeletingUserValidResponse(){
        Assertions.lineSeparatorStartEndLines ( 1,"TestDeletingUserValidResponse  " );
        Response response = deleteUserByUsername ( "Lionell Allmighty" );
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
        Assert.assertEquals ( message,"Lionell Allmighty","The message  field is not 'Lionell Hastighy' " );


        Assertions.lineSeparatorStartEndLines ( 0," TestDeletingUserValidResponse " );

    }

    @Test (enabled = true,groups = {USER},description ="Deleting User INVALID Response Case",priority = 3)
    public void TestDeletingUserInValidResponse(){
        Assertions.lineSeparatorStartEndLines ( 1,"TestDeletingUserInValidResponse  " );
        Response response =deleteUserByUsername ( "Lionell Allmightyy" );
        Assert.assertEquals(response.getStatusCode(), 404, "Invalid response code");

        String contentType = response.getHeader("Content-Type");
        Assert.assertNull ( contentType, "There is a Content-Type  header ");

        String server = response.getHeader("Server");
        Assert.assertNotNull(server, "Server header is missing");
        Assert.assertEquals(server, "Jetty(9.2.9.v20150224)", "Invalid content server");
        String responseBody = response.getBody().asString();

        Assertions.assertResponseTimeLessThan (response, 20000l );
        String headers = response.getHeader("Accept=*/*");
        Assert.assertEquals (  headers,null,"'Headers' field is not null!");
        Assert.assertTrue (responseBody.isEmpty(), "Response body is not empty");

      Assertions.lineSeparatorStartEndLines ( 0,"TestDeletingUserInValidResponse  " );

    }

}
