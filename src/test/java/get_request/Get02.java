package get_request;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class Get02 {

    /* Given
        https://restful-booker.herokuapp.com/booking/1
    When
        User send a GET Request to the url
    Then
        HTTP Status code should be 404
    And
        Status Line should be HTTP/1.1 404 Not Found
    And
        Response body contains "Not Found"
    And
        Response body does not contain "TechProEd"
    And
        Server is "Cowboy"
 */

    @Test
    public void get01() {

        //i) Set the URL
        String url = "https://restful-booker.herokuapp.com/booking/1";

        //ii)Set the expected Data (beklenen datanin olusturulmasi,Post ,Put ,Patch)
        //Bizden Post ,Put ,Patch istenmdigi icin bu case de kullanmayacagiz

        //iii)Type code to send request(Talep göndermek icin kod yazimi

        Response response = given().when().get(url);
        response.prettyPrint();

        //iv)Do Assertion(dogrulama yapmak)
        response.then().assertThat().statusCode(404).statusLine("HTTP/1.1 404 Not Found");

        //body Not Found iceriyormu testi yapiliyor
        assertTrue(response.asString().contains("Not Found"));

        //body nin TechProEd icermedigi test yapiliyor
        assertFalse(response.asString().contains("TechProEd"));

        //body nin Cowboy olup olmadigini test ediyor
        assertEquals("Cowboy", response.getHeader("Server"));


    }
}
