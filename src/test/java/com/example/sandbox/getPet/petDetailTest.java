package com.example.sandbox.getPet;

import com.example.sandbox.Common;
import com.example.sandbox.util.Assertions;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;
import java.util.TreeMap;

import static com.example.sandbox.util.constans.Tags.SMOKE;

public class petDetailTest extends Common {
Assertions Assertions=new Assertions ();
    @Test(enabled = true,groups = {SMOKE},description ="petDetailTest  VALID Response Case",priority = 1)
    public void TestValidResponse(){
        Assertions.lineSeparatorStartEndLines ( 1,"TestValidResponse petDetailTest " );
        Map<String, String> queryParams = new TreeMap<>();
        queryParams.put("status", "sold");


        Response response = getUrl(findByStatus, queryParams);

        Assert.assertEquals(response.getStatusCode(), 200, "Invalid response code");

        JsonPath jsonPath = response.jsonPath();

        String contentType = response.getHeader("Content-Type");
        Assert.assertEquals(contentType, "application/json", "Invalid content type");
        Assert.assertNotNull ( contentType, "The Content-Type  header is missing");

        String server = response.getHeader("Server");
        Assert.assertNotNull(server, "Server header is missing");
        Assert.assertEquals(server, "Jetty(9.2.9.v20150224)", "Invalid content server");


        String responseBody = response.getBody().asString();
        Assert.assertFalse(responseBody.isEmpty(), "Response body is empty");
        Assert.assertTrue ( responseBody.contains ( "sold"));



        String id = jsonPath.getString("id");
        Assert.assertNotNull(id, "Pet ID is null");

        String name = jsonPath.getString("name");
        Assert.assertNotNull(name, "Pet name is null");

        String expectedId="[12, 9223372036854604567, 822922, 68, 13]";
        String expectedName="[null, doggie, doggie, bagworm, null]";

        if ( id.contains ( expectedId ) ){
        Assert.assertEquals (id, expectedId, "Invalid ID");} else if ( name.contains (expectedName) ) {
        Assert.assertEquals(name, expectedName, "Invalid pet name");}

        String expectedStatus="[sold, sold, sold, sold]";
        if ( queryParams.containsValue ( expectedStatus ) ){
        Assert.assertEquals(jsonPath.getString("status"), "sold", "Invalid status");}

        if ( jsonPath.getString ( "category.name" ).contains ( "[dogs, nulla quis pariatur, irure laborum id proident incididunt, bagworm, cats,cats]" ) ){
        Assert.assertEquals(jsonPath.getString("category.name"), "[dogs, nulla quis pariatur, irure laborum id proident incididunt, bagworm, cats,cats]", "Invalid category name");}

        if ( jsonPath.getString ( "category.id" ).contains ( "[0, -4630181, -69794609, 68, 0]" ) ){
        Assert.assertEquals(jsonPath.getString("category.id"), "[0, -4630181, -69794609, 68, 0]", "Invalid category ID");}
        Assert.assertNotEquals (jsonPath.getBoolean("available"), true, "Invalid availability");

        Assertions.assertResponseTimeLessThan (response, 20000l );
        String headers = response.getHeader("Accept=*/*");
        Assert.assertEquals (  headers,null,"'Headers' field is not null!");


        Assert.assertFalse(responseBody.isEmpty(), "Response body is empty");
        Assertions.lineSeparatorStartEndLines ( 0,"TestValidResponse petDetailTest " );

    }

    @Test(enabled = true,groups = {SMOKE},description ="petDetailTest  INVALID Response Case",priority = 2)
    public void TestInvalidResponse(){

        Assertions.lineSeparatorStartEndLines ( 1,"TestInValidResponse petDetailTest " );
        Map<String, String> queryParams = new TreeMap<>();
        queryParams.put("status","sold");


        Response response = getUrl(newPet, queryParams);

        Assert.assertEquals(response.getStatusCode(), 405, "Invalid response code");

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
        int expectedLength=102;
        Assert.assertEquals ( length,expectedLength,"Response length mismatch!" );

        Assertions.assertResponseTimeLessThan(response, 20000000L);
        Assertions.lineSeparatorStartEndLines ( 0,"TestInValidResponse petDetailTest " );


    }


}
