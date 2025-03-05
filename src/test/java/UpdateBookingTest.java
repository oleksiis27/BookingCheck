import io.qameta.allure.Description;
import oleksiiS.BaseTest;
import oleksiiS.dto.BookingRequest;
import oleksiiS.dto.BookingResponse;
import oleksiiS.helpers.ApiHelper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class UpdateBookingTest extends BaseTest {

    @Test
    @DisplayName("Partial update a booking")
    @Description("Verifying partial updating of Booking with valid response")
    public void updateBookingTest() throws Exception{
        String lastnameForUpdate = "Syzonov";

        BookingRequest bookingObjectSetup = ApiHelper.setupDefaultBooking();
        BookingResponse bookingObjectCreated = ApiHelper.createBooking(bookingObjectSetup);

        bookingObjectCreated.getBooking().setLastname(lastnameForUpdate);

        BookingRequest bookingResponseUpdated = ApiHelper.partialUpdateBooking(Map.of("lastname", lastnameForUpdate), bookingObjectCreated.getBookingid());

        assertThat(bookingObjectCreated.getBooking())
                .usingRecursiveComparison()
                .isEqualTo(bookingResponseUpdated);
    }

    @Test
    @DisplayName("Full update without required fields")
    @Description("Verify that full updating without all required field is not allowed")
    public void updateBookingWithoutRequiredField() throws Exception{
        BookingRequest bookingObjectSetup = ApiHelper.setupDefaultBooking();
        BookingResponse bookingObjectCreated = ApiHelper.createBooking(bookingObjectSetup);

        bookingObjectCreated.getBooking().setLastname(null);

        assertThatThrownBy(() -> ApiHelper.fullUpdateBooking(bookingObjectCreated))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("Expected status code <200> but was <400>");
    }
}