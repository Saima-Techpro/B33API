package post_requests;

import base_urls.JsonPlaceholderBaseUrl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static utilities.ObjectMapperUtil.convertJsonStringToMap;

public class Post05 extends JsonPlaceholderBaseUrl {
      /*
    Given
        https://jsonplaceholder.typicode.com/todos
        {"userId":55, "title":"Tidy your room", "completed":false}
    When
        User sends a POST request to the URL
    Then
        Status code should be 201
    And
        Response body should be this:
        {
            "userId":55,
            "title":"Tidy your room",
            "completed":false,
            "id":201
        }
    */

    @Test
    public void test01() throws JsonProcessingException {
        // 1. Set the URL
        spec.pathParam("first", "todos");
        // 2. Set the payload / expected data
        String payloadString = "{\"userId\":55, \"title\":\"Tidy your room\", \"completed\":false}";
        System.out.println("payloadString = " + payloadString);

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> payloadMap =  objectMapper.readValue(payloadString, HashMap.class);
        System.out.println("payloadMap = " + payloadMap);


        // 3. Send the request and get the response
        Response response = given(spec).body(payloadMap).when().post("{first}");
        response.prettyPrint();

        // 4. Make Assertions
        // De-serialization
        Map<String, Object> actualData = response.as(HashMap.class);

        assertEquals(201, response.statusCode());
        assertEquals(payloadMap.get("userId"), actualData.get("userId"));
        assertEquals(payloadMap.get("title"), actualData.get("title"));
        assertEquals(payloadMap.get("completed"), actualData.get("completed"));
    }

    @Test
    public void test02() {
        // 1. Set the URL
        spec.pathParam("first", "todos");
        // 2. Set the payload / expected data
        String payloadString = "{\"userId\":55, \"title\":\"Tidy your room\", \"completed\":false}";
        System.out.println("payloadString = " + payloadString);

        // Using Reusable method convertJsonStringToMap() coming from ObjectMapperUtil class
        Map<String, Object> payloadMap =  convertJsonStringToMap(payloadString);
        System.out.println("payloadMap = " + payloadMap);


        // 3. Send the request and get the response
        Response response = given(spec).body(payloadMap).when().post("{first}");
        response.prettyPrint();

        // 4. Make Assertions
        // De-serialization
        Map<String, Object> actualData = response.as(HashMap.class);

        assertEquals(201, response.statusCode());
        assertEquals(payloadMap.get("userId"), actualData.get("userId"));
        assertEquals(payloadMap.get("title"), actualData.get("title"));
        assertEquals(payloadMap.get("completed"), actualData.get("completed"));
    }
}
