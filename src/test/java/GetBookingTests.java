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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Slf4j
@Epic("API tests for restful-booker")
@Feature("Get booking")
@Tag("api")
public class GetBookingTests extends BaseTest {

    @Test
    @DisplayName("Get Booking by Id")
    @Description("Verifying getting Booking by id")
    public void getBookingByIdTest() throws Exception{
        BookingRequest bookingObjectSetup = ApiHelper.setupDefaultBooking();
        log.info("Create pre-setup Booking");
        int bookingId = ApiHelper.createBooking(bookingObjectSetup).getBookingid();

        log.info("Get created Booking by id");
        BookingRequest bookingFetched  = ApiHelper.getBookingById(bookingId);

        log.info("Verify that fetched booking response is equal to created");
        assertThat(bookingFetched)
                .usingRecursiveComparison()
                .isEqualTo(bookingObjectSetup);
    }

    @Test
    @DisplayName("Get Booking by Full Name")
    @Description("Verifying getting Booking by Full Name")
    public void getBookingByFullName() throws Exception{
        BookingRequest bookingObjectSetup = ApiHelper.setupDefaultBooking();
        log.info("Create pre-setup Booking");
        BookingResponse bookingObjectCreated = ApiHelper.createBooking(bookingObjectSetup);

        Map<String, Object> params = new HashMap<>();
        params.put("firstname", bookingObjectSetup.getFirstname());
        params.put("lastname", bookingObjectSetup.getLastname());

        log.info("Get created Booking by full name query param");
        List<Integer> ids = ApiHelper.getBookingByParam(params);

        log.info("Check that fetched id is equal to created");
        assertThat(ids.size()).isEqualTo(1);
        assertThat(ids.getFirst()).isEqualTo(bookingObjectCreated.getBookingid());
    }
}