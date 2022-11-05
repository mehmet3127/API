package get_request;

import base_Url.GoRestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.collections.Objects;
import test_data.GoRestTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Get10 extends GoRestBaseUrl {

     /*
   Given
       https://gorest.co.in/public/v1/users/2986
   When
       User send GET Request to the URL
   Then
       Status Code should be 200
   And
       Response body should be like
  {
    "meta": null,
    "data": {
        "id": 2986,
        "name": "Navin Talwar",
        "email": "navin_talwar@mclaughlin.name",
        "gender": "male",
        "status": "inactive"
    }
}
*/
    //set the Url
    //Set the Expected Data
    //send tehe request and Get the Response
    //Do assert


    @Test
    public void get01() {
        //set the Url
        spec.pathParams("first", "users", "second", "2986");

        //Set the Expected Data
        GoRestTestData obj = new GoRestTestData();
        Map<String, String> dataKeyMap = obj.dataKeyMap("Navin Talwar", "navin_talwar@mclaughlin.name", "male", "inactive");
        //icerideki Map Data map

        Map<String, Object> expectedData = obj.expectedDataMethod(null, dataKeyMap);
        //ikinciMap dis Map
        System.out.println("expectedData = " + expectedData);

        //send tehe request and Get the Response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //Do assert

        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println("actualData = " + actualData);

        assertEquals(expectedData.get("meta"), actualData.get("meta"));
        assertEquals(dataKeyMap.get("name"), ((Map) (actualData.get("data"))).get("name"));
        assertEquals(dataKeyMap.get("email"), ((Map) (actualData.get("data"))).get("email"));
        assertEquals(dataKeyMap.get("gender"), ((Map) (actualData.get("data"))).get("gender"));
        assertEquals(dataKeyMap.get("status"), ((Map) (actualData.get("data"))).get("status"));
    }
}
