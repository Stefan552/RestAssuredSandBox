package com.example.sandbox.Pet.uploadImagePet;
import com.example.sandbox.Common;
import com.example.sandbox.util.Assertions;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.File;
import static com.example.sandbox.util.constans.Tags.SMOKE;



public class uploadImagePet extends Common {
 com.example.sandbox.util.Assertions Assertions=new Assertions ();

    @Test (enabled = true, groups = {SMOKE}, description = "testValidResponseUploadImage Test VALID Response Case",priority = 1)
    public void testValidResponseUploadImage() {
        Assertions.lineSeparatorStartEndLines ( 1 , " testValidResponseUploadImage " );
        Integer id=1452;
        File file = new File("D:\\IT school\\programe\\RestAssuredSandbox2\\src\\test\\java\\com\\example\\sandbox\\util\\constans\\heyho.jpg");

        Response response=updatePetImage ( id ,file);
        Assert.assertNotNull(response);
        Assert.assertEquals( response.getStatusCode(),200);
        Assertions.assertResponseHasHeader(response, "Server");
        String server = response.getHeader("Server");
        Assert.assertNotNull(server, "Server header is missing");
        Assert.assertEquals(server, "Jetty(9.2.9.v20150224)", "Invalid content server");

        Assertions.assertResponseBodyHasKey(response, "message");
        String contentType = response.getHeader("Content-Type");
        Assert.assertEquals(contentType, "application/json", "Invalid content type");
        Assert.assertNotNull ( contentType, "The Content-Type  header is missing");

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
        Assert.assertEquals ( message,"additionalMetadata: null\nFile uploaded to ./heyho.jpg, 31253 bytes","The 'message'  field is not 'additionalMetadata: null\\nFile uploaded to ./heyho.jpg, 31253 bytes' " );

        Assertions.lineSeparatorStartEndLines ( 0, " testValidResponseUploadImage " );


    }


    @Test (enabled = true, groups = {SMOKE}, description = "testInValidResponseUploadImage Test INVALID Response Case",priority = 2)
    public void testInValidResponseUploadImage() {
        Assertions.lineSeparatorStartEndLines ( 1 , " testInValidResponseUploadImage " );
        Integer id=null;
        File file = new File("D:\\IT school\\programe\\RestAssuredSandbox2\\src\\test\\java\\com\\example\\sandbox\\util\\constans\\heyho.jpg");

        Response response=updatePetImage ( id ,file);
        Assert.assertNotNull(response);
        Assert.assertEquals( response.getStatusCode(),404);
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
        Assertions.assertResponseTimeLessThan(response, 200000000L);
        Assertions.lineSeparatorStartEndLines ( 0 , " testInValidResponseUploadImage " );

    }
}
