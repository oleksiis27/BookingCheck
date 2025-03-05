import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import oleksiiS.BaseTest;
import oleksiiS.dto.BookingRequest;
import oleksiiS.dto.BookingResponse;
import oleksiiS.helpers.ApiHelper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@Epic("API tests for restful-booker")
@Feature("Create and update booking")
@Tag("api")
public class CreateBookingTests extends BaseTest {

    @Test
    @DisplayName("Successful create a new booking")
    @Description("Verifying creation of Booking with valid response")
    public void createBookingTest() throws Exception{
        BookingRequest bookingObjectSetup = ApiHelper.setupDefaultBooking();
        BookingResponse bookingObjectCreated = ApiHelper.createBooking(bookingObjectSetup);

        assertThat(bookingObjectCreated.getBooking())
                .usingRecursiveComparison()
                .isEqualTo(bookingObjectSetup);
    }
}