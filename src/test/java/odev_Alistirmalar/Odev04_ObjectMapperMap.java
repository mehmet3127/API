package odev_Alistirmalar;

import base_Url.ReqresBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.ReqresTestData;
import test_data.RestfulTestData;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Odev04_ObjectMapperMap extends ReqresBaseUrl {

    //4: Map ile ve Pojo Class ile ayrı ayrı Object Mapper kullanarak yapınız.
     /*
       Given
           1) https://reqres.in/api/users/2
           2)
                          {
                            "name": "neo"
                           }
       When
            I send PATCH Request to the Url
       Then
             Status code is 200
             And response body is like
                          {
                            "name": "neo",
                            "updatedAt": "2022-10-02T12:53:21.675Z"
                           }
     */

    //set the Url
    //Set the Expected Data
    //send tehe request and Get the Response
    //Do assert

    @Test
    public void test01(){
        //set the Url
        spec.pathParams("first","users","second",2);


        //Set the Expected Data
        ReqresTestData obj =new ReqresTestData();
        Map<String,Object> expectedData =obj.reqresTestDataMethod("neo",null);
        System.out.println("expectedData = " + expectedData);

        //send tehe request and Get the Response
        Response response =given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().patch("/{first}/{second}");
        response.prettyPrint();

        //Do assert
        Map<String,Object> actualData = response.as(Map.class);
        System.out.println("actualData = " + actualData);

        assertEquals(expectedData.get("name"), actualData.get("name"));
    }

}
