package com.example.sandbox.User.createUserArray;

import com.example.sandbox.Common;
import com.example.sandbox.util.Assertions;
import com.example.sandbox.util.body.user.PostCreateUser;
import com.example.sandbox.util.body.user.UserJsonBody;
import com.example.sandbox.util.swagger.definitions.UserBody;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.example.sandbox.util.Tools.generateRandomNumber;
import static com.example.sandbox.util.constans.Tags.USER;

public class createListUserWithArray extends Common {
    com.example.sandbox.util.Assertions Assertions = new Assertions ();

    @Test ( enabled = true, groups = {USER}, description = "testCreateUserArrayValidResponse Test VALID Response Case", priority = 1 )
    public void testCreateUserArrayValidResponse ( ) {
        Assertions.lineSeparatorStartEndLines ( 1 , " testCreateUserArrayValidResponse " );

        Integer id = generateRandomNumber ();
        PostCreateUser body = PostCreateUser.builder ()
                .UserBody ( UserBody.builder ()
                        .id ( id )
                        .username ( "Lionell Hastighy" )
                        .firstName ( "Lionell" )
                        .lastName ( "Hastighy" )
                        .email ( "lionell145@gmail.com" )
                        .password ( "lion147" )
                        .phone ( "+1208945712" )
                        .userStatus ( 1 )
                        .build () ).build ();

        UserBody[] userBodies = new UserBody[1];
        userBodies[0] = body.getUserBody ();
        Response response = postUrl ( createWithArray , userBodies );
        Assert.assertNotNull ( response );
        Assert.assertEquals (  response.getStatusCode (),200 );
        String code = response.jsonPath ().get ( "code" ).toString ();
        Assert.assertNotNull ( code , " Code is not Null" );
        Assert.assertEquals ( code , "200" , "Invalid message" );

        String type = response.jsonPath ().get ( "type" ).toString ();
        Assert.assertNotNull ( type , " Type is not Null" );
        Assert.assertEquals ( type , "unknown" , "Invalid message" );

        String message = response.jsonPath ().get ( "message" ).toString ();
        Assert.assertNotNull ( message , " Message is not Null" );
        Assert.assertEquals ( message , "ok" , "Invalid message" );

        Assertions.assertResponseHasHeader ( response , "Content-Type" );
        Assertions.assertResponseTimeLessThan ( response , 200000000L );

        Assert.assertTrue ( response.body ().asString ().contains ( "code" ) , "Database response 'code' not found in response" );

        Assert.assertTrue ( response.body ().asString ().contains ( "type" ) , "Database response 'type' not found in response" );
        Assert.assertTrue ( response.body ().asString ().contains ( "message" ) , "Database response 'message' not found in response" );

        Assert.assertFalse ( response.body ().asString ().contains ( "id" ) , "Response body contains error" );

        Assert.assertFalse ( response.body ().asString ().isEmpty () , "Response body is empty" );

        Assertions.assertResponseHasHeader ( response , "Server" );
        String server = response.getHeader ( "Server" );
        Assert.assertNotNull ( server , "Server header is missing" );
        Assert.assertEquals ( server , "Jetty(9.2.9.v20150224)" , "Invalid content server" );

        String contentType = response.getHeader ( "Content-Type" );
        Assert.assertEquals ( contentType , "application/json" , "Invalid content type" );
        Assert.assertNotNull ( contentType , "The Content-Type  header is missing" );

        Assertions.lineSeparatorStartEndLines ( 0 , " testCreateUserArrayValidResponse " );
    }

    @Test ( enabled = true, groups = {USER}, description = "testCreateUserArrayINValidResponse Test INVALID Response Case", priority = 2 )
    public void testCreateUserArrayINValidResponse ( ) {
        Assertions.lineSeparatorStartEndLines ( 1 , " testCreateUserArrayINValidResponse " );

        Integer id = generateRandomNumber ();
        PostCreateUser body = PostCreateUser.builder ()
                .UserBody ( UserBody.builder ()
                        .id ( id )
                        .username ( "Lionell Hastighy" )
                        .firstName ( "Lionell" )
                        .lastName ( "Hastighy" )
                        .email ( "lionell145@gmail.com" )
                        .password ( "lion147" )
                        .phone ( "+1208945712" )
                        .userStatus ( 1 )
                        .build () ).build ();

        UserBody[] userBodies = new UserBody[5];
        userBodies[0] = body.getUserBody ();
        Response response = postUrl ( createWithArray , userBodies );
        Assert.assertNotNull ( response );
        Assert.assertEquals ( 500 , response.getStatusCode () );
        String contentType = response.getHeader("Content-Type");
        Assert.assertEquals(contentType, "application/json", "Invalid content type");
        Assert.assertNotNull ( contentType, "The Content-Type  header is missing");

        String server = response.getHeader("Server");
        Assert.assertNotNull(server, "Server header is missing");
        Assert.assertEquals(server, "Jetty(9.2.9.v20150224)", "Invalid content server");


        String responseBody = response.getBody().asString();
        Assert.assertFalse(responseBody.isEmpty(), "Response body is empty");


        Assertions.assertResponseTimeLessThan (response, 20000l );
        String headers = response.getHeader("Accept=*/*");
        Assert.assertEquals (  headers,null,"'Headers' field is not null!");

        int length = responseBody.length ();
        int expectedLength=64;
        Assert.assertEquals ( length,expectedLength,"Response length mismatch!" );


        String code = response.jsonPath ().get ( "code" ).toString ();
        Assert.assertNotNull ( code , " Code is not Null" );
        Assert.assertEquals ( code , "500" , "Invalid message" );

        String type = response.jsonPath ().get ( "type" ).toString ();
        Assert.assertNotNull ( type , " Type is not Null" );
        Assert.assertEquals ( type , "unknown" , "Invalid message" );

        String message = response.jsonPath ().get ( "message" ).toString ();
        Assert.assertNotNull ( message , " Message is not Null" );
        Assert.assertEquals ( message , "something bad happened" , "Invalid message" );

        Assertions.assertResponseHasHeader ( response , "Content-Type" );
        Assertions.assertResponseTimeLessThan ( response , 200000000L );

        Assert.assertTrue ( response.body ().asString ().contains ( "code" ) , "Database response 'code' not found in response" );

        Assert.assertTrue ( response.body ().asString ().contains ( "type" ) , "Database response 'type' not found in response" );
        Assert.assertTrue ( response.body ().asString ().contains ( "message" ) , "Database response 'message' not found in response" );

        Assert.assertFalse ( response.body ().asString ().contains ( "id" ) , "Response body contains error" );



    }
}