package put_requests;

import base_urls.JsonPlaceholderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class Put01 extends JsonPlaceholderBaseUrl {
       /*
    Given
        https://jsonplaceholder.typicode.com/todos/198
        {
            "userId":55,
            "title":"Wash the dishes",
            "completed":false
        }
    When
        User sends a PUT request to the URL
    Then
        Status code should be 200
    And
        Response body should be this:
        {
            "userId":55,
            "title":"Wash the dishes",
            "completed":false,
            "id":198
        }
     */

    @Test
    public void test01(){
        // 1. Set the URL
        spec.pathParams("first", "todos", "second", 198);

        // 2. Set the payload / expected data
        String payload = " {\n" +
                "            \"userId\":55,\n" +
                "            \"title\":\"Wash the dishes\",\n" +
                "            \"completed\":false\n" +
                "        }";
        // System.out.println("payload = " + payload);

        Map<String, Object> payloadMap = new HashMap<>();
        payloadMap.put("userId", 55);
        payloadMap.put("title", "Wash the dishes");
        payloadMap.put("completed", false);

        System.out.println("payloadMap = " + payloadMap);

        // 3. Send the request and get the response
        Response response = given(spec).body(payloadMap).when().put("{first}/{second}");
        response.prettyPrint();

        // 4. Make Assertions
        //1st way: Keeping it Response data type
        response.
                then().
                statusCode(200).
                body("userId", equalTo(55),
                        "title", equalTo("Wash the dishes"),
                        "completed", equalTo(false));

        // 2nd way: Using JsonPath
        JsonPath jsonPath = response.jsonPath();
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
}
