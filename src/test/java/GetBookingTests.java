import io.qameta.allure.Description;
import oleksiiS.BaseTest;
import oleksiiS.dto.BookingRequest;
import oleksiiS.dto.BookingResponse;
import oleksiiS.helpers.ApiHelper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class GetBookingTests extends BaseTest {

    @Test
    @DisplayName("Get Booking by Id")
    @Description("Verifying getting Booking by id")
    public void getBookingByIdTest() throws Exception{
        BookingRequest bookingObjectSetup = ApiHelper.setupDefaultBooking();
        int bookingId = ApiHelper.createBooking(bookingObjectSetup).getBookingid();

        BookingRequest bookingFetched  = ApiHelper.getBookingById(bookingId);

        assertThat(bookingFetched)
                .usingRecursiveComparison()
                .isEqualTo(bookingObjectSetup);
    }

    @Test
    @DisplayName("Get Booking by Full Name")
    @Description("Verifying getting Booking by Full Name")
    public void getBookingByFullName() throws Exception{
        BookingRequest bookingObjectSetup = ApiHelper.setupDefaultBooking();
        BookingResponse bookingObjectCreated = ApiHelper.createBooking(bookingObjectSetup);
        Map<String, Object> params = new HashMap<>();
        params.put("firstname", bookingObjectSetup.getFirstname());
        params.put("lastname", bookingObjectSetup.getLastname());

        List<Integer> ids = ApiHelper.getBookingByParam(params);

        assertThat(ids.size()).isEqualTo(1);
        assertThat(ids.getFirst()).isEqualTo(bookingObjectCreated.getBookingid());
    }
}