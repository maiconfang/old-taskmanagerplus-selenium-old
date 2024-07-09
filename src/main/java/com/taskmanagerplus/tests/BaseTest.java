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

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.getDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Define um timeout implícito
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // WebDriverWait para ser usado nos testes
        ExtentReportManager.createTest(getClass().getSimpleName());
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}