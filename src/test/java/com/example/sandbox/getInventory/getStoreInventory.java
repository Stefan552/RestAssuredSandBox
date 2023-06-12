package com.example.sandbox.getInventory;

import com.example.sandbox.Common;
import com.example.sandbox.util.Assertions;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.example.sandbox.util.constans.Tags.ORDER;

public class getStoreInventory extends Common {
    Assertions Assertions= new Assertions ();

    @Test ( enabled = true, groups = {ORDER}, description = "ValidResponse Getting New Inventory", priority = 1 )
    public void testValidResponseGettinNewInventory ( ) {
        Assertions.lineSeparatorStartEndLines ( 1 , "testValidResponseGettinNewInventory " );

        Response response = getUrl ( inventory );
        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getStatusCode());

        String id = response.jsonPath().get("sold").toString();
        Assert.assertNotNull(id, " Sold is not Null");

        Assertions.assertResponseHasHeader(response, "Content-Type");
        Assertions.assertResponseTimeLessThan(response, 200000000L);

        String VL4EQE = response.jsonPath().get("VL4EQE").toString ();
        Assertions.assertResponseBodyContains ( response,VL4EQE );

        String string = response.jsonPath().get("string").toString ();
        Assertions.assertResponseBodyContains ( response,string);

        String availability = response.jsonPath().get("\"available\"").toString ();
        Assertions.assertResponseBodyContains ( response,availability);

        String pending = response.jsonPath().get("pending").toString ();
        Assertions.assertResponseBodyContains ( response,pending );

        String available = response.jsonPath().get("available").toString ();
        Assertions.assertResponseBodyContains ( response,available );

        String peric = response.jsonPath().get("peric").toString ();
        Assertions.assertResponseBodyContains ( response,peric );

        Assertions.assertResponseHasHeader(response, "Server");
        String server = response.getHeader("Server");
        Assert.assertNotNull(server, "Server header is missing");
        Assert.assertEquals(server, "Jetty(9.2.9.v20150224)", "Invalid content server");
        Assertions.assertResponseBodyHasKey(response, "peric");
        String contentType = response.getHeader("Content-Type");
        Assert.assertEquals(contentType, "application/json", "Invalid content type");
        Assert.assertNotNull ( contentType, "The Content-Type  header is missing");

        Assertions.lineSeparatorStartEndLines ( 0 , "testValidResponseGettinNewInventory " );


    }
    @Test ( enabled = true, groups = {ORDER}, description = "ValidResponse Getting New Inventory", priority = 1 )
    public void testInValidResponseGettinNewInventory ( ) {
        Assertions.lineSeparatorStartEndLines ( 1 , "testInValidResponseGettinNewInventory " );

        Response response = getUrl ( inventory+22 );
        Assert.assertNotNull(response);
        Assert.assertEquals(404, response.getStatusCode());

        JsonPath jsonPath = response.jsonPath();

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
        Assertions.lineSeparatorStartEndLines ( 0, "testInValidResponseGettinNewInventory " );

    }
}
