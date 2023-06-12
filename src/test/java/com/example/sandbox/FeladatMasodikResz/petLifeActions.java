package com.example.sandbox.FeladatMasodikResz;

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


public class petLifeActions extends Common {
    com.example.sandbox.util.Assertions Assertions=new Assertions ();

    @Test(enabled = true, groups = {SMOKE}, description = " Test createPetForUsageTest ",priority = 1)
    public void createPetForUsageTest() {
        Assertions.lineSeparatorStartEndLines ( 1," Test createPetForUsageTest" );

        PostCreatePet body = PostCreatePet.builder()
                .PetBody( PetBody.builder()
                        .id(1542)
                        .category( Item.builder()
                                .id(1542)
                                .name("Hydra")
                                .build())
                        .name("Princess")
                        .photoUrl(HYDRAIMAGE)
                        .tag(Item.builder()
                                .id(1542)
                                .name("cute")
                                .build())
                        .status("available")
                        .build()
                ).build();

        Response response = postUrl(newPet, createJsonBody(body));
        Assert.assertEquals(response.getStatusCode(), 200, "Invalid response code");
        Assertions.lineSeparatorStartEndLines ( 0," Test createPetForUsageTest" );}

    @Test (enabled = true,groups = {SMOKE},description ="TestValidResponseFindPet  VALID Response Case",priority = 2)
    public void TestValidResponseFindPet(){
        Assertions.lineSeparatorStartEndLines ( 1,"TestValidResponse FindPet " );
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
        Assertions.lineSeparatorStartEndLines ( 0,"TestValidResponse FindPet " );

    }

    @Test(enabled = true,groups = {SMOKE},description ="TestInvalidResponseFindPet  INVALID Response Case",priority = 3)
    public void TestInvalidResponseFindPet(){
        Assertions.lineSeparatorStartEndLines ( 1,"Test InValidResponse FindPet " );
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
        Assertions.lineSeparatorStartEndLines ( 0,"Test InValidResponse FindPet " );

}

    @Test (enabled = true,groups = {SMOKE},description ="TestValiUpdatePet  VALID Response Case",priority = 4)
    public void TestValiUpdatePet() {
        Assertions.lineSeparatorStartEndLines ( 1 , "Test TestValiUpdatePet  " );
        PostCreatePet body = PostCreatePet.builder()
                .PetBody(PetBody.builder()
                        .id(1542)
                        .category(Item.builder()
                                .id(1542)
                                .name("Anne")
                                .build())
                        .name("Hoardig")
                        .photoUrl(HYDRAIMAGE)
                        .tag(Item.builder()
                                .id(1542)
                                .name("Hoardig2")
                                .build())
                        .status("sold")
                        .build()
                ).build();

        Response response = updatePet ( createJsonBody(body));
        Assert.assertEquals(response.getStatusCode(), 200, "Invalid response code");

        String id = response.jsonPath().get("id").toString();
        Assert.assertNotNull(id, "Pet ID is not Null");

        String petName = response.jsonPath().get("name");
        Assert.assertEquals(petName, "Hoardig", "Invalid pet name");

        Assertions.assertResponseHasHeader(response, "Content-Type");
        Assertions.assertResponseTimeLessThan(response, 200000000L);

        String petStatus = response.jsonPath().get("status");
        Assert.assertEquals(petStatus, "sold", "Invalid Status");

        Object tags = response.jsonPath().get("tags").toString();
        String expectedTags = "[{id=1542, name=Hoardig2}]";
        Assert.assertEquals(tags, expectedTags, "Invalid tags");

        Object category = response.jsonPath().get("category").toString();
        String expectedCategories = "{id=1542, name=Anne}";
        Assert.assertEquals(category, expectedCategories, "Invalid category");

        Object photoUrl = response.jsonPath().get("photoUrls").toString();
        String expectedPhotoUrls = "[https://gods-and-demons.fandom.com/wiki/Lernaean_Hydra?file=Venture_the_fog_hydra_by_darkcloud013_dbpdkjn-fullview.jpg]";
        Assert.assertEquals(photoUrl, expectedPhotoUrls, "Invalid photoUrls");

        int petId = Integer.parseInt(id);
        Assert.assertTrue(petId > 0, "Invalid pet ID");

        int categoryId = response.jsonPath().get("category.id");
        Assert.assertEquals(categoryId, 1542, "Invalid category ID");

        String categoryName = response.jsonPath().get("category.name");
        Assert.assertEquals(categoryName, "Anne", "Invalid category name");

        String tagName = response.jsonPath().get("tags[0].name");
        Assert.assertEquals(tagName, "Hoardig2", "Invalid tag name");

        int tagId = response.jsonPath().get("tags[0].id");
        Assert.assertEquals(tagId, 1542, "Invalid tag ID");

        Assert.assertTrue(response.body().asString().contains(HYDRAIMAGE), "Photo URL not found in response");
        Assert.assertTrue(response.body().asString().contains("sold"), "Pet status 'available' not found in response");
        Assert.assertFalse(response.body().asString().contains("error"), "Response body contains error");
        long responseTime = response.getTime();
        Assert.assertTrue(responseTime < 5000L, "Response time exceeds the expected limit of 5 seconds");
        Assert.assertFalse(response.body().asString().isEmpty(), "Response body is empty");
        Assertions.assertResponseHasHeader(response, "Server");
        String server = response.getHeader("Server");
        Assert.assertNotNull(server, "Server header is missing");
        Assert.assertEquals(server, "Jetty(9.2.9.v20150224)", "Invalid content server");
        Assertions.assertResponseStatus(response, "sold");
        Assertions.assertResponseBodyContains(response, "Hoardig2");
        Assertions.assertResponseBodyHasKey(response, "id");
        Assertions.lineSeparatorStartEndLines ( 0 , "Test TestValiUpdatePet  " );
    }

    @Test (enabled = true,groups = {SMOKE},description ="TestInValidUpdatePet  INVALID Response Case",priority = 5)
    public void TestInValidUpdatePet(){
            Assertions.lineSeparatorStartEndLines ( 1,"Test TestInValidResponseUpdatePet updateTestInvalid " );

             PostCreatePet body=   PostCreatePet.builder().build();

            Response response=updatePet (  createJsonBody ( body )  );
            response.getBody ().print ();
            Assert.assertEquals ( response.getStatusCode () , 405 , "Invalid response code" );

            Assertions.assertResponseHasHeader ( response , "Content-Type" );
            Assertions.assertResponseHasHeader ( response , "Date" );

            String contentType = response.getHeader ( "Content-Type" );
            Assert.assertFalse ( contentType.contains ( "charset" ) , "Charset not found" );

            Assert.assertNotNull ( response.getBody () , "Response body is null" );

            String responseBody = response.getBody ().asString ();
            Assert.assertFalse ( responseBody.isEmpty () , "Response body is empty" );

            Assert.assertTrue ( responseBody.contains ( "405" ) , "Response does not contain the '405'" );
            Assert.assertTrue ( responseBody.contains ( "code" ) , "Response does not contain the 'code' " );
            Assert.assertTrue ( responseBody.contains ( "type" ) , "Response does not contain 'type'" );
            Assert.assertTrue ( responseBody.contains ( "unknown" ) , "Response does not contain 'unknown'" );
            Assert.assertTrue ( responseBody.contains ( "message" ) , "Response does not contain 'message'" );
            Assert.assertTrue ( responseBody.contains ( "no data" ) , "Response does not contain 'no data'" );
            Assert.assertFalse ( responseBody.contains ( "Header1" ) , "Response does  contain Header1" );
            Assert.assertFalse ( responseBody.contains ( "charset" ) , "Response does  contain charset" );
            Assert.assertFalse ( responseBody.contains ( "body" ) , "Response does  contain body" );
            Assert.assertFalse ( responseBody.contains ( "Date" ) , "Response does  contain Date" );
            Assert.assertFalse ( responseBody.contains ( "Content-Type" ) , "Response does  contain Content-Type" );
            String server = response.getHeader ( "Server" );
            Assert.assertNotNull ( server , "Server header is missing" );
            Assert.assertEquals ( server , "Jetty(9.2.9.v20150224)" , "Invalid content server" );
            Assertions.assertResponseTimeLessThan ( response , 20000000L );
            String headers = response.getHeader("Accept=*/*");
            Assert.assertEquals (  headers,null,"'Headers' field is not null!");

            Assertions.lineSeparatorStartEndLines ( 1,"Test TestInValidResponseUpdatePet updateTestInvalid " );


    }

    @Test(enabled = true,groups = {SMOKE},description ="CheckForUpDates  VALID Response Case",priority = 6)
    public void TestCheckForUpdates(){
        Assertions.lineSeparatorStartEndLines ( 1,"TestValidResponse TestValidResponseCheckForUpdates " );
        Map<String, String> queryParams = new TreeMap<>();
        queryParams.put("id", "1542");

        Response response = getUrlPetID ("1542");

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
        String expectedId="1542";
        String expectedName="Hoardig";
        Assert.assertEquals ( id,expectedId,"Invalid id field" );
        Assert.assertEquals(name, expectedName, "Invalid pet name");
        Assert.assertEquals(jsonPath.getString("status"), "sold", "Invalid status");
        Assertions.assertResponseTimeLessThan (response, 20000l );
        String headers = response.getHeader("Accept=*/*");
        Assert.assertEquals (  headers,null,"'Headers' field is not null!");
        Assert.assertFalse(responseBody.isEmpty(), "Response body is empty");

        Assertions.lineSeparatorStartEndLines ( 0,"TestValidResponse TestValidResponseCheckForUpdates " );

    }

    @Test(enabled = true,groups = {SMOKE},description ="CheckForUpDates  INVALID Response Case",priority = 7)
    public void TestInvalidResponseCheckForUpdates(){

        Assertions.lineSeparatorStartEndLines ( 1,"TestInValidResponse TestInvalidResponseCheckForUpdates " );
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
        Assertions.lineSeparatorStartEndLines ( 0,"TestInValidResponse TestInvalidResponseCheckForUpdates " );


    }

    @Test(enabled = true,groups = {SMOKE},description ="DeletingPet  VALID Response Case",priority = 8)
    public void TestDeletingPet(){
        Assertions.lineSeparatorStartEndLines ( 1,"TestValidResponse TestDeletingPet " );


        Response response = deletePet ("1542");
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

        Assertions.lineSeparatorStartEndLines ( 0,"TestValidResponse TestDeletingPet " );

    }

    @Test(enabled = true,groups = {SMOKE},description ="CheckIfPetIsDeleted  VALID Response Case",priority = 9)
    public void TestCheckIfPetIsDeleted(){
        Assertions.lineSeparatorStartEndLines ( 1,"TestValidResponse Check IF The Pet Is Deleted " );

        Response response = getUrlPetID ("1542");
        Assert.assertEquals(response.getStatusCode(), 404, "Invalid response code");
        JsonPath jsonPath = response.jsonPath();

        String contentType = response.getHeader("Content-Type");
        Assert.assertEquals(contentType, "application/json", "Invalid content type");
        Assert.assertNotNull ( contentType, "The Content-Type  header is missing");

        String server = response.getHeader("Server");
        Assert.assertNotNull(server, "Server header is missing");
        Assert.assertEquals(server, "Jetty(9.2.9.v20150224)", "Invalid content server");


        String responseBody = response.getBody().asString();
        Assert.assertTrue ( responseBody.contains ( "error"));
        Assert.assertTrue ( responseBody.contains ( "Pet not found"));
        Assert.assertTrue ( responseBody.contains ( "code"));


        String message = jsonPath.getString("message");
        Assert.assertNotNull(message, "Response message is null");

        String type = jsonPath.getString("type");
        Assert.assertNotNull(type, "Response type is null");

        Assertions.assertResponseTimeLessThan (response, 20000l );

        String headers = response.getHeader("Accept=*/*");
        Assert.assertEquals (  headers,null,"'Headers' field is not null!");

        Assert.assertFalse(responseBody.isEmpty(), "Response body is empty");

        Assertions.lineSeparatorStartEndLines ( 0,"TestValidResponse Check IF The Pet Is Deleted " );

    }
}