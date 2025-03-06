import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import lombok.extern.slf4j.Slf4j;
import oleksiiS.BaseTest;
import oleksiiS.dto.BookingRequest;
import oleksiiS.helpers.ApiHelper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@Slf4j
@Epic("API tests for restful-booker")
@Feature("Delete booking")
@Tag("api")
public class DeleteBookingTests extends BaseTest {



    @Test
    @DisplayName("Successful delete a booking")
    @Description("Verifying deleting of Booking by Id")
    public void createBookingTest() throws Exception{
        BookingRequest bookingObjectSetup = ApiHelper.setupDefaultBooking();
        log.info("Create pre-setup Booking");
        int bookingId = ApiHelper.createBooking(bookingObjectSetup).getBookingid();

        log.info("Delete the Booking");
        ApiHelper.deleteBooking(bookingId);

        log.info("Check that the deleted Booking can't be fetched by id");
        assertThatThrownBy(() -> ApiHelper.getBookingById(bookingId))
                .isInstanceOf(AssertionError.class)
                .hasMessageContaining("Expected status code <200> but was <404>");
    }
}