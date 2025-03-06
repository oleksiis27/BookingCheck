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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Slf4j
@Epic("API tests for restful-booker")
@Feature("Create booking")
@Tag("api")
public class CreateBookingTests extends BaseTest {

    @Test
    @DisplayName("Successful create a new booking")
    @Description("Verifying creation of Booking with valid response")
    public void createBookingTest() throws Exception{
        BookingRequest bookingObjectSetup = ApiHelper.setupDefaultBooking();
        log.info("Create pre-setup Booking");
        BookingResponse bookingObjectCreated = ApiHelper.createBooking(bookingObjectSetup);

        log.info("Verify that created Booking response is equal to pre-setup");
        assertThat(bookingObjectCreated.getBooking())
                .usingRecursiveComparison()
                .isEqualTo(bookingObjectSetup);
    }
}