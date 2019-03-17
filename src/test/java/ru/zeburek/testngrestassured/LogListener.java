package ru.zeburek.testngrestassured;

import io.qameta.allure.Attachment;
import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class LogListener implements ITestListener {
    private ByteArrayOutputStream listenerStream = new ByteArrayOutputStream();

    private PrintStream listenerPrintStream = new PrintStream(listenerStream, true);


    public void onStart(ITestContext iTestContext) {
        RestAssured.filters(new ResponseLoggingFilter(LogDetail.ALL, listenerPrintStream),
                new RequestLoggingFilter(LogDetail.ALL, listenerPrintStream));
    }

    public void onTestSuccess(ITestResult iTestResult) {
        attachment(listenerStream);
    }

    public void onTestFailure(ITestResult iTestResult) {
        onTestSuccess(iTestResult);
    }

    @Attachment(value = "requests")
    public byte[] attachment(ByteArrayOutputStream stream) {
        return attach(stream);
    }

    public byte[] attach(ByteArrayOutputStream log) {
        byte[] array = log.toByteArray();
        log.reset();
        return array;
    }

    public void onTestStart(ITestResult iTestResult) {
    }

    public void onTestSkipped(ITestResult iTestResult) {

    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    public void onFinish(ITestContext iTestContext) {

    }
}
