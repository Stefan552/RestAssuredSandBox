package com.example.sandbox.util;

import io.restassured.response.Response;
import org.junit.Assert;

public class Assertions  {
    public Assertions ( ) {
    }

    public void assertReturnCode ( Response response , Integer code ) {
        Assert.assertEquals ( (Integer) response.getStatusCode () , code );
    }
    public void assertResponseHasHeader(Response response, String headerName) {
        org.testng.Assert.assertTrue(response.headers().hasHeaderWithName(headerName),
                "Response does not have header: " + headerName);
    }

    public void assertResponseTimeLessThan(Response response, long expectedTimeInMillis) {
        org.testng.Assert.assertTrue(response.getTime() < expectedTimeInMillis,
                "Response time exceeds the expected limit");
    }



    public void assertResponseHasHeader1(Response response, String headerName) {
        org.testng.Assert.assertTrue(response.headers().hasHeaderWithName(headerName), "Response does not have header: " + headerName);
    }

    public void assertResponseTimeLessThan1(Response response, long expectedTimeInMillis) {
        org.testng.Assert.assertTrue(response.getTime() < expectedTimeInMillis, "Response time exceeds the expected limit");
    }


    public void assertResponseStatus(Response response, String expectedStatus) {
        String actualStatus = response.jsonPath().get("status");
        org.testng.Assert.assertEquals(actualStatus, expectedStatus, "Invalid response status");
    }

    public void assertResponseBodyContains(Response response, String expectedValue) {
        String responseBody = response.body().asString();
        org.testng.Assert.assertTrue(responseBody.contains(expectedValue), "Response body does not contain the expected value: " + expectedValue);
    }

    public void assertResponseBodyHasKey(Response response, String key) {
        boolean hasKey = response.jsonPath().getMap("").containsKey(key);
        org.testng.Assert.assertTrue(hasKey, "Response body does not have the key: " + key);
    }

    public void   lineSeparatorStartEndLines(int situation,String testName){
        String line;
        switch (situation){
            case 1 : line="------------------------------------"+"START TEST: "+testName+"------------------------------------";
                System.out.println (line);
                break;
            case 0 : line="------------------------------------"+"END TEST "+testName+"---------------------------------------";
                System.out.println (line);
                break;
            default:
                System.out.println ("Invalid Input!");
                break;
        }
    }
}