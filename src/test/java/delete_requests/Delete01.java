package delete_requests;

import base_urls.JsonPlaceholderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class Delete01 extends JsonPlaceholderBaseUrl {
        /*
    Given
        https://jsonplaceholder.typicode.com/todos/198
    When
        User sends a DELETE request to the URL
    Then
        Status code should be 200
    And
        Response body should be this: {}
     */

    @Test
    public void test01(){
        // 1. Set the URL
        spec.pathParams("first", "todos", "second", 198);

        // 2. Set the payload / expected data
        // 3. Send the request and get the response
        Response response = given(spec).when().delete("{first}/{second}");
        response.prettyPrint();

        // 4. Make Assertions
        response.then().statusCode(200);

        // De-serialization
        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println("actualData = " + actualData);
        assertEquals(0, actualData.size());
        assertTrue(actualData.isEmpty());

    }

    @Test
    public void test02(){
        // 1. Set the URL
        spec.pathParams("first", "todos", "second", 198);

        // 2. Set the payload / expected data
        Map<String, Object> expectedData  = new HashMap<>();
        System.out.println("expectedData = " + expectedData);

        // 3. Send the request and get the response
        Response response = given(spec).when().delete("{first}/{second}");
        response.prettyPrint();

        // 4. Make Assertions
        response.then().statusCode(200);

        // De-serialization
        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println("actualData = " + actualData);
        assertEquals(expectedData, actualData);


    }


}
