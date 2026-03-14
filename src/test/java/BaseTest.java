import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    protected RequestSpecification request;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        request = RestAssured.given()
                .header("Content-Type", "application/json");
                //.header("Authorization", "Bearer " + token);
    }
}