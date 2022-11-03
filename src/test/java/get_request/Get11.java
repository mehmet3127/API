package get_request;

import base_Url.GoRestBaseUrl;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertTrue;


public class Get11 extends GoRestBaseUrl {
    /*
    Given
        https://gorest.co.in/public/v1/users
    When
        User send GET Request
    Then
        The value of "pagination limit" is 10
    And
        The "current link" should be "https://gorest.co.in/public/v1/users?page=1"
    And
        The number of users should  be 10
    And
        We have at least one "active" status
    And
        "Fr. Paramartha Bandopadhyay", "Pres. Amarnath Dhawan" and "Sujata Chaturvedi" are among the users
    And
        The female users are less than or equals to male users
 */
    //set the Url
    //Set the Expected Data
    //send tehe request and Get the Response
    //Do assert


    @Test
    public void get11() {

        //set the Url
        spec.pathParam("first", "users");

        //Set the Expected Data

        //send tehe request and Get the Response
        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        //Do assert
        response.
                then().
                assertThat().
                statusCode(200).
                body("meta.pagination.limit", equalTo(10),
                        "meta.pagination.links.current", equalTo("https://gorest.co.in/public/v1/users?page=1"),
                        "data", hasSize(10),
                        "data.status", hasItem("active"), "data.name", hasItems("Pres. Amarnath Dhawan", "Sujata Chaturvedi", "Navin Panicker"));

        //Kadin Ve Erkek Sayilarini Karsilastiralim
        //1.YOL : java Forloop ile
        List<String> genders = response.jsonPath().getList("data.gender");
        System.out.println(genders);

        int numfamale = 0;
        for (String w : genders) {

            if (w.equalsIgnoreCase("female")){
                numfamale++;
            }
        }
        assertTrue(numfamale<=genders.size()-numfamale);

        //2.Yol Kadin Ve Erkek Sayilarini Groovy ile bulalim
       List<String> femaleGender = response.jsonPath().getList("data.findAll{it.gender=='female'}.name");
        System.out.println(femaleGender);

        List<String> maleGender = response.jsonPath().getList("data.findAll{it.gender=='male'}.name");
        System.out.println(maleGender);
        assertTrue(femaleGender.size()<maleGender.size());


    }
}
