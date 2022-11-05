package get_request;

import base_Url.GoRestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.GoRestDataPojo;
import pojos.GoRestPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class get13Pojo extends GoRestBaseUrl {

            /*
        Given
            https://gorest.co.in/public/v1/users/2508
        When
            User send GET Request to the URL
        Then
            Status Code should be 200
        And
            Response body should be like
          {
            "meta": null,
            "data": {
                "id": 2508,
                "name": "Sharmila Deshpande VM",
                "email": "deshpande_sharmila_vm@becker.name",
                "gender": "female",
                "status": "active"
                 }
          }
    */

    @Test
    public void get13Pojo() {

        //set the Url
        spec.pathParams("first", "users", "second", 2508);

        //Set the Expected Data
        GoRestDataPojo goRestDataPojo = new GoRestDataPojo(2508, "Sharmila Deshpande VM", "deshpande_sharmila_vm@becker.name", "female", "active");
        System.out.println("goRestDataPojo = " + goRestDataPojo);

        GoRestPojo expectedData = new GoRestPojo(null, goRestDataPojo);
        System.out.println("expectedData = " + expectedData);

        //send tehe request and Get the Response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //Do assert
        GoRestPojo actualData = response.as(GoRestPojo.class);

        assertEquals(expectedData.getMeta(), actualData.getMeta());
        //1.yoll inner data
        assertEquals(expectedData.getData().getId(), actualData.getData().getId());
        assertEquals(expectedData.getData().getName(), actualData.getData().getName());
        assertEquals(expectedData.getData().getEmail(), actualData.getData().getEmail());
        assertEquals(expectedData.getData().getGender(), actualData.getData().getGender());
        assertEquals(expectedData.getData().getStatus(), actualData.getData().getStatus());

        //2.Yol inner data
        assertEquals(goRestDataPojo.getId(), actualData.getData().getId());
        assertEquals(goRestDataPojo.getName(), actualData.getData().getName());
        assertEquals(goRestDataPojo.getEmail(), actualData.getData().getEmail());
        assertEquals(goRestDataPojo.getGender(), actualData.getData().getGender());
        assertEquals(goRestDataPojo.getStatus(), actualData.getData().getStatus());

    }
}
