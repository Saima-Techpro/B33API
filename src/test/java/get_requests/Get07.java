package get_requests;

import base_urls.Restful_BookerBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Get07 extends Restful_BookerBaseUrl {
    /*
    Given
    https://restful-booker.herokuapp.com/booking/4623
    When
        User sends a GET request to the URL
    Then
        HTTP Status Code should be 200
    And
        Content type should be "application/json"
    And
        Response body should be like this:
    {
        "firstname": "Bob",
        "lastname": "Smith",
        "totalprice": 111,
        "depositpaid": true,
        "bookingdates": {
            "checkin": "2018-01-01",
            "checkout": "2019-01-01"
        },
        "additionalneeds": "Breakfast"
    }
     */


    @Test
    public void test01(){
        //https://restful-booker.herokuapp.com/booking/4623
        // 1. Set the URL / endpoint
        spec.pathParams("first", "booking", "second", 1258);

        // 2. Set the payload / expected data
        // 3. Send the request and get the response
        Response response = given(spec).when().get("{first}/{second}");
        response.prettyPrint();
        // 4. Make Assertions

        response.
                then().
                statusCode(200).
                contentType(ContentType.JSON).
                body("firstname", equalTo("Bob")).
                body("lastname", equalTo("Smith")).
                body("totalprice", equalTo(111)).
                body("depositpaid", equalTo(true)).
                body("bookingdates.checkin", equalTo("2018-01-01")).
                body("bookingdates.checkout", equalTo("2019-01-01")).
                body("additionalneeds", equalTo("Breakfast"));

    }


}
