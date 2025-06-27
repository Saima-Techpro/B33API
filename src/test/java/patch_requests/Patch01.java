package patch_requests;

import base_urls.JsonPlaceholderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class Patch01 extends JsonPlaceholderBaseUrl {
       /*
    Given
        https://jsonplaceholder.typicode.com/todos/198
        {
            "title":"Attend the baby",
        }
    When
        User sends a PATCH request to the URL
    Then
        Status code should be 200
    And
        Response body should be this:
        {
            "userId":10,
            "title":"Attend the baby",
            "completed":true,
            "id":198
        }
     */

    @Test
    public void test01(){
        // 1. Set the URL
        spec.pathParams("first", "todos", "second", 198);

        // 2. Set the payload / expected data
        //String payload = "Attend the baby";
        //System.out.println("payload = " + payload);

        Map<String, Object> payloadMap = new HashMap<>();
        payloadMap.put("title", "Attend the baby");
        System.out.println("payloadMap = " + payloadMap);

        // 3. Send the request and get the response
        Response response = given(spec).body(payloadMap).when().patch("{first}/{second}");
        response.prettyPrint();

        // 4. Make Assertions
        //1st way: Keeping it Response data type + expected values as HARD CODES
//        response.
//                then().
//                statusCode(200).
//                body("userId", equalTo(10),
//                        "title", equalTo("Attend the baby"),
//                        "completed", equalTo(true));

        // 2nd way: Using JsonPath
        JsonPath jsonPath = response.jsonPath();

        // We set only one key=value pair above , BEFORE sending the payload in PATCH request
        // Once the PATCH request is sent and response is received, we can update our payloadMap
        // with other key=value pairs, so we can use them for assertions (if we don't, following assertions would fail)

        payloadMap.put("userId", 10);
        payloadMap.put("completed", true);
        System.out.println("payloadMap = " + payloadMap);

        assertEquals(payloadMap.get("userId"), jsonPath.getInt("userId"));
        assertEquals(payloadMap.get("title"), jsonPath.getString("title"));
        assertEquals(payloadMap.get("completed"), jsonPath.getBoolean("completed"));

        int actualId = jsonPath.getInt("id");

        // 3rd way: Using Maps
        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println("actualData = " + actualData);

        assertEquals(payloadMap.get("userId"), actualData.get("userId"));
        assertEquals(payloadMap.get("title"), actualData.get("title"));
        assertEquals(payloadMap.get("completed"), actualData.get("completed"));

        // OPTIONAL (if asked to verify the id coming from the backend)
        payloadMap.put("id", actualId);
        System.out.println("payloadMap = " + payloadMap);
        assertEquals(payloadMap.get("id"), actualData.get("id"));

    }

    @Test
    public void test02(){
        // 1. Set the URL
        spec.pathParams("first", "todos", "second", 198);

        // 2. Set the payload / expected data
        Map<String, Object> payloadMap = new HashMap<>();
        payloadMap.put("title", "Attend the baby");

        // 3. Send the request and get the response
        // Serialization : changing Map (Java object) to JSON object
        Response response = given(spec).body(payloadMap).when().patch("{first}/{second}");
        response.prettyPrint();

        // 4. Make Assertions
        // We set only one key=value pair above , BEFORE sending the payload in PATCH request
        // Once the PATCH request is sent and response is received, we can update our payloadMap
        // with other key=value pairs, so we can use them for assertions (if we don't, following assertions would fail)

        payloadMap.put("userId", 10);
        payloadMap.put("completed", true);
        System.out.println("payloadMap = " + payloadMap);

        // De-serialization : Changing JSON object back to Map (Java object)
        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println("actualData = " + actualData);

        assertEquals(payloadMap.get("userId"), actualData.get("userId"));
        assertEquals(payloadMap.get("title"), actualData.get("title"));
        assertEquals(payloadMap.get("completed"), actualData.get("completed"));

    }

}
