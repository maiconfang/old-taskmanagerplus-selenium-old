/**
 * Base class for all test classes in the Task Manager Plus application.
 * 
 * <p>This class sets up the WebDriver and WebDriverWait before each test method
 * and ensures that ExtentReports is initialized. It also handles cleaning up 
 * the WebDriver and flushing the report after each test method.</p>
 * 
 * <p>Example usage:</p>
 * <pre>
 * {@code
 * public class LoginTest extends BaseTest {
 *     @Test
 *     public void testLogin() {
 *         // Test code here
 *     }
 * }
 * }
 * </pre>
 * 
 * <p><b>Note:</b> All test classes should extend this class to ensure proper 
 * setup and teardown of the WebDriver and ExtentReports.</p>
 * 
 * Author: Maicon Fang
 * Date: 2024-07-09
 * Version: 1.0
 */
package com.taskmanagerplus.tests;

import com.taskmanagerplus.drivers.DriverFactory;
import com.taskmanagerplus.reports.ExtentReportManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import java.time.Duration;

@Listeners(com.taskmanagerplus.listeners.TestListener.class)
public class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;

    /**
     * Sets up the WebDriver and WebDriverWait before each test method.
     * 
     * <p>This method initializes the WebDriver using {@link DriverFactory}, maximizes the browser window,
     * sets an implicit wait, and initializes the WebDriverWait. It also initializes ExtentReports
     * and creates a test entry for the current test class.</p>
     */
    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.getDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Initialize ExtentReports before creating the test
        ExtentReportManager.getInstance();
        ExtentReportManager.createTest(getClass().getSimpleName());
    }

    /**
     * Cleans up the WebDriver and flushes the report after each test method.
     * 
     * <p>This method quits the WebDriver if it is not null and flushes the ExtentReports instance to 
     * ensure that all logs and information are written to the report.</p>
     */
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }

        // Flush the report after each test method
        ExtentReportManager.flush();
    }
}
