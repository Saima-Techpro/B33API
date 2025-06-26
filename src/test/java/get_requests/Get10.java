package get_requests;

import base_urls.JsonPlaceholderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get10 extends JsonPlaceholderBaseUrl {
     /*

    Given
        https://jsonplaceholder.typicode.com/todos
    When
        User sends a GET request to the URL
    Then
        Status code should be 200
    And
        Print the todos with IDs greater than 190
    And
        Verify that there are 10 todos with IDs greater than 190
    And
        Print the userIds of todos with IDs less than 5
    And
        Verify that there are 4 todos with IDs less than 5
    And
        Print the titles of todos with IDs less than 5
    And
        Verify that the title "delectus aut autem" belongs to a todo with ID less than 5.

        HW- to add above assertions on POSTMAN Scripts for this GET request
      */

    @Test
    public void test01(){
        // 1. Set the URL
        spec.pathParam("first", "todos");
        // 2. Set the payload / expected data

        // 3. Send the request and get the response
        Response response = given(spec).when().get("{first}");
        // response.prettyPrint();

        // 4. Make Assertions
        assertEquals(200, response.statusCode());

        JsonPath jsonPath = response.jsonPath();
        System.out.println(jsonPath.getInt("[0].id"));

        // Get the list of ids from the response body
        List<Integer> idList =  jsonPath.getList("id");
        System.out.println("idList = " + idList);

        // Print the todos with IDs greater than 190
        // 1st way: using Java logic
//        int counter = 0;
//        for ( Integer w:idList){
//            if (w > 190){
//                System.out.print(w + " ");
//                counter ++;
//            }
//        }
//
//        // Verify that there are 10 todos with IDs greater than 190
//        System.out.println();
//        System.out.println("counter = " + counter);
//        assertTrue(counter == 10);

        //2nd way: Using GROOVY

        // Print the todos with IDs greater than 190
        List<Integer>  todosIdList = jsonPath.getList("findAll{it.id>190}.id");
        System.out.println("todosIdList = " + todosIdList);
        assertEquals(10 , todosIdList.size());

        // To can store all 10 json objects in the list as well
        List<Object> todosList = jsonPath.getList("findAll{it.id>190}");
        System.out.println("todosList = " + todosList);


        // Print the userIds of todos with IDs less than 5
        List<Integer> userIdList = jsonPath.getList("findAll{it.id<5}.userId");
        System.out.println("userIdList = " + userIdList);
        // Verify that there are 4 todos with IDs less than 5
        assertEquals(4, userIdList.size());

        //Print the titles of todos with IDs less than 5
        List<String> titleList = jsonPath.getList("findAll{it.id < 5}.title");
        System.out.println("titleList = " + titleList);

        // Verify that the title "delectus aut autem" belongs to a todos with ID less than 5.
        assertTrue(titleList.contains("delectus aut autem"));
        // 0R
        //assertEquals("delectus aut autem", titleList.getFirst());

    }






}
