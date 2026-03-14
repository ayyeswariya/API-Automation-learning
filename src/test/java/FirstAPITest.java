import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class FirstAPITest extends BaseTest{

    @Test
    public void getPosts() {

        given()
                //.baseUri("https://jsonplaceholder.typicode.com")
                .when()
                .get("/posts/1")
                .then()
                .log().all()
                .statusCode(200)
                .body("id", equalTo(1));

    }

    @Test
    public void createPost() {

        String requestBody = """
                {
                  "title": "API Testing",
                  "body": "Learning RestAssured",
                  "userId": 1
                }
                """;

        int postId =
                given()
                        //.baseUri("https://jsonplaceholder.typicode.com")
                       // .header("Content-Type", "application/json")
                        .body(requestBody)
                        .when()
                          .post("/posts")
                        .then()
                          .log().all()
                          .statusCode(201)
                          .extract()
                          .path("id");

        System.out.println("Created Post ID: " + postId);

        given()
                //.baseUri("https://jsonplaceholder.typicode.com")
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/posts")
                .then()
                .body("title", equalTo("API Testing"))
                .body("body", equalTo("Learning RestAssured"))
                .log().body();

    }

    @Test
    public void printResponse(){
        String requestBody = """
        {
          "title": "API Testing",
          "body": "Learning RestAssured",
          "userId": 1
        }
        """;

        Response response =
                request
                        //.baseUri("https://jsonplaceholder.typicode.com")
                        //.header("Content-Type", "application/json")
                        .body(requestBody)
                        .when()
                          .post("/posts")
                        .then()
                          .statusCode(201)
                          .extract()
                          .response();

        String actualTitle = response.path("title");
        int actualUserId = response.path("userId");

        Assert.assertEquals(actualTitle, "API Testing");
        System.out.println("✅ Title is matching");
    }

    @Test
    public void updatePost() {

        String updatedBody = """
            {
              "id": 1,
              "title": "Updated Title",
              "body": "Updated Body Content",
              "userId": 1
            }
            """;

        request
                //.baseUri("https://jsonplaceholder.typicode.com")
                //.header("Content-Type", "application/json")
                .body(updatedBody)
                .when()
                  .put("/posts/1")
                .then()
                    .log().all()
                    .statusCode(200)
                    .body("title", equalTo("Updated Title"))
                    .body("body", equalTo("Updated Body Content"));
    }

    @Test
    public void deletePost() {

        given()
                //.baseUri("https://jsonplaceholder.typicode.com")
                .when()
                    .delete("/posts/1")
                .then()
                    .log().all()
                    .statusCode(200);
    }

    @Test
    public void POJO(){
        Post post = new Post(
                "API Testing",
                "Learning RestAssured",
                1
        );
        String response =request
                .body(post)
                .when()
                    .post("/posts")
                .then()
                   // .log().all()
                    .statusCode(201)
                    .body("title", equalTo("API Testing"))
                .extract().response().asString();
        System.out.println("response is :" +response);
    }

    @Test
    public void Deserialization(){

        Post post = new Post(
                "API Testing",
                "Learning RestAssured",
                1
        );

        Post responsePost =
                request
                        .body(post)
                        .when()
                        .post("/posts")
                        .then()
                        .statusCode(201)
                        .log().all()
                        .extract()
                        .as(Post.class);

        Assert.assertEquals(responsePost.getTitle(), "API Testing");
        Assert.assertEquals(responsePost.getUserId(), 1);

        System.out.println("✅ Deserialization working perfectly");
    }

}