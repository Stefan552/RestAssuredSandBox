package com.example.sandbox.postNewOrderForPet;

import com.example.sandbox.Common;
import com.example.sandbox.util.Assertions;
import com.example.sandbox.util.body.store.PostCreateStore;
import com.example.sandbox.util.body.store.StoreJSonBody;
import com.example.sandbox.util.swagger.definitions.StoreBody;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static com.example.sandbox.util.constans.Tags.ORDER;

public class postNewOrderForPet extends Common {
    Assertions Assertions = new Assertions ();

    @Test ( enabled = true, groups = {ORDER}, description = "ValidResponse Placing NewOrder at the  Store", priority = 1 )
    public void testValidResponsePlacingNewOrder ( ) {
        Assertions.lineSeparatorStartEndLines ( 1 , "testValidResponsePlacingNewOrder " );

        PostCreateStore bodyStore=PostCreateStore.builder ()
                .StoreBody ( StoreBody.builder ()
                        .id ( 14521 )
                        .petId ( 1542 )
                        .quantity ( 1 )
                        .shipDate ( "2023-06-12T16:59:30.181Z" )
                        .status ( "placed" )
                        .complete ( true )
                        .build ()).build ();
        StoreJSonBody storeJSonBody=new StoreJSonBody ();
        Response response = postUrl ( order,storeJSonBody.createJsonBody2 ( bodyStore ));
        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getStatusCode());

        String id = response.jsonPath().get("id").toString();
        Assert.assertNotNull(id, " ID is not Null");

        String petId = response.jsonPath().get("petId").toString ();
        Assert.assertEquals(petId, "1542", "Invalid pet ID");

        Assertions.assertResponseHasHeader(response, "Content-Type");
        Assertions.assertResponseTimeLessThan(response, 200000000L);

        String petStatus = response.jsonPath().get("status");
        Assert.assertEquals(petStatus, "placed", "Invalid Status");
        String shipDate = response.jsonPath().get("shipDate");
        Assert.assertEquals(shipDate, "2023-06-12T16:59:30.181+0000", "Invalid shipDate");
        String completed = response.jsonPath().get("complete").toString ();
        Assert.assertEquals ( completed,"true","The order is not completed ");

        String quantity = response.jsonPath().get("quantity").toString ();
        Assert.assertEquals(quantity, "1", "Invalid quantity");
        Assert.assertTrue(response.body().asString().contains(quantity), "Quantity field not found in response");

        Assert.assertTrue(response.body().asString().contains("placed"), "Order status 'placed' not found in response");

        Assert.assertFalse(response.body().asString().contains("error"), "Response body contains error");

        Assert.assertFalse(response.body().asString().isEmpty(), "Response body is empty");

        Assertions.assertResponseHasHeader(response, "Server");
        String server = response.getHeader("Server");
        Assert.assertNotNull(server, "Server header is missing");
        Assert.assertEquals(server, "Jetty(9.2.9.v20150224)", "Invalid content server");

        Assertions.assertResponseStatus(response, "placed");

        Assertions.assertResponseBodyContains(response, "shipDate");

        Assertions.assertResponseBodyHasKey(response, "petId");
        String contentType = response.getHeader("Content-Type");
        Assert.assertEquals(contentType, "application/json", "Invalid content type");
        Assert.assertNotNull ( contentType, "The Content-Type  header is missing");

        Assertions.assertReturnCode ( response,200 );

        Assertions.lineSeparatorStartEndLines ( 0 , "testValidResponsePlacingNewOrder " );

    }

    @Test ( enabled = true, groups = {ORDER}, description = "INValidResponse Placing NewOrder at the  Store", priority = 2 )
    public void testInvalidResponsePlacingNewOrder ( ) {
        Assertions.lineSeparatorStartEndLines ( 1 , "testINValidResponsePlacingNewOrder " );

        PostCreateStore bodyStore=PostCreateStore.builder ()
                .build ();
        StoreJSonBody storeJSonBody=new StoreJSonBody ();
        Response response = postUrl ( order,storeJSonBody.createJsonBody2 ( bodyStore ));
        Assert.assertNotNull(response);
        Assert.assertEquals(400, response.getStatusCode());

        String code = response.jsonPath().get("code").toString();
        Assert.assertNotNull(code, "The code field is not Null");

        String message = response.jsonPath().get("message").toString();
        Assert.assertNotNull(message, " The message field is not Null");

        String type = response.jsonPath().get("type").toString ();
        Assert.assertEquals(type, "error", "Invalid type text field");

        Assertions.assertResponseHasHeader(response, "Content-Type");
        Assertions.assertResponseTimeLessThan(response, 200000000L);

        Assert.assertEquals(message, "No data", "Invalid message");

        Assert.assertEquals(code, "1", "Invalid code message");


        Assert.assertTrue(response.body().asString().contains(code), "Code field not found in response");

        Assert.assertTrue(response.body().asString().contains("No data"), "Text field 'No data ' not found in response");

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
        Assertions.lineSeparatorStartEndLines ( 0 , "testValidResponsePlacingNewOrder " );

    }
}