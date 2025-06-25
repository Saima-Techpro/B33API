package get_requests;

import base_urls.Restful_BookerBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class Get08 extends Restful_BookerBaseUrl {

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
        spec.pathParams("first", "booking", "second", 219);

        // 2. Set the payload / expected data
        // 3. Send the request and get the response
        Response response = given(spec).when().get("{first}/{second}");
        response.prettyPrint();

        // 4. Make Assertions
        JsonPath jsonPath = response.jsonPath();
        System.out.println(jsonPath.getString("firstname"));

        assertEquals(jsonPath.getString("firstname"), "Bob");
        assertEquals(jsonPath.getString("lastname") , "Smith");
        assertEquals(jsonPath.getInt("totalprice") , 111);
        assertEquals(jsonPath.getBoolean("depositpaid") , true);
        assertEquals(jsonPath.getString("bookingdates.checkin") , "2018-01-01");
        assertEquals(jsonPath.getString("bookingdates.checkout") , "2019-01-01");
        assertEquals(jsonPath.getString("additionalneeds"), "Breakfast");



    }
}
