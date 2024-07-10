package com.taskmanagerplus.tests;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.taskmanagerplus.pages.InitialPage;
import com.taskmanagerplus.pages.LoginPage;
import com.taskmanagerplus.pages.DashboardPage;
import com.taskmanagerplus.reports.ExtentReportManager;
import com.taskmanagerplus.utils.ExcelUtils;
import com.aventstack.extentreports.Status;
import com.taskmanagerplus.config.ConfigReader;

import java.time.Duration;

public class LoginTest extends BaseTest {

    private ExcelUtils excelUtils;

    @BeforeClass
    public void setUpClass() {
        // Initialize ExcelUtils with the path to the LoginCredentials.xlsx file
        excelUtils = new ExcelUtils("testdata/LoginCredentials.xlsx");
        System.out.println("ExcelUtils initialized with file: testdata/LoginCredentials.xlsx");
        excelUtils.printFilePath();
    }

    @Test
    public void testLogin() {
        ExtentReportManager.getTest().log(Status.INFO, "Navigating to the initial page");
        driver.get(ConfigReader.getProperty("url"));

        InitialPage initialPage = new InitialPage(driver);
        ExtentReportManager.getTest().log(Status.INFO, "Initial page loaded successfully");

        ExtentReportManager.getTest().log(Status.INFO, "Clicking the login link");
        initialPage.clickLogin();

        LoginPage loginPage = new LoginPage(driver);
        ExtentReportManager.getTest().log(Status.INFO, "Login page loaded successfully");

        // Read credentials from Excel file
        String username = excelUtils.getCellDataByColumnName("LoginCredentials", 1, "Username");
        String password = excelUtils.getCellDataByColumnName("LoginCredentials", 1, "Password");

        ExtentReportManager.getTest().log(Status.INFO, "Entering login credentials");
        loginPage.enterLogin(username);
        loginPage.enterPassword(password);

        ExtentReportManager.getTest().log(Status.INFO, "Clicking the login button");
        loginPage.clickLoginButton();

        // Explicit wait to ensure the URL has changed to the expected one
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        ExtentReportManager.getTest().log(Status.INFO, "Waiting for URL to change to the application page");
        wait.until(ExpectedConditions.urlToBe(ConfigReader.getProperty("urlApplication")));

        // Getting the expected URL from the config file
        String expectedUrl = ConfigReader.getProperty("urlApplication");
        String actualUrl = driver.getCurrentUrl();

        ExtentReportManager.getTest().log(Status.INFO, "Verifying the login was successful");
        Assert.assertEquals(actualUrl, expectedUrl, "The login was not successful.");

        // Additional check: Verify if the specific elements unique to the dashboard are present
        DashboardPage dashboardPage = new DashboardPage(driver);
        ExtentReportManager.getTest().log(Status.INFO, "Verifying presence of user link on the dashboard");
        Assert.assertTrue(dashboardPage.isUserLinkPresent(), "The user link on the dashboard was not found.");

        ExtentReportManager.getTest().log(Status.INFO, "Verifying presence of logout button on the dashboard");
        Assert.assertTrue(dashboardPage.isLogoutButtonPresent(), "The logout button on the dashboard was not found.");
    }
}
