package ru.zeburek.testngrestassured;

import com.github.javafaker.Faker;
import ru.zeburek.testngrestassured.types.BookingInfoType;

import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

public class Random {
    private static Faker faker = new Faker();

    public static BookingInfoType randomBookingInfoType() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return new BookingInfoType(
                faker.name().firstName(),
                faker.name().lastName(),
                faker.random().nextInt(1, 100),
                true,
                formatter.format(faker.date().past(100, TimeUnit.DAYS)),
                formatter.format(faker.date().future(100, TimeUnit.DAYS)),
                faker.food().ingredient());
    }
}
