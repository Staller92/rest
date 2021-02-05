package com.api.logging;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;

public class TestListener implements ITestListener {
    private ByteArrayOutputStream request = new ByteArrayOutputStream();
    private ByteArrayOutputStream response = new ByteArrayOutputStream();

    private PrintStream requestVar = new PrintStream(request, true);
    private PrintStream responseVar = new PrintStream(response, true);

    private static final Logger LOG = LogManager.getLogger(TestListener.class);

    public void onTestStart(ITestResult result) {
        LOG.info("Test method: '"+ result.getMethod().getMethodName() + "' - Started");
        RestAssured.filters(new ResponseLoggingFilter(LogDetail.ALL, responseVar),
                new RequestLoggingFilter(LogDetail.ALL, requestVar));
    }

    public void onTestSuccess(ITestResult result) {
        LOG.info("Test method: '"+ result.getMethod().getMethodName() + "' - Passed");
    }

    public void onTestFailure(ITestResult result) {
        LOG.info("Failed because of - " + result.getThrowable());
    }

    public void onTestSkipped(ITestResult result) {
        LOG.info("Skipped because of - " + result.getThrowable());
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // TODO Auto-generated method stub
    }

    public void onStart(ITestContext context) {
        LOG.info("=========== onStart : test name : '" + context.getName() + "' ===============");

    }

    public void onFinish(ITestContext context) {
        LOG.info("=========== onFinish : test name : '" + context.getName() + "' ===============");

    }
}