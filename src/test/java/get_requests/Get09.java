package get_requests;

import base_urls.ContactListBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Get09 extends ContactListBaseUrl {

    /*
    Given
        https://thinking-tester-contact-list.herokuapp.com/contacts
    When
        User sends a GET request to this endpoint
    Then
        Status code should be 200

     */

    @Test
    public void test01(){
        //1. Set the URL
        spec.pathParam("first", "contacts");

        // 2. Set the payload / expected data
        // 3. Send the request and get the response
        Response response = given(spec).when().get("{first}");
        response.prettyPrint();
        // 4. Make Assertions
    }
}
