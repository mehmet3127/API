package odev_Alistirmalar;

import base_Url.ReqresBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.ReqresPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Odev03_PutPojo extends ReqresBaseUrl {
    //3: Map ile ve Pojo Class ile ayrı ayrı Gson kullanarak yapınız.

    /*
        Given
            1) https://reqres.in/api/users/2
            2)
                {
                "name": "morpheus",
                "job": "zion president"
                }
        When
            I send Put Request to the Url
        Then
            Status code is 200
            And response body should be like
                              {
                                "name": "morpheus",
                                "job": "zion president",
                                "updatedAt": "2022-10-02T11:35:05.693Z"
                               }
   */

    //set the Url
    //Set the Expected Data
    //send tehe request and Get the Response
    //Do assert


    @Test
    public void test01() {

        //set the Url
        spec.pathParams("first", "users", "second", 2);

        //Set the Expected Data
        ReqresPojo expectedData = new ReqresPojo("morpheus", "zion president");
        System.out.println("expectedData = " + expectedData);

        //send tehe request and Get the Response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().put("/{first}/{second}");
        response.prettyPrint();

        //Do assert
        ReqresPojo actualData = response.as(ReqresPojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(expectedData.getName(), actualData.getName());
        assertEquals(expectedData.getJob(), actualData.getJob());

    }
}
