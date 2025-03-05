import io.qameta.allure.Description;
import oleksiiS.BaseTest;
import oleksiiS.dto.BookingRequest;
import oleksiiS.helpers.ApiHelper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class DeleteBookingTests extends BaseTest {

    @Test
    @DisplayName("Successful delete a booking")
    @Description("Verifying deleting of Booking by Id")
    public void createBookingTest() throws Exception{
        BookingRequest bookingObjectSetup = ApiHelper.setupDefaultBooking();
        int bookingId = ApiHelper.createBooking(bookingObjectSetup).getBookingid();

        ApiHelper.deleteBooking(bookingId);

        assertThatThrownBy(() -> ApiHelper.getBookingById(bookingId))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("Expected status code <200> but was <404>");
    }
}