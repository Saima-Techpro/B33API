package post_requests;

import base_urls.PetStoreBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Post01 extends PetStoreBaseUrl {

    /*
    Given
        https://petstore.swagger.io/v2/pet
    When
        User sends a POST request to this endpoint
    And
        {
      "id": 25,
      "category": {
        "id": 0,
        "name": "Cat"
      },
      "name": "Ginger",
      "photoUrls": [
        "string"
      ],
      "tags": [
        {
          "id": 0,
          "name": "Male"
        }
      ],
      "status": "available"
    }
    Then
        Status code should be 200
    And
        Content type should be "application/json"

     */
    @Test
    public void test01(){
     // 1. Set the URL
        spec.pathParam("first", "pet");
     // 2. Set the payload / expected data

        String payload = "{\n" +
                "  \"id\": 25,\n" +
                "  \"category\": {\n" +
                "    \"id\": 0,\n" +
                "    \"name\": \"Cat\"\n" +
                "  },\n" +
                "  \"name\": \"Ginger\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"string\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 0,\n" +
                "      \"name\": \"Male\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"available\"\n" +
                "}";
        System.out.println(payload);


        // 3. Send the request and get the response
        Response response = given(spec).body(payload).when().post("{first}");
        response.prettyPrint();

     // 4. Make Assertions
        response.then().statusCode(200).contentType(ContentType.JSON);




    }


}
