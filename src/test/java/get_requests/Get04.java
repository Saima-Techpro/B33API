package get_requests;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Get04 {
          /*
    Given
        User goes to https://petstore.swagger.io/v2/pet/70
    When
        User sends GET request to this endpoint
    Then
        HTTP Status code is 200
    And
        Content Type is "application/json"
    And
        Category name value should not be "Horse"
    And
        Tags name value should be "string"
    And
       Status should be "sold"

     */

    @Test
    public void test01(){
        // 1. Set the URL
        String url = "https://petstore.swagger.io/v2/pet/70";
        // 2. Set the payload / expected data
        // 3. Send the request and get the response
        Response response = given().when().get(url);
        response.prettyPrint();

        // 4. Make Assertions
        response.
                then().
                statusCode(200).
                contentType("application/json").
                body("name", equalTo("Sloth")).
                body("category.name", not(equalTo("Horse"))).
                body("tags[0].name", equalTo("string")).
                body("status", equalTo("sold"));

    }

    @Test
    public void test02(){
        // 1. Set the URL
        // 2. Set the payload / expected data
        // 3. Send the request and get the response
        // 4. Make Assertions

        given().when().get("https://petstore.swagger.io/v2/pet/70").
                then().
                statusCode(200).
                contentType("application/json").
                body("name", equalTo("Sloth")).
                body("category.name", not(equalTo("Horse"))).
                body("tags[0].name", equalTo("string")).
                body("status", equalTo("sold"));


    }



}
