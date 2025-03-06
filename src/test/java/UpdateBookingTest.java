import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import lombok.extern.slf4j.Slf4j;
import oleksiiS.BaseTest;
import oleksiiS.dto.BookingRequest;
import oleksiiS.dto.BookingResponse;
import oleksiiS.helpers.ApiHelper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@Slf4j
@Epic("API tests for restful-booker")
@Feature("Update booking")
@Tag("api")
public class UpdateBookingTest extends BaseTest {

    @Test
    @DisplayName("Partial update a booking")
    @Description("Verifying partial updating of Booking with valid response")
    public void updateBookingTest() throws Exception{
        String lastnameForUpdate = "Syzonov";

        BookingRequest bookingObjectSetup = ApiHelper.setupDefaultBooking();
        log.info("Create pre-setup Booking");
        BookingResponse bookingObjectCreated = ApiHelper.createBooking(bookingObjectSetup);

        bookingObjectCreated.getBooking().setLastname(lastnameForUpdate);

        log.info("Partial update of the Booking with 'lastname'");
        BookingRequest bookingResponseUpdated = ApiHelper.partialUpdateBooking(Map.of("lastname", lastnameForUpdate), bookingObjectCreated.getBookingid());

        log.info("Verify that Booking field 'lastname' is updated");
        assertThat(bookingObjectCreated.getBooking())
                .usingRecursiveComparison()
                .isEqualTo(bookingResponseUpdated);
    }

    @Test
    @DisplayName("Full update without required fields")
    @Description("Verify that full updating without all required field is not allowed")
    public void updateBookingWithoutRequiredField() throws Exception{
        BookingRequest bookingObjectSetup = ApiHelper.setupDefaultBooking();
        log.info("Create pre-setup Booking");
        BookingResponse bookingObjectCreated = ApiHelper.createBooking(bookingObjectSetup);

        bookingObjectCreated.getBooking().setLastname(null);

        log.info("Check that full updating of Booking without required fields is not allowed");
        assertThatThrownBy(() -> ApiHelper.fullUpdateBooking(bookingObjectCreated))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("Expected status code <200> but was <400>");
    }
}