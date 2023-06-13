package com.example.sandbox.Order.OrderLifeCycle;

import com.example.sandbox.Common;
import com.example.sandbox.util.Assertions;
import com.example.sandbox.util.body.store.PostCreateStore;
import com.example.sandbox.util.body.store.StoreJSonBody;
import com.example.sandbox.util.swagger.definitions.StoreBody;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.example.sandbox.util.constans.Tags.ORDER;

public class orderLifeCycle extends Common {
Assertions Assertions=new Assertions ();

    @Test ( enabled = true, groups = {ORDER}, description = "ValidResponse Placing NewOrder at the  Store", priority = 1 )
    public void TestPlacingNewOrder ( ) {
        Assertions.lineSeparatorStartEndLines ( 1 , "testValidResponsePlacingNewOrder " );

        PostCreateStore bodyStore = PostCreateStore.builder ()
                .StoreBody ( StoreBody.builder ()
                        .id ( 14521 )
                        .petId ( 1542 )
                        .quantity ( 1 )
                        .shipDate ( "2023-06-12T16:59:30.181Z" )
                        .status ( "placed" )
                        .complete ( true )
                        .build () ).build ();
        StoreJSonBody storeJSonBody = new StoreJSonBody ();
        Response response = postUrl ( order , storeJSonBody.createJsonBody2 ( bodyStore ) );
        Assert.assertNotNull ( response );
        Assert.assertEquals ( response.getStatusCode () , 200 );

        String id = response.jsonPath ().get ( "id" ).toString ();
        Assert.assertNotNull ( id , " ID is not Null" );

        String petId = response.jsonPath ().get ( "petId" ).toString ();
        Assert.assertEquals ( petId , "1542" , "Invalid pet ID" );

        Assertions.assertResponseHasHeader ( response , "Content-Type" );
        Assertions.assertResponseTimeLessThan ( response , 200000000L );

        String petStatus = response.jsonPath ().get ( "status" );
        Assert.assertEquals ( petStatus , "placed" , "Invalid Status" );
        String shipDate = response.jsonPath ().get ( "shipDate" );
        Assert.assertEquals ( shipDate , "2023-06-12T16:59:30.181+0000" , "Invalid shipDate" );
        String completed = response.jsonPath ().get ( "complete" ).toString ();
        Assert.assertEquals ( completed , "true" , "The order is not completed " );

        String quantity = response.jsonPath ().get ( "quantity" ).toString ();
        Assert.assertEquals ( quantity , "1" , "Invalid quantity" );
        Assert.assertTrue ( response.body ().asString ().contains ( quantity ) , "Quantity field not found in response" );

        Assert.assertTrue ( response.body ().asString ().contains ( "placed" ) , "Order status 'placed' not found in response" );

        Assert.assertFalse ( response.body ().asString ().contains ( "error" ) , "Response body contains error" );

        Assert.assertFalse ( response.body ().asString ().isEmpty () , "Response body is empty" );

        Assertions.assertResponseHasHeader ( response , "Server" );
        String server = response.getHeader ( "Server" );
        Assert.assertNotNull ( server , "Server header is missing" );
        Assert.assertEquals ( server , "Jetty(9.2.9.v20150224)" , "Invalid content server" );

        Assertions.assertResponseStatus ( response , "placed" );

        Assertions.assertResponseBodyContains ( response , "shipDate" );

        Assertions.assertResponseBodyHasKey ( response , "petId" );
        String contentType = response.getHeader ( "Content-Type" );
        Assert.assertEquals ( contentType , "application/json" , "Invalid content type" );
        Assert.assertNotNull ( contentType , "The Content-Type  header is missing" );
        Assertions.lineSeparatorStartEndLines ( 0 , "testValidResponsePlacingNewOrder " );

    }

    @Test ( enabled = true, groups = {ORDER}, description = "TestGetOrderByIdValidResponse Valid Response Case", priority = 2 )
    public void TestGetOrderById ( ) {
        Assertions.lineSeparatorStartEndLines ( 1 , "TestGetOrderByIdValidResponse " );
        Response response = getOrderById ( "14521" );

        Assert.assertNotNull ( response );
        Assert.assertEquals ( response.getStatusCode () , 200 );

        String id = response.jsonPath ().get ( "id" ).toString ();
        Assert.assertNotNull ( id , " ID is not Null" );

        String petId = response.jsonPath ().get ( "petId" ).toString ();
        Assert.assertEquals ( petId , "1542" , "Invalid pet ID" );

        Assertions.assertResponseHasHeader ( response , "Content-Type" );
        Assertions.assertResponseTimeLessThan ( response , 200000000L );

        String petStatus = response.jsonPath ().get ( "status" );
        Assert.assertEquals ( petStatus , "placed" , "Invalid Status" );
        String shipDate = response.jsonPath ().get ( "shipDate" );
        Assert.assertEquals ( shipDate , "2023-06-12T16:59:30.181+0000" , "Invalid shipDate" );
        String completed = response.jsonPath ().get ( "complete" ).toString ();
        Assert.assertEquals ( completed , "true" , "The order is not completed " );

        String quantity = response.jsonPath ().get ( "quantity" ).toString ();
        Assert.assertEquals ( quantity , "1" , "Invalid quantity" );
        Assert.assertTrue ( response.body ().asString ().contains ( quantity ) , "Quantity field not found in response" );

        Assert.assertTrue ( response.body ().asString ().contains ( "placed" ) , "Order status 'placed' not found in response" );

        Assert.assertFalse ( response.body ().asString ().contains ( "error" ) , "Response body contains error" );

        Assert.assertFalse ( response.body ().asString ().isEmpty () , "Response body is empty" );

        Assertions.assertResponseHasHeader ( response , "Server" );
        String server = response.getHeader ( "Server" );
        Assert.assertNotNull ( server , "Server header is missing" );
        Assert.assertEquals ( server , "Jetty(9.2.9.v20150224)" , "Invalid content server" );

        Assertions.assertResponseStatus ( response , "placed" );

        Assertions.assertResponseBodyContains ( response , "shipDate" );

        Assertions.assertResponseBodyHasKey ( response , "petId" );
        String contentType = response.getHeader ( "Content-Type" );
        Assert.assertEquals ( contentType , "application/json" , "Invalid content type" );
        Assert.assertNotNull ( contentType , "The Content-Type  header is missing" );
        Assertions.lineSeparatorStartEndLines ( 0 , "TestGetOrderByIdValidResponse " );

    }

    @Test (enabled = true,groups = {ORDER},description ="Deleting Order  VALID Response Case",priority = 3)
    public void TestDeletingOrder(){
        Assertions.lineSeparatorStartEndLines ( 1,"TestDeletingOrderValidResponse  " );

        Response response = deleteOrderById ("14521");
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
        Assert.assertEquals ( message,"14521","The 'message'  field is not 'message' " );


        Assertions.lineSeparatorStartEndLines ( 0," TestDeletingOrderValidResponse " );

    }

}