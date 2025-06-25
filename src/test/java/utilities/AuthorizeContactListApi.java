package utilities;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AuthorizeContactListApi {

//    public static void main(String[] args) {
//        System.out.println(generateToken());
//    }

    public static String generateToken(){
        String url  = "https://thinking-tester-contact-list.herokuapp.com/users/login";

        String credentials = "{\n" +
                "    \"email\": \"jBeanstalk@mail.com\",\n" +
                "    \"password\": \"1234567\"\n" +
                "}";

        Response response = given().
                            body(credentials).
                            contentType(ContentType.JSON).
                            when().post(url);

        //response.prettyPrint();

        return  response.jsonPath().getString("token");
    }
}
