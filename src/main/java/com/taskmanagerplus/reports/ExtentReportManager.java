package com.taskmanagerplus.reports;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {
    private static ExtentReports extent;
    private static ExtentTest test;

    public static void startReporter() {
        ExtentSparkReporter reporter = new ExtentSparkReporter("target/ExtentReports.html");
        extent = new ExtentReports();
        extent.attachReporter(reporter);
    }

    public static ExtentTest getTest() {
        return test;
    }

    public static void createTest(String testName) {
        test = extent.createTest(testName);
    }

    public static void endReporter() {
        extent.flush();
    }
}
