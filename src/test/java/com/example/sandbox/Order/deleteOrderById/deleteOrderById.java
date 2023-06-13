package com.example.sandbox.Order.deleteOrderById;

import com.example.sandbox.Common;
import com.example.sandbox.util.Assertions;
import com.example.sandbox.util.body.store.PostCreateStore;
import com.example.sandbox.util.body.store.StoreJSonBody;
import com.example.sandbox.util.swagger.definitions.StoreBody;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.example.sandbox.util.constans.Tags.ORDER;

public class deleteOrderById  extends Common {
    Assertions Assertions=new Assertions ();
    @Test ( enabled = true, groups = {ORDER}, description = "Creating a new Order to Delete ", priority = 1 )
    public void testCreatingNewOrderToDelete ( ) {
        Assertions.lineSeparatorStartEndLines ( 1 , "testCreatingNewOrderToDelete " );

        PostCreateStore bodyStore = PostCreateStore.builder ()
                .StoreBody ( StoreBody.builder ()
                        .id ( 111154 )
                        .petId ( 1542 )
                        .quantity ( 1 )
                        .shipDate ( "2024-06-12T16:59:30.181Z" )
                        .status ( "placed" )
                        .complete ( false )
                        .build () ).build ();
        StoreJSonBody storeJSonBody = new StoreJSonBody ();
        Response response = postUrl ( order , storeJSonBody.createJsonBody2 ( bodyStore ) );
        Assert.assertNotNull ( response );
        Assert.assertEquals ( 200 , response.getStatusCode () );
        Assert.assertFalse(response.getBody ().asString ().isEmpty(), "Response body is empty");
        String contentType = response.getHeader("Content-Type");
        Assert.assertEquals(contentType, "application/json", "Invalid content type");
        Assert.assertNotNull ( contentType, "The Content-Type  header is missing");

        String server = response.getHeader("Server");
        Assert.assertNotNull(server, "Server header is missing");
        Assert.assertEquals(server, "Jetty(9.2.9.v20150224)", "Invalid content server");
        String responseBody = response.getBody().asString();
        Assertions.assertResponseTimeLessThan (response, 20000l );
        String headers = response.getHeader("Accept=*/*");
        Assert.assertEquals (  headers,null,"'Headers' field is not null!");
        Assertions.lineSeparatorStartEndLines ( 1 , "testCreatingNewOrderToDelete " );


    }

    @Test (enabled = true,groups = {ORDER},description ="Deleting Order  VALID Response Case",priority = 2)
    public void TestDeletingOrderValidResponse(){
        Assertions.lineSeparatorStartEndLines ( 1,"TestDeletingOrderValidResponse  " );


        Response response = deleteOrderById ("111154");
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
        Assert.assertEquals ( message,"111154","The 'message'  field is not 'message' " );


        Assertions.lineSeparatorStartEndLines ( 0," TestDeletingOrderValidResponse " );

    }

    @Test (enabled = true,groups = {ORDER},description ="Deleting Order INVALID Response Case",priority = 3)
    public void TestDeletingOrderInValidResponse(){
        Assertions.lineSeparatorStartEndLines ( 1,"TestDeletingOrderInValidResponse  " );


        Response response = deleteOrderById (null);
        Assert.assertEquals(response.getStatusCode(), 404, "Invalid response code");


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
        Assert.assertEquals ( code,"404","The 'code'  field is not 404" );

        String type=response.jsonPath ().getString ( "type" );
        Assert.assertEquals ( type,"unknown","The 'type'  field is not unknown" );

        String message=response.jsonPath ().getString ( "message" );
        Assert.assertEquals ( message,"java.lang.NumberFormatException: For input string: \"null\"","The 'message'  field is not 'java.lang.NumberFormatException: For input string: \\\"null\\\"' " );
        Assertions.lineSeparatorStartEndLines ( 0,"TestDeletingOrderInValidResponse  " );

    }}
