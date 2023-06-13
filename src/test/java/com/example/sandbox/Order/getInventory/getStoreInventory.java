package com.example.sandbox.Order.getInventory;

import com.example.sandbox.Common;
import com.example.sandbox.util.Assertions;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.example.sandbox.util.constans.Tags.ORDER;

public class getStoreInventory extends Common {
    Assertions Assertions= new Assertions ();

    @Test ( enabled = true, groups = {ORDER}, description = "ValidResponse Getting New Inventory", priority = 1 )
    public void testValidResponseGettingNewInventory ( ) {
        Assertions.lineSeparatorStartEndLines ( 1 , "testValidResponseGettingNewInventory " );

        Response response = getUrl ( inventory );
        Assert.assertNotNull(response);
        Assert.assertEquals( response.getStatusCode(),200);

        String id = response.jsonPath().get("sold").toString();
        Assert.assertNotNull(id, " Sold is not Null");

        Assertions.assertResponseHasHeader(response, "Content-Type");
        Assertions.assertResponseTimeLessThan(response, 200000000L);


        String string = response.jsonPath().get("string").toString ();
        Assertions.assertResponseBodyContains ( response,string);

        String availability = response.jsonPath().get("\"available\"").toString ();
        Assertions.assertResponseBodyContains ( response,availability);

        String pending = response.jsonPath().get("pending").toString ();
        Assertions.assertResponseBodyContains ( response,pending );

        String available = response.jsonPath().get("available").toString ();
        Assertions.assertResponseBodyContains ( response,available );



        Assertions.assertResponseHasHeader(response, "Server");
        String server = response.getHeader("Server");
        Assert.assertNotNull(server, "Server header is missing");
        Assert.assertEquals(server, "Jetty(9.2.9.v20150224)", "Invalid content server");

        String contentType = response.getHeader("Content-Type");
        Assert.assertEquals(contentType, "application/json", "Invalid content type");
        Assert.assertNotNull ( contentType, "The Content-Type  header is missing");

        Assertions.lineSeparatorStartEndLines ( 0 , "testValidResponseGettingNewInventory " );


    }
    @Test ( enabled = true, groups = {ORDER}, description = "ValidResponse Getting New Inventory", priority = 2 )
    public void testInValidResponseGettingNewInventory ( ) {
        Assertions.lineSeparatorStartEndLines ( 1 , "testInValidResponseGettingNewInventory " );

        Response response = getUrl ( inventory+22 );
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
        int expectedLength=182;
        Assert.assertEquals ( length,expectedLength,"Response length mismatch!" );

         Assertions.assertResponseBodyContains ( response,"http://petstore.swagger.io/v2/store/inventory22" );
        Assertions.assertResponseTimeLessThan(response, 20000000L);
        Assertions.lineSeparatorStartEndLines ( 0, "testInValidResponseGettingNewInventory " );

    }
}
