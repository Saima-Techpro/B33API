package get_requests;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;


public class Get03 {
       /*
    Given
        User goes to https://petstore.swagger.io/v2/pet/0
    When
        User sends GET request to this endpoint
    Then
        HTTP Status code is 404
    And
        Content Type is "application/json"
    And
        Status Line is "HTTP/1.1 404 Not Found"
    And
        The response body should contain "Pet not found"
    And
        The response body should NOT contain "TechPro"
    And
        Server value should be "Jetty(9.2.9.v20150224)"

     */

    @Test
    public void test01(){
        // 1. Set the URL
        String url = "https://petstore.swagger.io/v2/pet/0";

        // 2. Set the payload / expected data
        // 3. Send the request and get the response
        // given().when().get(url).prettyPrint(); You can print the response directly like this
        // OR store it in response variable to make it reusable

        Response response = given().when().get(url);
        response.prettyPrint();

        // 4. Make Assertions
        response.
                then().
                statusCode(404).
                contentType("application/json").
                statusLine("HTTP/1.1 404 Not Found").
                body(containsString("Pet not found")).
                body(not(containsString("TechPro"))).
                header("Server", "Jetty(9.2.9.v20150224)");

    }


    @Test
    public void test02(){
        // 1. Set the URL
        String url = "https://petstore.swagger.io/v2/pet/0";

        // 2. Set the payload / expected data
        // 3. Send the request and get the response
        Response response = given().when().get(url);
        response.prettyPrint();

        // 4. Make Assertions
        // HARD ASSERTION from JUNIT

        assertEquals(404, response.statusCode());
        assertEquals("HTTP/1.1 404 Not Found", response.statusLine());

        assertTrue(response.asString().contains("Pet not found"));
        assertFalse(response.asString().contains("TechPro"));

        // NOTE: In TestNG framework, we have 3rd option of using SOFT ASSERTIONS as well but in JUNIT Framework.

    }


}
