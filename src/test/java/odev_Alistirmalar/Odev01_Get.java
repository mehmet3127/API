package odev_Alistirmalar;

import base_Url.AutomationExerciseBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class Odev01_Get extends AutomationExerciseBaseUrl {

//1:
   /*
        Given
            https://automationexercise.com/api/brandsList
        When
            User sends a GET Request to the url
        Then
            HTTP Status Code should be 200
        And
            Content Type should be "text/html; charset=utf-8"
        And
            Status Line should be HTTP/1.1 200 OK
        And
             Number of H&M brands must be equal to Polo(H&M marka sayısı Polo marka sayısına eşit olmalı)
    */


    //set the Url
    //Set the Expected Data
    //send tehe request and Get the Response
    //Do assert

    @Test
    public void test01() {

        //set the Url
        spec.pathParams("first", "api", "second", "brandsList");

        //Set the Expected Data

        //send tehe request and Get the Response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPeek();

        //Do assert
        response.then().assertThat().
                statusCode(200).
                contentType("text/html; charset=utf-8").
                statusLine("HTTP/1.1 200 OK");

        JsonPath jsonPath = response.jsonPath();
        List<String> brandH_M = jsonPath.getList("brands.findAll{it.brand=='H&M'}.brand");
        System.out.println("brandH_M.size() = " + brandH_M.size());

        List<String> brandPolo = jsonPath.getList("brands.findAll{it.brand=='Polo'}.brand");
        System.out.println("brandPolo.size() = " + brandPolo.size());
        assertEquals("H&M sayisi Polo sayisina esit degil",brandPolo.size(),brandH_M.size());


    }
}
