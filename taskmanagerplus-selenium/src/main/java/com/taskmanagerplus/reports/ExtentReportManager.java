/**
 * Manager class for ExtentReports in the Task Manager Plus application.
 * 
 * <p>This class provides methods to create and manage ExtentReports, 
 * ensuring that reports are properly configured and generated. It uses 
 * a singleton pattern to ensure that only one instance of ExtentReports 
 * is created and used throughout the application.</p>
 * 
 * <p>Example usage:</p>
 * <pre>
 * {@code
 * ExtentReportManager.createTest("Test Name");
 * ExtentReportManager.getTest().log(Status.INFO, "Test started");
 * ExtentReportManager.flush();
 * }
 * </pre>
 * 
 * <p><b>Note:</b> This class should be used to manage all ExtentReports 
 * interactions, ensuring consistency and proper resource management.</p>
 * 
 * Author: Maicon Fang
 * Date: 2024-07-09
 * Version: 1.0
 */
package com.taskmanagerplus.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {
    private static ExtentReports extent;
    private static ExtentTest test;
    private static ExtentSparkReporter htmlReporter;
    private static String reportFileName = "ExtentReport.html";
    private static String reportFilePath = System.getProperty("user.dir") + "/test-output/" + reportFileName;

    /**
     * Gets the singleton instance of ExtentReports.
     * 
     * @return the singleton instance of {@link ExtentReports}
     */
    public static synchronized ExtentReports getInstance() {
        if (extent == null) {
            createInstance();
        }
        return extent;
    }

    /**
     * Creates a new instance of ExtentReports and configures the reporter.
     */
    private static void createInstance() {
        htmlReporter = new ExtentSparkReporter(reportFilePath);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setDocumentTitle("Automation Test Report");
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName("Automation Test Results");

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Browser", "Chrome");
    }

    /**
     * Creates a new test entry in the ExtentReports instance.
     * 
     * @param testName the name of the test
     * @return the created {@link ExtentTest} instance
     */
    public static synchronized ExtentTest createTest(String testName) {
        test = getInstance().createTest(testName);
        return test;
    }

    /**
     * Gets the current ExtentTest instance.
     * 
     * @return the current {@link ExtentTest} instance
     * @throws IllegalStateException if no test has been created
     */
    public static synchronized ExtentTest getTest() {
        if (test == null) {
            throw new IllegalStateException("ExtentTest is not initialized. Call createTest() before getTest().");
        }
        return test;
    }

    /**
     * Flushes the ExtentReports instance, writing all logs and information to the report.
     */
    public static synchronized void flush() {
        getInstance().flush();
    }
}
