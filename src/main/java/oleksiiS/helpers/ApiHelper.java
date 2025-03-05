package oleksiiS.helpers;

import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import oleksiiS.dto.BookingDatesObject;
import oleksiiS.dto.BookingRequest;
import oleksiiS.dto.BookingResponse;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static oleksiiS.BaseTest.getRequestSpec;


public class ApiHelper {

    final static Faker faker = new Faker();

    @Step("Create Booking")
    public static BookingResponse createBooking(BookingRequest bookingRequest) {
        return given()
                .spec(getRequestSpec())
                .body(bookingRequest)
                .when()
                .post("/booking")
                .then()
                .statusCode(200)
                .extract()
                .as(BookingResponse.class);
    }

    @Step("Get Booking by id")
    public static BookingRequest getBookingById(int id) {
        return given()
                .spec(getRequestSpec())
                .when()
                .get("/booking/" + id)
                .then()
                .statusCode(200)
                .extract()
                .as(BookingRequest.class);
    }

    @Step
    public static List<Integer> getBookingByParam(Map<String, Object> queryParam) {
        return given()
                .spec(getRequestSpec())
                .queryParams(queryParam)
                .when()
                .get("/booking")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath().getList("bookingid", Integer.class);
    }

    @Step("Delete Booking by id")
    public static void deleteBooking(int id) {
        given()
                .spec(getRequestSpec())
                .when()
                .delete("/booking/" + id)
                .then()
                .statusCode(201).log().all();
    }

    @Step("Update full Booking info by id")
    public static BookingRequest fullUpdateBooking(BookingResponse bookingResponse){
        return given()
                .spec(getRequestSpec())
                .body(bookingResponse.getBooking())
                .when()
                .put("/booking/" + bookingResponse.getBookingid())
                .then()
                .statusCode(200)
                .extract()
                .as(BookingRequest.class);
    }

    @Step("Update partial Booking info by id")
    public static BookingRequest partialUpdateBooking(Map<String, Object> partialBody, int id){
        return given()
                .spec(getRequestSpec())
                .body(partialBody)
                .when()
                .patch("/booking/" + id)
                .then()
                .statusCode(200)
                .extract()
                .as(BookingRequest.class);
    }

    @Step("Setup default Booking")
    public static BookingRequest setupDefaultBooking() throws Exception {
        return BookingRequest.builder()
                .firstname(faker.name().firstName())
                .lastname(faker.name().lastName())
                .totalprice(faker.number().numberBetween(100, 1000))
                .depositpaid(faker.bool().bool())
                .bookingdates(getDefaultBookingDates())
                .additionalneeds(faker.lebowski().quote())
                .build();
    }

    public static BookingDatesObject getDefaultBookingDates() throws Exception {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Date startDate = new Date();
        Date endDate = new Date(startDate.getTime() + TimeUnit.DAYS.toMillis(7));

        Date randomDate = faker.date().between(startDate, endDate);

        LocalDate checkInDate = randomDate.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        LocalDate checkOutDate = checkInDate.plusDays(faker.number().numberBetween(1, 30));

        return BookingDatesObject.builder()
                .checkin(checkInDate.format(dateTimeFormatter))
                .checkout(checkOutDate.format(dateTimeFormatter))
                .build();
    }
}