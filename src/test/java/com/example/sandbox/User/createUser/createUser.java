package com.example.sandbox.User.createUser;

import com.example.sandbox.Common;
import com.example.sandbox.util.Assertions;
import com.example.sandbox.util.body.pet.PostCreatePet;
import com.example.sandbox.util.body.user.PostCreateUser;
import com.example.sandbox.util.body.user.UserJsonBody;
import com.example.sandbox.util.swagger.definitions.PetBody;
import com.example.sandbox.util.swagger.definitions.UserBody;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.example.sandbox.util.Tools.generateRandomNumber;
import static com.example.sandbox.util.constans.Tags.SMOKE;
import static com.example.sandbox.util.constans.Tags.USER;

public class createUser extends Common {
    Assertions Assertions=new Assertions ();

    @Test (enabled = true, groups = {USER}, description = "testCreateUserValidResponse Test VALID Response Case",priority = 1)
    public void testCreateUserValidResponse() {
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

    @Test (enabled = true, groups = {USER}, description = "testCreateUserInValidResponse Test INVALID Response Case",priority = 2)
    public void testCreateUserInValidResponse() {
                Assertions.lineSeparatorStartEndLines ( 1 , " testCreateUserInValidResponse " );
                UserJsonBody UserJsonBody=new UserJsonBody ();
                PostCreateUser body = PostCreateUser.builder()
                        .UserBody ( UserBody.builder()

                                .build ()).build ();

                Response response=postUrl ( createUserSimple+"DD", UserJsonBody.createJsonBody3(body));
                Assert.assertNotNull(response);
                Assert.assertEquals( response.getStatusCode(),404);
                String contentType = response.getHeader("Content-Type");
                Assert.assertEquals(contentType, "application/xml", "Invalid content type");
                Assert.assertNotNull ( contentType, "The Content-Type  header is missing");

                String server = response.getHeader("Server");
                Assert.assertNotNull(server, "Server header is missing");
                Assert.assertEquals(server, "Jetty(9.2.9.v20150224)", "Invalid content server");

                String responseBody = response.getBody().asString();
                Assert.assertFalse(responseBody.isEmpty(), "Response body is empty");
                Assert.assertTrue ( responseBody.contains ( "/apiResponse"));

                Assertions.assertResponseTimeLessThan (response, 20000l );
                String headers = response.getHeader("Accept=*/*");
                Assert.assertEquals (  headers,null,"'Headers' field is not null!");

                int length = responseBody.length ();
                int expectedLength=171;
                Assert.assertEquals ( length,expectedLength,"Response length mismatch!" );

                Assertions.assertResponseBodyContains ( response,"http://petstore.swagger.io/v2/userDD" );
                Assertions.assertResponseTimeLessThan(response, 20000000L);

                Assertions.lineSeparatorStartEndLines ( 0 , " testCreateUserInValidResponse " );

    }


    }
