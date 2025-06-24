package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class PetStoreBaseUrl {

    /*
    The purpose of this class is to increase the maintainability of tests by configuring the
    common test requirements such as base url, content type etc. through this common central class.
     */

    protected RequestSpecification spec;

    @Before
    public void setUp(){
        spec = new RequestSpecBuilder().
                setBaseUri("https://petstore.swagger.io/v2").
                setContentType(ContentType.JSON).build();
    }
}
