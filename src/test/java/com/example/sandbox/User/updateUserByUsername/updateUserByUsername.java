package com.example.sandbox.User.updateUserByUsername;

import com.example.sandbox.Common;
import com.example.sandbox.util.Assertions;
import com.example.sandbox.util.body.user.PostCreateUser;
import com.example.sandbox.util.body.user.UserJsonBody;
import com.example.sandbox.util.swagger.definitions.UserBody;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.example.sandbox.util.constans.Tags.ORDER;

public class updateUserByUsername extends Common {

    Assertions Assertions=new Assertions ();

    @Test (enabled = true,groups = {ORDER},description ="TestUpdateByUsernameValidResponse",priority = 1)
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

    @Test (enabled = true,groups = {ORDER},description ="TestUpdateByUsernameINValidResponse",priority = 2)
    public void TestUpdateByUsernameinValidResponse() {
        Assertions.lineSeparatorStartEndLines ( 1 , "TestUpdateByUsernameinValidResponse " );

        PostCreateUser body = PostCreateUser.builder()
                .build ();
        UserJsonBody userJsonBody=new UserJsonBody ();

        Response response=updateUserByName ("Lionell Hastighy",userJsonBody.createJsonBody3(body) );
        Assert.assertNotNull(response);
        Assert.assertEquals( response.getStatusCode(),405);

        String code = response.jsonPath().get("code").toString();
        Assert.assertNotNull(code, "The code field is  Null");
        Assert.assertEquals ( code,"405","The code field has invalid value" );

        String message = response.jsonPath().get("message").toString();
        Assert.assertNotNull(message, " The message field is  Null");
        Assert.assertEquals ( message,"no data","Invalid value for 'message' field" );

        String type = response.jsonPath().get("type").toString ();
        Assert.assertEquals(type, "unknown", "Invalid type text field");
        Assert.assertNotNull(type, " The type field is  Null");

        Assertions.assertResponseHasHeader(response, "Content-Type");
        Assertions.assertResponseTimeLessThan(response, 200000000L);

        Assert.assertTrue(response.body().asString().contains(code), "Code field not found in response");

        Assert.assertTrue(response.body().asString().contains("no data"), "Text field 'User not found ' not found in response");

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
        Assertions.lineSeparatorStartEndLines ( 0 , "TestUpdateByUsernameinValidResponse " );

    }
    }
