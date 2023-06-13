package com.example.sandbox.Pet.businessProcesses;

import com.example.sandbox.Common;
import com.example.sandbox.util.Assertions;
import com.example.sandbox.util.body.pet.PostCreatePet;
import com.example.sandbox.util.swagger.definitions.Item;
import com.example.sandbox.util.swagger.definitions.PetBody;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.example.sandbox.util.Tools.generateRandomNumber;
import static com.example.sandbox.util.body.pet.JsonBody.createJsonBody;
import static com.example.sandbox.util.constans.Tags.SMOKE;
import static com.example.sandbox.util.constans.TestData.HYDRAIMAGE;

public class PetLifeCycle extends Common  {
    Assertions Assertions=new Assertions ();
    @Test(enabled = true, groups = {SMOKE}, description = "PetLifeCycle Test VALID Response Case",priority = 1)
    public void testValidResponse() {
        Assertions.lineSeparatorStartEndLines ( 1,"TestValidResponse PetLifeCycle " );
        Integer randomNumber = generateRandomNumber();
        PostCreatePet body = PostCreatePet.builder()
                .PetBody(PetBody.builder()
                        .id(randomNumber)
                        .category(Item.builder()
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

        String id = response.jsonPath().get("id").toString();
        Assert.assertNotNull(id, "Pet ID is not Null");

        String petName = response.jsonPath().get("name");
        Assert.assertEquals(petName, "Princess", "Invalid pet name");

        Assertions.assertResponseHasHeader(response, "Content-Type");
        Assertions.assertResponseTimeLessThan(response, 200000000L);

        String petStatus = response.jsonPath().get("status");
        Assert.assertEquals(petStatus, "available", "Invalid Status");

        Object tags = response.jsonPath().get("tags").toString();
        String expectedTags = "[{id=2, name=cute}]";
        Assert.assertEquals(tags, expectedTags, "Invalid tags");

        Object category = response.jsonPath().get("category").toString();
        String expectedCategories = "{id=1, name=Hydra}";
        Assert.assertEquals(category, expectedCategories, "Invalid category");

        Object photoUrl = response.jsonPath().get("photoUrls").toString();
        String expectedPhotoUrls = "[https://gods-and-demons.fandom.com/wiki/Lernaean_Hydra?file=Venture_the_fog_hydra_by_darkcloud013_dbpdkjn-fullview.jpg]";
        Assert.assertEquals(photoUrl, expectedPhotoUrls, "Invalid photoUrls");

        int petId = Integer.parseInt(id);
        Assert.assertTrue(petId > 0, "Invalid pet ID");

        int categoryId = response.jsonPath().get("category.id");
        Assert.assertEquals(categoryId, 1, "Invalid category ID");

        String categoryName = response.jsonPath().get("category.name");
        Assert.assertEquals(categoryName, "Hydra", "Invalid category name");

        String tagName = response.jsonPath().get("tags[0].name");
        Assert.assertEquals(tagName, "cute", "Invalid tag name");

        int tagId = response.jsonPath().get("tags[0].id");
        Assert.assertEquals(tagId, 2, "Invalid tag ID");

        Assert.assertTrue(response.body().asString().contains(HYDRAIMAGE), "Photo URL not found in response");

        Assert.assertTrue(response.body().asString().contains("available"), "Pet status 'available' not found in response");

        Assert.assertFalse(response.body().asString().contains("error"), "Response body contains error");

        long responseTime = response.getTime();
        Assert.assertTrue(responseTime < 5000L, "Response time exceeds the expected limit of 5 seconds");

        Assert.assertFalse(response.body().asString().isEmpty(), "Response body is empty");

        Assertions.assertResponseHasHeader(response, "Server");
        String server = response.getHeader("Server");
        Assert.assertNotNull(server, "Server header is missing");
        Assert.assertEquals(server, "Jetty(9.2.9.v20150224)", "Invalid content server");

        Assertions.assertResponseStatus(response, "available");

        Assertions.assertResponseBodyContains(response, "Princess");

        Assertions.assertResponseBodyHasKey(response, "id");
        Assertions.lineSeparatorStartEndLines ( 0,"TestValidResponse PetLifeCycle " );

    }

    @Test(enabled = true, groups = {SMOKE}, description = "PetLifeCycle Test INVALID Response Case",priority = 2)
    public void testInvalidResponse() {
        Assertions.lineSeparatorStartEndLines ( 1,"TestInValidResponse PetLifeCycle " );
        PostCreatePet body = PostCreatePet.builder()
                .build();

        Response response = postUrl(newPet, createJsonBody(body));

        Assert.assertEquals(response.getStatusCode(), 405, "Invalid response code");
        Assertions.assertResponseHasHeader(response, "Content-Type");
        Assertions.assertResponseHasHeader(response, "Date");
        String contentType = response.getHeader("Content-Type");
        Assert.assertFalse (contentType.contains("charset"), "Charset not found");
        Assert.assertNotNull(response.getBody(), "Response body is null");

        String responseBody = response.getBody().asString();
        Assert.assertFalse(responseBody.isEmpty(), "Response body is empty");

        Assert.assertTrue(responseBody.contains("405"), "Response does not contain the '405'");
        Assert.assertTrue(responseBody.contains("code"), "Response does not contain the 'code' ");
        Assert.assertTrue(responseBody.contains("type"), "Response does not contain 'type'");
        Assert.assertTrue(responseBody.contains("unknown"), "Response does not contain 'unknown'");
        Assert.assertTrue(responseBody.contains("message"), "Response does not contain 'message'");
        Assert.assertTrue(responseBody.contains("no data"), "Response does not contain 'no data'");
        Assert.assertFalse (responseBody.contains("Header1"), "Response does  contain Header1");
        Assert.assertFalse(responseBody.contains("charset"), "Response does  contain charset");
        Assert.assertFalse(responseBody.contains("body"), "Response does  contain body");
        Assert.assertFalse (responseBody.contains("Date"), "Response does  contain Date");
        Assert.assertFalse (responseBody.contains("Content-Type"), "Response does  contain Content-Type");
        String server = response.getHeader("Server");
        Assert.assertNotNull(server, "Server header is missing");
        Assert.assertEquals(server, "Jetty(9.2.9.v20150224)", "Invalid content server");
        Assertions.assertResponseTimeLessThan(response, 20000000L);

        Assertions.lineSeparatorStartEndLines ( 0,"TestInValidResponse PetLifeCycle " );

    }

}



