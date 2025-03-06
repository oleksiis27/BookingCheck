package oleksiiS;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;


import java.util.Map;

import static io.restassured.RestAssured.given;

@Slf4j
public abstract class BaseTest {

    private static final ApiConfig bookingConfig = ConfigFactory.create(ApiConfig.class, System.getProperties());
    protected static RequestSpecification spec;

    public static RequestSpecification getRequestSpec() {
        return spec;
    }

    @BeforeAll
    public static void setup(){
        healthCheck();

        log.info("Setup");
        spec = new RequestSpecBuilder()
                .setBaseUri(bookingConfig.baseUrl())
                .setContentType(ContentType.JSON)
                .addHeader("Cookie", "token=" + getWebAuthToken())
                .build();
    }

    public static String getWebAuthToken() {
        log.info("Getting WebAuthToken");
        return given().contentType(ContentType.JSON)
                .baseUri(bookingConfig.baseUrl())
                .body(Map.of("username", bookingConfig.username(),
                        "password", bookingConfig.password()))
                .when().post("/auth")
                .then().statusCode(200)
                .extract().jsonPath().getString("token");
    }

    public static void healthCheck() {
        log.info("Health check verification");
        given()
                .baseUri(bookingConfig.baseUrl())
                .when()
                .get("/ping")
                .then()
                .assertThat().statusCode(201);
    }
}