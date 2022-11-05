package get_request;

import base_Url.RestfulBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class Get09 extends RestfulBaseUrl {

    /*
    Given
        https://restful-booker.herokuapp.com/booking/91
    When
        I send GET Request to the url
    Then
        Response body should be like that;
  {
    "firstname": "Sally",
    "lastname": "Brown",
    "totalprice": 111,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2013-02-23",
        "checkout": "2014-10-23"
    },
    "additionalneeds": "Breakfast"
}

    //set the Url
    //Set the Expected Data
    //send tehe request and Get the Response
    //Do assert

 */

    @Test
    public void get01() {

        //set the Url
        spec.pathParams("first", "booking", "second", "91");

        //Set the Expected Data
        Map<String, String> bookingdatesMap = new HashMap<>();
        bookingdatesMap.put("checkin", "2013-02-23");
        bookingdatesMap.put("checkout", "2014-10-23");

        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("firstname", "Sally");
        expectedData.put("lastname", "Brown");
        expectedData.put("totalprice", 111);
        expectedData.put("depositpaid", true);
        expectedData.put("bookingdates", bookingdatesMap);
        expectedData.put("additionalneeds", "Breakfast");

        System.out.println("expectedData = " + expectedData);

        //send tehe request and Get the Response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //Do assert

        Map<String, Object> actualData = response.as(HashMap.class);//De-Serialization

        assertEquals(expectedData.get("firstname"), actualData.get("firstname"));
        assertEquals(expectedData.get("lastname"), actualData.get("lastname"));
        assertEquals(expectedData.get("totalprice"), actualData.get("totalprice"));
        assertEquals(expectedData.get("depositpaid"), actualData.get("depositpaid"));
        assertEquals(bookingdatesMap.get("checkin"), ((Map) (actualData.get("bookingdates"))).get("checkin"));
        assertEquals(bookingdatesMap.get("checkout"), ((Map) (actualData.get("bookingdates"))).get("checkout"));
        assertEquals(expectedData.get("additionalneeds"), actualData.get("additionalneeds"));


    }
}
