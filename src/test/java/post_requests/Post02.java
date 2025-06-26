package post_requests;

import base_urls.ContactListBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post02 extends ContactListBaseUrl {

        /*
    HW TASK:
    Given
        https://thinking-tester-contact-list.herokuapp.com/contacts
    When
        User sends a POST request to this endpoint
    And
            {
        "firstName": "Jake",
        "lastName": "Smith",
        "birthdate": "1985-01-01",
        "email": "jSmith@fake.com",
        "phone": "8005779555",
        "street1": "1 Main St.",
        "street2": "Apartment A",
        "city": "London",
        "stateProvince": "Yorkshire",
        "postalCode": "56745",
        "country": "UK"
      }
    Then
        Status code should be 201
    And
        Verify response body values

     */

    @Test
    public void test01(){
        // 1. Set the URL
        spec.pathParam("first","contacts");
        // 2. Set the payload / expected data
        String payload="\n" +
                "            {\n" +
                "        \"firstName\": \"Jake\",\n" +
                "        \"lastName\": \"Smith\",\n" +
                "        \"birthdate\": \"1985-01-01\",\n" +
                "        \"email\": \"jSmith@fake.com\",\n" +
                "        \"phone\": \"8005779555\",\n" +
                "        \"street1\": \"1 Main St.\",\n" +
                "        \"street2\": \"Apartment A\",\n" +
                "        \"city\": \"London\",\n" +
                "        \"stateProvince\": \"Yorkshire\",\n" +
                "        \"postalCode\": \"56745\",\n" +
                "        \"country\": \"UK\"\n" +
                "      }";


        // 3. Send the request and get the response

        Response response=given(spec).body(payload).when().post("{first}");
        response.prettyPrint();
        // 4. Make Assertions
      /*  response.
                then().
                statusCode(201).
       body("firstName",equalTo("Jake")).
                body("lastName",equalTo("Smith")).
                body("birthdate",equalTo("1985-01-01")).
                body("email",equalTo("jsmith@fake.com")).
                body("phone",equalTo("8005779555")).

                body("street1",equalTo("1 Main St.")).
                body("street2",equalTo("Apartment A")).

                body("city",equalTo("London")).
                body("stateProvince",equalTo("Yorkshire")).
                body("postalCode",equalTo("56745")).
                body("country",equalTo("UK"));

       */


        JsonPath jsonPath=response.jsonPath();
        assertEquals(jsonPath.getString("firstName"),"Jake");
        assertEquals(jsonPath.getString("lastName"),"Smith");
        assertEquals(jsonPath.getString("birthdate"),"1985-01-01");
        assertEquals(jsonPath.getString("email"),"jsmith@fake.com");
        assertEquals(jsonPath.getString("phone"),"8005779555");
        assertEquals(jsonPath.getString("street1"),"1 Main St.");
        assertEquals(jsonPath.getString("street2"),"Apartment A");
        assertEquals(jsonPath.getString("city"),"London");
        assertEquals(jsonPath.getString("stateProvince"),"Yorkshire");
        assertEquals(jsonPath.getString("postalCode"),"56745");
        assertEquals(jsonPath.getString("country"),"UK");


    }



}
