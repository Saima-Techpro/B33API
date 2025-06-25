package get_requests;

import base_urls.PetStoreBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Get06 extends PetStoreBaseUrl {
    /*
     Given
           https://petstore.swagger.io/v2/pet/findByStatus?status=available
     When
          User sends the GET request to the URL
     Then
          HTTP Status Code should be 200
     And
          The Content-Type should be "application/json"
     And
          There should be an item in the list with an Id of 77
     And
          There should be an item in the list with a name value of "Sloth"
     And
          There should be items in the list with name values of "scooby", "fish", "doggie"
     And
          There should be at least 200 items in the list
     And
          There should be less than 500 items in the list
     And  ----- HW --------
          The first item in the list should have a category - id 0
     And
         The first item in the list should have a photoUrls value of "string"
     And
         The first item in the list should have tags - id 0
     */

    @Test
    public void test01(){
        // 1. Set the URL
        spec.pathParams("first", "pet", "second", "findByStatus").
                queryParam("status", "available");

        // 2. Set the payload / expected data
        // 3. Send the request and get the response
        Response response = given(spec).when().get("{first}/{second}");
        // response.prettyPrint();

        // 4. Make Assertions
        response.
                then().
                statusCode(200).
                contentType(ContentType.JSON).
                body("id", hasItem(70)).
                body("name", hasItem("Sloth")).
                body("name", hasItems("scooby", "Sloth", "doggie")).
                body("photoUrls", hasSize(greaterThan(200))).
                body("photoUrls", hasSize(lessThan(500))).
                body("[0].category.id", equalTo(0)).
                body("[0].photoUrls[0]", equalTo("string")).
                body("[0].tags[0].id", equalTo(0));

    }


    @Test
    public void test02(){
        // 1. Set the URL
        spec.pathParams("first", "pet", "second", "findByStatus").
                queryParam("status", "available");

        // 2. Set the payload / expected data
        // 3. Send the request and get the response
        Response response = given(spec).when().get("{first}/{second}");
        //response.prettyPrint();

        // 4. Make Assertions
        response.
                then().
                statusCode(200).
                contentType(ContentType.JSON).
                body("id", hasItem(70) ,
                        "name", hasItem("Sloth"),
                        "name", hasItems("scooby", "Sloth", "doggie"),
                        "photoUrls", hasSize(greaterThan(200)),
                        "photoUrls", hasSize(lessThan(500)),
                        "[0].category.id", equalTo(0),
                        "[0].photoUrls[0]", equalTo("string"),
                        "[0].tags[0].id", equalTo(0));

        // NOTE: This assertion with single body() works the same way as SOFT ASSERTION works in TESTNG

    }

}
