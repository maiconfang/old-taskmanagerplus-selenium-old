package com.taskmanagerplus.listeners;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;

import com.taskmanagerplus.reports.ExtentReportManager;



public class TestListener implements ITestListener {
    @Override
    public void onTestStart(ITestResult result) {
        ExtentReportManager.getTest().log(Status.INFO, "Test started: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentReportManager.getTest().log(Status.PASS, "Test passed: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentReportManager.getTest().log(Status.FAIL, "Test failed: " + result.getName());
    }

    @Override
    public void onStart(ITestContext context) {
        ExtentReportManager.startReporter();
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentReportManager.endReporter();
    }
}
