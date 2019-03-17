package ru.zeburek.testngrestassured.types;

public class BookingInfoType extends BaseType {
    public String firstname;
    public String lastname;
    public int totalprice;
    public Boolean depositpaid;
    public BookingDatesType bookingdates;
    public String additionalneeds;

    public BookingInfoType(String firstname, String lastname, int totalprice, Boolean depositpaid,
                           String checkin, String checkout, String additionalneeds){
        this(firstname, lastname, totalprice, depositpaid, new BookingDatesType(checkin, checkout), additionalneeds);
    }

    public BookingInfoType(String firstname, String lastname, int totalprice, Boolean depositpaid,
                           BookingDatesType bookingdates, String additionalneeds){
        this.firstname = firstname;
        this.lastname = lastname;
        this.totalprice = totalprice;
        this.depositpaid = depositpaid;
        this.bookingdates = bookingdates;
        this.additionalneeds = additionalneeds;
    }
}
