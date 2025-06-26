package post_requests;

import base_urls.JsonPlaceholderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Post03 extends JsonPlaceholderBaseUrl {

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
        String payload = "{\n" +
                "    \"userId\": 55,\n" +
                "    \"title\": \"Tidy your room\",\n" +
                "    \"completed\": false\n" +
                "}";

        // 3. Send the request and get the response
        Response response = given(spec).body(payload).when().post("{param}");
        response.prettyPrint();

        // 4. Make Assertions

        // To get the id allocated to this data by the system, we need to use jsonPath().
        JsonPath jsonPath = response.jsonPath();
        int allocatedId = jsonPath.getInt("id");
        System.out.println("allocatedId = " + allocatedId);


        response.
                then().
                statusCode(201).
                contentType(ContentType.JSON).
                body("userId", equalTo(55),
                        "title", equalTo("Tidy your room"),
                        "completed", equalTo(false), "id", equalTo(allocatedId));


        // We can use same jsonPath for JUnit assertions

    }
}
