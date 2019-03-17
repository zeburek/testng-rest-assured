package ru.zeburek.testngrestassured.types;

public class BookingDatesType extends BaseType {
    public String checkin;
    public String checkout;

    public BookingDatesType(String checkin, String checkout){
        this.checkin = checkin;
        this.checkout = checkout;
    }
}
