# BookingCheck

This is a learning project for testing the [restful-booker](https://restful-booker.herokuapp.com/) API.
It includes automated tests written in **Java** using **Rest Assured**, **JUnit 5**, **Allure**, **Lombok**, **Slf4j**, **JavaFaker** and **Gradle**.

## Overview
The project contains several test classes that cover creating, deleting, retrieving, and updating bookings:

- **CreateBookingTests**
  - Checks the creation of a booking and verifies the response matches the request data.

- **DeleteBookingTests**
  - Creates a new booking, then deletes it by ID.
  - Ensures a subsequent attempt to retrieve the deleted booking returns a 404 status.

- **GetBookingTests**
  - Verifies that retrieving a booking by ID returns the correct data.
  - Checks that searching by query parameters (e.g., `firstname`, `lastname`) returns the expected booking IDs.

- **UpdateBookingTests**
  - **Partial update** (PATCH): Updates only selected fields (e.g., `lastname`) and expects a valid response.
  - **Full update without required fields** (negative case): Attempts a PUT with missing mandatory fields, expecting an error (e.g., 400 or an assertion failure).

## Technologies
- **Java 17+**
- **Gradle** (for build and dependency management)
- **Rest Assured** (for HTTP requests and assertions)
- **JUnit 5** (for test framework)
- **Allure** (for reporting)
- **Lombok** (to reduce boilerplate code)
- **Slf4j** (for logging)
-**JavaFaker** (for random data generating)

## Project Structure
- `src/test/java/`
  Contains the test classes (`CreateBookingTests`, `DeleteBookingTests`, `GetBookingTests`, `UpdateBookingTests`) and helpers.
- `src/test/resources/config.properties`
  Stores configuration (e.g., `baseUrl`, `username`, `password`).
- **BaseTest**
  Sets up the main `RequestSpecification`.
- **ApiHelper**
  Contains methods for creating, deleting, retrieving, and updating bookings (e.g., `createBooking()`, `deleteBooking()`, `getBookingById()`, `partialUpdateBooking()`, etc.).

## Running Tests
1. **Clone** the repository:
   ```bash
   git clone https://github.com/oleksiis27/BookingCheck.git
2. **Navigate** to the project folder:
    cd BookingCheck
3. **Run tests** with Gradle:
    ./gradlew clean test
4. **Allure** reports (optional):
    ./gradlew allureReport
    ./gradlew allureServe
The first command generates the Allure report, the second command serves it locally in a browser.