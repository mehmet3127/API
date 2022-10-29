package get_request;

import base_Url.ReqresBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Get02b extends ReqresBaseUrl {
     /*
   Given
       https://reqres.in/api/users/23
   When
       User send a GET Request to the url
   Then
       HTTP Status code should be 404
   And
       Status Line should be HTTP/1.1 404 Not Found
   And
       Server is "cloudflare"
   And
       Response body should be empty

*/

    @Test
    public void get02() {

        //i) Set the URL,
        spec.pathParams("first", "users", "second",23);
        //ii)Set the expected Data (beklenen datanin olusturulmasi,Post ,Put ,Patch)

        // iii)Type code to send request(Talep göndermek icin kod yazimi
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //iv)Do Assertion(dogrulama yapmak)
        assertEquals(404,response.statusCode());
        assertEquals("HTTP/1.1 404 Not Found",response.statusLine());
        assertEquals("cloudflare",response.getHeader("Server"));
        assertEquals(2,response.asString().replaceAll("\\s","").length());
    }
}
