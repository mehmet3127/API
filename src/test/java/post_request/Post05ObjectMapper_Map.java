package post_request;

import base_Url.JsonplaceholderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;
import utils.ObjectMapperUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post05ObjectMapper_Map extends JsonplaceholderBaseUrl {

   /*
     Given
       1) https://jsonplaceholder.typicode.com/todos
       2) {
             "userId": 55,
             "title": "Tidy your room",
             "completed": false
           }
        I send POST Request to the Url
    Then
        Status code is 201
    And
        response body is like {
                                "userId": 55,
                                "title": "Tidy your room",
                                "completed": false,
                                "id": 201
                                }
 */

    @Test
    public void post05ObjectMapper() throws IOException {
        //set the url
        spec.pathParam("first", "todos");

        //set the expected data

       /* String jsonString = "{\n" +
                "\"userId\": 55,\n" +
                "\"title\": \"Tidy your room\",\n" +
                "\"completed\": false,\n" +
                "\"id\": 201\n" +
                "}";*/
        //Set the Expected Data
        JsonPlaceHolderTestData obj = new JsonPlaceHolderTestData();
        String jsonInString = obj.expectedDataInString(55,"Tidy your room",false);

        HashMap expectedData = new ObjectMapper().readValue(jsonInString, HashMap.class);
        System.out.println("expectedData: " + expectedData);

        //send the request and get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        response.prettyPrint();
        //System.out.println("response.asString() = " + response.asString());
        // response'i JSON formatinda degil de String olarak yazdirir

        //Do Assertion
        HashMap actualData = new ObjectMapper().readValue(response.asString(), HashMap.class);
        System.out.println("actualData = " + actualData);

        assertEquals(201,response.getStatusCode());
        assertEquals(expectedData.get("completed"),actualData.get("completed"));
        assertEquals(expectedData.get("title"),actualData.get("title"));
        assertEquals(expectedData.get("userId"),actualData.get("userId"));

        System.out.println("(ObjectMapperUtils.convertJsonToJava(\"15\",Integer.class)+45) = " + (ObjectMapperUtils.convertJsonToJava("15", Integer.class) + 45));

    }
}
