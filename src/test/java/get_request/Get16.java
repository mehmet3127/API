package get_request;

import base_Url.DummyRestApiBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;

public class Get16 extends DummyRestApiBaseUrl {

        /*
           URL: https://dummy.restapiexample.com/api/v1/employees
           HTTP Request Method: GET Request
           Test Case: Type by using Gherkin Language
           Assert:  i) Status code is 200
                   ii) There are 24 employees
                  iii) "Tiger Nixon" and "Garrett Winters" are among the employees
                   iv) The greatest age is 66
                    v) The name of the lowest age is "Tatyana Fitzpatrick"
                   vi) Total salary of all employees is 6,644,770
        */


    @Test
    public void get16() {

        spec.pathParams("first", "employees");

        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        //i) Status code is 200
        response.then().statusCode(200);

        //ii) There are 24 employee,iii) "Tiger Nixon" and "Garrett Winters" are among the employees
        response.then().assertThat().body("data", hasSize(24),
                "data.employee_name", hasItems("Tiger Nixon", "Garrett Winters"));

        //iv) The greatest age is 66
        List<Integer> ages = response.jsonPath().getList("data.employee_age");
        System.out.println("ages = " + ages);
        Collections.sort(ages);
        System.out.println("Sort ages = " + ages);
        System.out.println(ages.get(ages.size() - 1));
        assertEquals(66, (int) ages.get(ages.size() - 1)); //Yukarida Listi Integer yaptigimiz icin int e cevirdik ikiside ayni olmasini istiyor

        //v) The name of the lowest age is "Tatyana Fitzpatrick"
        String lowestAgeEmployee = response.jsonPath().getString("data.findAll{it.employee_age==" + ages.get(0) + "}.employee_name");
        System.out.println("lowestAgeEmployee = " + lowestAgeEmployee);

        assertEquals("[Tatyana Fitzpatrick]", lowestAgeEmployee);

        //vi) Total salary of all employees is 6,644,770
        List<Integer> Salarys = response.jsonPath().getList("data.employee_salary");

        //1.Yol
        int top = 0;
        for (int w : Salarys) {
            top += w;
        }

        System.out.println("top = " + top);
        assertEquals(6644770, top);

        //2.Yol
        int sum2 = Salarys.stream().reduce(0, Integer::sum);
        System.out.println("sum2 = " + sum2);
        assertEquals(6644770, sum2);

        //3.Yol
        int sum3 = Salarys.stream().reduce(0, Math::addExact);
        System.out.println("sum3 = " + sum3);
        assertEquals(6644770, sum3);

    }
}
