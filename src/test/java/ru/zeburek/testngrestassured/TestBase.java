package ru.zeburek.testngrestassured;

import com.github.javafaker.Faker;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;

public class TestBase {

    String host = "https://restful-booker.herokuapp.com";
    String username = "admin";
    String password = "password123";
    RestfulBookerClient client;
    Faker faker = new Faker();

    @BeforeClass(description = "Initializing REST API client")
    public void initRestClient(ITestContext context) {
        client = new RestfulBookerClient(host, username, password);
        client.authorize();
    }

}
