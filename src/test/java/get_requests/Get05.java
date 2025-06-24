package get_requests;

import base_urls.PetStoreBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Get05 extends PetStoreBaseUrl {
    /*
      Given
        User goes to https://petstore.swagger.io/v2/pet/70
    When
        User sends GET request to this endpoint
    Then
        HTTP Status code is 200
    And
        Content Type is "application/json"
     */

    @Test
    public void test01(){
        // 1. Set the URL

        spec.pathParams("first", "pet", "second", 70);


        // 2. Set the payload / expected data
        // 3. Send the request and get the response
        // given().spec(spec).when().get();
        Response response = given(spec).when().get("{first}/{second}");
        response.prettyPrint();


        // 4. Make Assertions
        response.then().statusCode(200).contentType(ContentType.JSON);

    }
}
