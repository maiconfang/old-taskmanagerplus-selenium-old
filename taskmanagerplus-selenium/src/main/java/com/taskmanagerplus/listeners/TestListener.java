/**
 * Listener class for TestNG tests that integrates with ExtentReports.
 * 
 * <p>This class implements the {@link org.testng.ITestListener} interface to 
 * provide custom behavior for test start, success, failure, and skip events.
 * It uses {@link com.taskmanagerplus.reports.ExtentReportManager} to log these 
 * events to ExtentReports.</p>
 * 
 * <p>Example usage:</p>
 * <pre>
 * {@code
 * @Listeners(com.taskmanagerplus.listeners.TestListener.class)
 * public class MyTest {
 *     // test methods
 * }
 * }
 * </pre>
 * 
 * <p><b>Note:</b> This listener needs to be registered with TestNG either 
 * through the {@code @Listeners} annotation or the testng.xml configuration file.</p>
 * 
 * Author: Maicon Fang
 * Date: 2024-07-09
 * Version: 1.0
 */
package com.taskmanagerplus.listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;
import com.taskmanagerplus.reports.ExtentReportManager;

public class TestListener implements ITestListener {
    
    /**
     * Invoked each time a test starts.
     * 
     * <p>This method creates a new test entry in ExtentReports and logs the start of the test.</p>
     * 
     * @param result the result of the test that is starting
     */
    @Override
    public void onTestStart(ITestResult result) {
        ExtentReportManager.createTest(result.getMethod().getMethodName());
        ExtentReportManager.getTest().log(Status.INFO, "Test started: " + result.getMethod().getMethodName());
    }

    /**
     * Invoked each time a test succeeds.
     * 
     * <p>This method logs the success of the test in ExtentReports.</p>
     * 
     * @param result the result of the test that succeeded
     */
    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentReportManager.getTest().log(Status.PASS, "Test passed: " + result.getMethod().getMethodName());
    }

    /**
     * Invoked each time a test fails.
     * 
     * <p>This method logs the failure of the test in ExtentReports, including the throwable that caused the failure.</p>
     * 
     * @param result the result of the test that failed
     */
    @Override
    public void onTestFailure(ITestResult result) {
        ExtentReportManager.getTest().log(Status.FAIL, "Test failed: " + result.getMethod().getMethodName());
        ExtentReportManager.getTest().log(Status.FAIL, result.getThrowable());
    }

    /**
     * Invoked each time a test is skipped.
     * 
     * <p>This method logs the skipping of the test in ExtentReports.</p>
     * 
     * @param result the result of the test that was skipped
     */
    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentReportManager.getTest().log(Status.SKIP, "Test skipped: " + result.getMethod().getMethodName());
    }

    /**
     * Invoked each time a test fails but is within the success percentage specified.
     * 
     * <p>This method is not commonly used, so it is left empty.</p>
     * 
     * @param result the result of the test that failed but is within the success percentage
     */
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // This method is not commonly used.
    }
}
