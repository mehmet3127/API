package get_request;

import base_Url.AutomationExerciseBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class Odev01 extends AutomationExerciseBaseUrl {

          /*
      Given
          https://automationexercise.com/api/productsList
      When
          User sends a GET Request to the url
      Then
          HTTP Status Code should be 200
      And
          Content Type should be "text/html; charset=utf-8"
      And
          Status Line should be HTTP/1.1 200 OK
      And
           There must be 12 Women, 9 Men, 13 Kids usertype in products
        */

    //set the Url
    //Set the Expected Data
    //send tehe request and Get the Response
    //Do assert


    @Test
    public void get01() {

        //set the Url
        spec.pathParams("first", "api", "second", "productsList");

        //Set the Expected Data

        //send tehe request and Get the Response
        Response response = given().spec(spec).when().get("/{first}/{second}");


        //Do assert
        //SoftAssert softAssert = new SoftAssert();


        //HTTP Status Code should be 200
        //Content Type should be "text/html; charset=utf-8"
        //Status Line should be HTTP/1.1 200 OK
        response.then().
                statusCode(200).
                contentType(ContentType.HTML).statusLine("HTTP/1.1 200 OK");


        //There must be 12 Women, 9 Men, 13 Kids usertype in products
        SoftAssert softAssert = new SoftAssert();
        JsonPath jsonPath = response.jsonPath();
        //jsonPath.prettyPrint();
        List<String> women = jsonPath.getList("products.category.usertype.findAll{it.usertype=='Women'}.usertype");
        System.out.println("women = " + women);
        softAssert.assertEquals(12, women.size(),"Women sayisi 12'ye esit degil");

        List<String> men = jsonPath.getList("products.category.usertype.findAll{it.usertype=='Men'}.usertype");
        System.out.println("men = " + men);
        softAssert.assertEquals(9, men.size(),"Men sayisi 9'a esit degil");

        List<String> kids = jsonPath.getList("products.category.usertype.findAll{it.usertype=='Kids'}.usertype");
        System.out.println("kids = " + kids);
        softAssert.assertEquals(13, kids.size(),"Kids saysi 13'e esit degil");

        softAssert.assertAll();


    }
}
