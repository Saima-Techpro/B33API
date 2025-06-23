package get_requests;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Get01 {

     /*
    1) We will use the Postman for manual testing.
    2) We will use the Rest Assured library for automation tests.
    3) Rest Assured makes use of the Gherkin Language for its predefined methods.
          - Given   : Context, preconditions.
          - When    : Actions, get(), post() etc.
          - Then    : Assertions, claims.
          - And     : Can be used for "multiple" situations.

     There are 4 steps for API Tests
            1. Set the URL / Endpoint
               Prepare the base url + path variables + query parameters (if needed)
            2. Set the payload / expected data
               Provide the information that you want to send (in POST, PUT , or PATCH requests)
            3. Send the request and get the response
            4. Make Assertions


      */

    @Test
    public void test01(){
//        1. Set the URL
        String url = "https://petstore.swagger.io/v2/pet/12";

//        2. Set the payload / expected data
//        3. Send the request and get the response

        Response response = given().when().get(url);
        response.print();        // Prints the response body in a single
        response.prettyPrint();  // Prints the response body in a JSON format

        System.out.println("Status Code = " + response.statusCode());
        System.out.println("Status Line = " + response.statusLine());
        System.out.println("All headers = " + response.headers());
        System.out.println("Response time = " + response.time());


//        4. Make Assertions

    }
}
