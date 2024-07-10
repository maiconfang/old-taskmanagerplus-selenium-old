package com.taskmanagerplus.tests;

import com.taskmanagerplus.drivers.DriverFactory;
import com.taskmanagerplus.reports.ExtentReportManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import java.time.Duration;

@Listeners(com.taskmanagerplus.listeners.TestListener.class)
public class BaseTest {
    public WebDriver driver;
    protected WebDriverWait wait;

    @BeforeSuite
    public void beforeSuite() {
        // Initialize ExtentReports before any tests run
        ExtentReportManager.setUp();
    }

    @BeforeMethod
    public void setUp() {
        // Initialize the WebDriver
        driver = DriverFactory.getDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Initialize ExtentReports with the WebDriver
        ExtentReportManager.getInstance(driver);
        // Create a test entry for the current test class
        ExtentReportManager.createTest(getClass().getSimpleName());
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }

        // Flush the report after each test method
        ExtentReportManager.flush();
    }
}
