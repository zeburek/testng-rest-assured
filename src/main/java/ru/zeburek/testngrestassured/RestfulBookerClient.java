package ru.zeburek.testngrestassured;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.io.IoBuilder;
import ru.zeburek.testngrestassured.types.AuthResponseType;
import ru.zeburek.testngrestassured.types.AuthType;
import ru.zeburek.testngrestassured.types.BookingInfoType;

import java.io.PrintStream;

import static io.restassured.RestAssured.given;

public class RestfulBookerClient {

    private Logger logger = LogManager.getLogger(RestfulBookerClient.class);
    private PrintStream logStream = IoBuilder.forLogger(logger).buildPrintStream();

    public String username;
    public String password;
    public String host;

    private RequestSpecification reqSpecs = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .addFilter(new RequestLoggingFilter(LogDetail.METHOD, logStream))
            .addFilter(new RequestLoggingFilter(LogDetail.URI, logStream))
            .addFilter(new RequestLoggingFilter(LogDetail.BODY, logStream))
            .addFilter(new ResponseLoggingFilter(LogDetail.STATUS, logStream))
            .addFilter(new ResponseLoggingFilter(LogDetail.BODY, logStream))
            .setConfig(RestAssured.config().objectMapperConfig(new ObjectMapperConfig(ObjectMapperType.GSON)))
            .build();

    public RestfulBookerClient(String host, String username, String password) {
        this.logger.info(String.format(
                "Creating new API client with data: host: %s, username: %s, password: %s", host, username, password));
        this.host = host;
        this.username = username;
        this.password = password;
        reqSpecs = new RequestSpecBuilder().addRequestSpecification(reqSpecs).setBaseUri(host).build();
    }

    @Step("Authorizing client")
    public void authorize() {
        Response data = login();
        AuthResponseType res = data.then().extract().body().as(AuthResponseType.class);
        reqSpecs = new RequestSpecBuilder().addRequestSpecification(reqSpecs).addCookie("token", res.token).build();
    }

    public Response login() {
        return this.login(username, password);
    }

    public Response login(String uname, String pass) {
        return this.login(new AuthType(uname, pass));
    }

    @Step("Logging in using {auth.username}/{auth.password}")
    public Response login(AuthType auth) {
        return given().spec(reqSpecs).body(auth).post("/auth");
    }

    @Step("Creating new booking")
    public Response createBooking(BookingInfoType data) {
        return given().spec(reqSpecs).body(data).post("/booking ");
    }

    @Step("Updating booking {id}")
    public Response updateBooking(int id, BookingInfoType data) {
        return given().spec(reqSpecs).body(data).put("/booking/" + id);
    }

    @Step("Getting booking {id} info")
    public Response getBooking(int id) {
        return given().spec(reqSpecs).get("/booking/" + id);
    }

}
