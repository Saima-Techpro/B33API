package get_requests;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Get02 {

    /*
    Given
        User goes to https://petstore.swagger.io/v2/pet/12
    When
        User sends GET request to this endpoint
    Then
        HTTP Status code is 200
    And
        Content Type is "application/json"
    And
        Status Line is "HTTP/1.1 200 OK"
     */

    @Test
    public void test01(){
        // 1. Set the url
        String endpoint = "https://petstore.swagger.io/v2/pet/12";
        // 2. Set the payload / expected data
        // 3. Send the request and get the response
       Response response =  given().when().get(endpoint);
       response.prettyPrint();

        // 4. Make Assertions
        // API Assertions
        response.then().
                assertThat().statusCode(200).
                and().assertThat().contentType("application/json").
                and().assertThat().statusLine("HTTP/1.1 200 OK");

    }
    @Test
    public void test02(){
        // 1. Set the url
        String endpoint = "https://petstore.swagger.io/v2/pet/12";
        // 2. Set the payload / expected data
        // 3. Send the request and get the response
        Response response =  given().when().get(endpoint);
        response.prettyPrint();

        // 4. Make Assertions
        // API Assertions
        response.
                then().
                statusCode(200).
                contentType("application/json").
                statusLine("HTTP/1.1 200 OK").
                log().
                body();

    }
}
