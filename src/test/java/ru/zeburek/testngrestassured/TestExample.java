package ru.zeburek.testngrestassured;

import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.zeburek.testngrestassured.types.BookingInfoType;
import ru.zeburek.testngrestassured.types.BookingType;

@Listeners(LogListener.class)
@Epic("Test example")
@Story("Working with bookings")
public class TestExample extends TestBase {

    @Test(description = "Creating new booking and verifying response")
    public void createNewBooking(){
        BookingInfoType data = Random.randomBookingInfoType();
        Response res = client.createBooking(data);
        res.then().statusCode(200);
        BookingType created = res.then().extract().body().as(BookingType.class);
        Assert.assertNotEquals(created.bookingid, 0);
        Assert.assertEquals(created.booking, data);
    }

    @Test(description = "Verifying that new booking exists after creation")
    public void newBookingExists(){
        BookingInfoType data = Random.randomBookingInfoType();
        Response res = client.createBooking(data);
        res.then().statusCode(200);
        BookingType created = res.then().extract().body().as(BookingType.class);
        res = client.getBooking(created.bookingid);
        res.then().statusCode(200);
        BookingInfoType updated = res.then().extract().body().as(BookingInfoType.class);
        Assert.assertEquals(updated, data);
    }

    @Test(description = "Updating created booking with new data")
    public void updateBooking(){
        BookingInfoType data = Random.randomBookingInfoType();
        Response res = client.createBooking(data);
        res.then().statusCode(200);
        BookingType created = res.then().extract().body().as(BookingType.class);
        data = Random.randomBookingInfoType();
        res = client.updateBooking(created.bookingid, data);
        res.then().statusCode(200);
        BookingInfoType updated = res.then().extract().body().as(BookingInfoType.class);
        Assert.assertEquals(updated, data);
    }

    @Test(description = "Verifying that not created booking does not exist")
    public void notExistingBookingDoesNotPresent(){
        Response res = client.getBooking(faker.random().nextInt(10000, 99999));
        res.then().statusCode(404);
    }
}
