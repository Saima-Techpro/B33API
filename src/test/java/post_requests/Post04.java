package post_requests;

import base_urls.JsonPlaceholderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class Post04 extends JsonPlaceholderBaseUrl {

    /*
    Given
        https://jsonplaceholder.typicode.com/todos

        {
            "userId":55,
            "title":"Tidy your room",
            "completed":false
        }
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
    public void test01(){
        // 1. Set the URL
        spec.pathParam("param", "todos");

        // 2. Set the payload / expected data
//        String payload = "{\n" +
//                "    \"userId\": 55,\n" +
//                "    \"title\": \"Tidy your room\",\n" +
//                "    \"completed\": false\n" +
//                "}";

        // System.out.println("payload = " + payload); The data type of this payload is STRING

        Map<String, Object> payloadMap = new HashMap<>();
        payloadMap.put("userId" , 55);
        payloadMap.put("title", "Tidy your room");
        payloadMap.put("completed", false);
        //System.out.println("payloadMap = " + payloadMap);


        // 3. Send the request and get the response
        Response response = given(spec).body(payloadMap).when().post("{param}");
        response.prettyPrint();

        // Change response to jsonPath
        JsonPath jsonPath =response.jsonPath();

        // 4. Make Assertions
        assertEquals(201, response.statusCode());
        assertEquals(payloadMap.get("userId"), jsonPath.getInt("userId"));
        assertEquals(payloadMap.get("title") , jsonPath.getString("title"));
        assertEquals(payloadMap.get("completed"), jsonPath.getBoolean("completed"));


    }
    @Test
    public void test02(){
        // 1. Set the URL
        spec.pathParam("param", "todos");

        // 2. Set the payload / expected data
//        String payload = "{\n" +
//                "    \"userId\": 55,\n" +
//                "    \"title\": \"Tidy your room\",\n" +
//                "    \"completed\": false\n" +
//                "}";

        // System.out.println("payload = " + payload); The data type of this payload is STRING

        Map<String, Object> payloadMap = new HashMap<>();
        payloadMap.put("userId" , 55);
        payloadMap.put("title", "Tidy your room");
        payloadMap.put("completed", false);
        //System.out.println("payloadMap = " + payloadMap);


        // 3. Send the request and get the response
        Response response = given(spec).body(payloadMap).when().post("{param}");
        response.prettyPrint();

        // Change response to jsonPath
        Map<String, Object> responseMap =  response.as(HashMap.class);

        // 4. Make Assertions
        assertEquals(201, response.statusCode());
        assertEquals(payloadMap.get("userId"), responseMap.get("userId"));
        assertEquals(payloadMap.get("title") , responseMap.get("title"));
        assertEquals(payloadMap.get("completed"), responseMap.get("completed"));


    }
}
