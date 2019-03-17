package ru.zeburek.testngrestassured.types;

public class BookingType extends BaseType {
    public int bookingid;
    public BookingInfoType booking;

    public BookingType(int bookingid, BookingInfoType booking){
        this.bookingid = bookingid;
        this.booking = booking;
    }

    public BookingType(int bookingid, String firstname, String lastname, int totalprice, Boolean depositpaid,
                       BookingDatesType bookingdates, String additionalneeds){
        this(
                bookingid,
                new BookingInfoType(firstname, lastname, totalprice, depositpaid, bookingdates, additionalneeds)
        );
    }

    public BookingType(int bookingid, String firstname, String lastname, int totalprice, Boolean depositpaid,
                       String checkin, String checkout, String additionalneeds){
        this(
                bookingid,
                new BookingInfoType(firstname, lastname, totalprice, depositpaid, checkin, checkout, additionalneeds)
        );
    }
}
