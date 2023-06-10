package com.example.sandbox.getPetById;

import com.example.sandbox.Common;
import com.example.sandbox.util.Assertions;
import com.example.sandbox.util.body.pet.PostCreatePet;
import com.example.sandbox.util.swagger.definitions.Item;
import com.example.sandbox.util.swagger.definitions.PetBody;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Map;
import java.util.TreeMap;
import static com.example.sandbox.util.body.pet.JsonBody.createJsonBody;
import static com.example.sandbox.util.constans.Tags.SMOKE;
import static com.example.sandbox.util.constans.TestData.HYDRAIMAGE;


public class petByIdTest extends Common {
    com.example.sandbox.util.Assertions Assertions=new Assertions ();

    @Test(enabled = true, groups = {SMOKE}, description = "petByIdTest Test createPetForUsageTest ",priority = 1)
    public void createPetForUsageTest() {
        Assertions.lineSeparatorStartEndLines ( 1,"petByIdTest Test createPetForUsageTest" );

        PostCreatePet body = PostCreatePet.builder()
                .PetBody( PetBody.builder()
                        .id(1542)
                        .category( Item.builder()
                                .id(1)
                                .name("Hydra")
                                .build())
                        .name("Princess")
                        .photoUrl(HYDRAIMAGE)
                        .tag(Item.builder()
                                .id(2)
                                .name("cute")
                                .build())
                        .status("available")
                        .build()
                ).build();




        Response response = postUrl(newPet, createJsonBody(body));
        Assert.assertEquals(response.getStatusCode(), 200, "Invalid response code");
        Assertions.lineSeparatorStartEndLines ( 0,"petByIdTest Test createPetForUsageTest" );}

    @Test (enabled = true,groups = {SMOKE},description ="petByIdTest  VALID Response Case",priority = 2)
    public void TestValidResponse(){
        Assertions.lineSeparatorStartEndLines ( 1,"TestValidResponse petByIdTest " );
        Map<String, String> queryParams = new TreeMap<>();
        queryParams.put("status", "available");
        Response response=getUrlPetID ( "1542" );
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
        Assert.assertTrue ( responseBody.contains ( "available"));



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

        Assertions.assertResponseTimeLessThan (response, 20000l );
        String headers = response.getHeader("Accept=*/*");
        Assert.assertEquals (  headers,null,"'Headers' field is not null!");


        Assert.assertFalse(responseBody.isEmpty(), "Response body is empty");
        Assertions.lineSeparatorStartEndLines ( 0,"TestValidResponse petByIdTest " );

    }

    @Test(enabled = true,groups = {SMOKE},description ="petDetailTest  INVALID Response Case",priority = 3)
    public void TestInvalidResponse(){
        Assertions.lineSeparatorStartEndLines ( 1,"Test InValidResponse petByIdTest " );
        Map<String, String> queryParams = new TreeMap<>();
        queryParams.put("status", "available");
        Response response=getUrlPetID ( "null" );

         Assert.assertEquals(response.getStatusCode(), 404, "Invalid response code");

        JsonPath jsonPath = response.jsonPath();
        String type=jsonPath.getString ( "type" );
        Assert.assertEquals ( type,"unknown","The type field is incorect!" );

        String contentType = response.getHeader("Content-Type");
        Assert.assertEquals(contentType, "application/json", "Invalid content type");
        Assert.assertNotNull ( contentType, "The Content-Type  header is missing");

         String server = response.getHeader("Server");
        Assert.assertNotNull(server, "Server header is missing");
        Assert.assertEquals(server, "Jetty(9.2.9.v20150224)", "Invalid content server");


        String responseBody = response.getBody().asString();
        Assert.assertFalse(responseBody.isEmpty(), "Response body is empty");
        Assert.assertTrue ( responseBody.contains ( "java.lang.NumberFormatException: For input string: \\\"null\\"));

        String responseMessage=jsonPath.getString ( "message" );
        Assert.assertEquals ( responseMessage,"java.lang.NumberFormatException: For input string: \"null\"","The  message is incorect!" );
        Assertions.assertResponseTimeLessThan (response, 20000l );

        String headers = response.getHeader("Accept=*/*");
        Assert.assertEquals (  headers,null,"'Headers' field is not null!");

        int length = responseBody.length ();
        int expectedLength=101;
        Assert.assertEquals ( length,expectedLength,"Response length mismatch!" );

        Assertions.assertResponseTimeLessThan(response, 20000000L);
        Assertions.lineSeparatorStartEndLines ( 0,"Test InValidResponse petByIdTest " );

}}
