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

/**
 * Test class for the login functionality in the Task Manager Plus application.
 * 
 * <p>This class contains test methods to verify the login functionality 
 * using valid and invalid credentials, as well as edge cases.</p>
 * 
 * <p>Example usage:</p>
 * <pre>
 * {@code
 * LoginTest loginTest = new LoginTest();
 * loginTest.setUpClass();
 * loginTest.testLogin();
 * loginTest.testLoginWithInvalidCredentials();
 * loginTest.testLoginWithEmptyCredentials();
 * loginTest.testLoginWithShortCredentials();
 * }
 * </pre>
 * 
 * <p><b>Note:</b> This class is designed to run as a part of the test suite, 
 * ensuring that the login functionality works as expected.</p>
 * 
 * Author: Maicon Fang
 * Date: 2024-07-11
 * Version: 1.0
 */

public class LoginTest extends BaseTest {

    private ExcelUtils excelUtils;

    @BeforeClass
    public void setUpClass() {
        // Initialize ExcelUtils with the path to the LoginCredentials.xlsx file
        excelUtils = new ExcelUtils("testdata/LoginCredentials.xlsx");
        System.out.println("ExcelUtils initialized with file: testdata/LoginCredentials.xlsx");
    }
    
    
    private LoginPage navigateToLoginPage() {
        ExtentReportManager.getTest().log(Status.INFO, "Navigating to the initial page");
        driver.get(ConfigReader.getProperty("url"));

        InitialPage initialPage = new InitialPage(driver);
        ExtentReportManager.getTest().log(Status.INFO, "Initial page loaded successfully");

        ExtentReportManager.getTest().log(Status.INFO, "Clicking the login link");
        initialPage.clickLogin();

        LoginPage loginPage = new LoginPage(driver);
        ExtentReportManager.getTest().log(Status.INFO, "Login page loaded successfully");
        
        return loginPage;
    }
    

    @Test
    public void testLogin() {

    	LoginPage loginPage = navigateToLoginPage();

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
    
    
    @Test
    public void testLoginWithInvalidCredentials() {
    	LoginPage loginPage = navigateToLoginPage();

        // Enter invalid credentials
        ExtentReportManager.getTest().log(Status.INFO, "Entering invalid login credentials");
        loginPage.enterLogin("invalidUser");
        loginPage.enterPassword("invalidPass");

        ExtentReportManager.getTest().log(Status.INFO, "Clicking the login button");
        loginPage.clickLoginButton();

        // Explicit wait to ensure the error message is displayed
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        ExtentReportManager.getTest().log(Status.INFO, "Waiting for error message to be visible");
        wait.until(ExpectedConditions.visibilityOf(loginPage.getErrorMessage()));

        // Verify the error message is displayed
        ExtentReportManager.getTest().log(Status.INFO, "Verifying error message is displayed");
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "The error message was not displayed for invalid credentials.");
    }
    
    @Test
    public void testLoginWithEmptyCredentials() {
        LoginPage loginPage = navigateToLoginPage();

        // Generate long strings for login and password
        String longLogin = "a".repeat(55);
        String longPassword = "b".repeat(33);

        // Enter long strings in login and password fields
        ExtentReportManager.getTest().log(Status.INFO, "Entering long login and password strings");
        loginPage.enterLogin(longLogin);
        loginPage.enterPassword(longPassword);

        // Verify the login button is disabled
        ExtentReportManager.getTest().log(Status.INFO, "Verifying the login button is disabled");
        Assert.assertFalse(loginPage.isLoginButtonEnabled(), "The login button should be disabled for invalid credentials.");


        // Explicit wait to ensure the error messages are displayed
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        ExtentReportManager.getTest().log(Status.INFO, "Waiting for error messages to be visible");
        wait.until(ExpectedConditions.visibilityOf(loginPage.getLoginErrorMessage()));
        wait.until(ExpectedConditions.visibilityOf(loginPage.getPasswordErrorMessage()));

        // Verify the error messages are displayed
        ExtentReportManager.getTest().log(Status.INFO, "Verifying login error message is displayed");
        Assert.assertTrue(loginPage.isLoginErrorMessageDisplayed(), "The login error message was not displayed.");

        ExtentReportManager.getTest().log(Status.INFO, "Verifying password error message is displayed");
        Assert.assertTrue(loginPage.isPasswordErrorMessageDisplayed(), "The password error message was not displayed.");
    }
    
    
    @Test
    public void testLoginWithShortCredentials() {
        LoginPage loginPage = navigateToLoginPage();

        // Enter short login and password
        String shortLogin = "1";
        String shortPassword = ".";

        ExtentReportManager.getTest().log(Status.INFO, "Entering short login and password");
        loginPage.enterLogin(shortLogin);
        loginPage.enterPassword(shortPassword);

        // Verify the login button is disabled
        ExtentReportManager.getTest().log(Status.INFO, "Verifying the login button is disabled");
        Assert.assertFalse(loginPage.isLoginButtonEnabled(), "The login button should be disabled for short credentials.");

        // Explicit wait to ensure the error messages are displayed
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        ExtentReportManager.getTest().log(Status.INFO, "Waiting for error messages to be visible");
        wait.until(ExpectedConditions.visibilityOf(loginPage.getLoginErrorMessage()));
        wait.until(ExpectedConditions.visibilityOf(loginPage.getPasswordErrorMessage()));

        // Verify the error messages are displayed
        ExtentReportManager.getTest().log(Status.INFO, "Verifying login error message is displayed");
        Assert.assertTrue(loginPage.isLoginErrorMessageDisplayed(), "The login error message was not displayed.");

        ExtentReportManager.getTest().log(Status.INFO, "Verifying password error message is displayed");
        Assert.assertTrue(loginPage.isPasswordErrorMessageDisplayed(), "The password error message was not displayed.");
    }
    
}
