package post_request;


import base_Url.DummyRestApiBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import pojos.DummyRestApiDataPojo;
import pojos.DummyRestApiResponseBodyPojo;
import utils.ObjectMapperUtils;

import static io.restassured.RestAssured.given;

public class Post06 extends DummyRestApiBaseUrl {
    /*
      URL: https://dummy.restapiexample.com/api/v1/create
      HTTP Request Method: POST Request
      Request body:
                    {
                       "employee_name": "Tom Hanks",
                       "employee_salary": 111111,
                       "employee_age": 23,
                       "profile_image": "Perfect image",
                       "id": 4891
                    }

      Test Case: Type by using Gherkin Language
      Assert:

               i) Status code is 200
               ii) Response body should be like the following
                   {
                       "status": "success",
                       "data": {
                           "employee_name": "Tom Hanks",
                           "employee_salary": 111111,
                           "employee_age": 23,
                           "profile_image": "Perfect image",
                           "id": 4891
                       },
                       "message": "Successfully! Record has been added."
                   }
    */
    /*
    Given
        https://dummy.restapiexample.com/api/v1/create

    And    {
            "employee_name": "Tom Hanks",
            "employee_salary": 111111,
            "employee_age": 23,
            "profile_image": "Perfect image",
            "id": 4891
          }
     When
        User sends POST request

     Then
        Status code is 200
     And
        Response body should be like the following
        {
            "status": "success",
            "data": {
                "employee_name": "Tom Hanks",
                "employee_salary": 111111,
                "employee_age": 23,
                "profile_image": "Perfect image",
                "id": 4891
            },
            "message": "Successfully! Record has been added."
        }


     */
    @Test
    public void post06() {

        //set the Url
        spec.pathParams("first", "create");


        //Set the Expected Data
        DummyRestApiDataPojo expectedData = new DummyRestApiDataPojo("Tom Hanks", 111111, 23, "Perfect image");
        System.out.println("expectedData = " + expectedData);

        //send tehe request and Get the Response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        response.prettyPrint();

        //Do assert
        DummyRestApiResponseBodyPojo actualData = ObjectMapperUtils.convertJsonToJava(response.asString(),DummyRestApiResponseBodyPojo.class);
        System.out.println("actualData = " + actualData);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(200,response.statusCode());
        softAssert.assertEquals(actualData.getData().getEmployee_name(),expectedData.getEmployee_name());
        softAssert.assertEquals(actualData.getData().getEmployee_salary(),expectedData.getEmployee_salary());
        softAssert.assertEquals(actualData.getData().getEmployee_age(),expectedData.getEmployee_age());
        softAssert.assertEquals(actualData.getData().getEmployee_image(),expectedData.getEmployee_image());

        softAssert.assertAll();

    }


}