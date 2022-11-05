package get_request;

import base_Url.JsonplaceholderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;
import utils.ObjectMapperUtils;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get14ObjectMapper_Map extends JsonplaceholderBaseUrl {

      /*
        Given
	        https://jsonplaceholder.typicode.com/todos/198
        When
	 		I send GET Request to the URL
	 	Then
	 		Status code is 200
    And response body is like {
                                "userId": 10,
                                "id": 198,
                                "title": "quis eius est sint explicabo",
                                "completed": true
                              }
     */

    @Test
    public void get14Map() {

        //set the Url
        spec.pathParams("first", "todos", "second", "198");

        //Set the Expected Data
        String expectedDataInString = new JsonPlaceHolderTestData().expectedDataInString(10, "quis eius est sint explicabo", true);
        Map<String, Object> expectedData = ObjectMapperUtils.convertJsonToJava(expectedDataInString, Map.class);
        System.out.println("expectedData = " + expectedData);

        //send tehe request and Get the Response
        Response response = given().spec(spec).get("/{first}/{second}");
        response.prettyPrint();

        //Do assert
        Map<String, Object> actualData = ObjectMapperUtils.convertJsonToJava(response.asString(), Map.class);
        System.out.println("actualData = " + actualData);

        assertEquals(200,response.getStatusCode());
        assertEquals(expectedData.get("userId"), actualData.get("userId"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("completed"), actualData.get("completed"));
    }
}
