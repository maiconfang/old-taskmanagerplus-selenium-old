/**
 * Tests for the login functionality of the Task Manager Plus application.
 * This test verifies the login process, ensuring that valid credentials 
 * redirect the user to the dashboard and the necessary elements are present.
 * 
 * <p> This test includes:
 * <ul>
 *   <li>Navigating to the initial page</li>
 *   <li>Clicking the login link</li>
 *   <li>Entering login credentials</li>
 *   <li>Clicking the login button</li>
 *   <li>Verifying the URL change to the dashboard</li>
 *   <li>Checking the presence of key elements on the dashboard</li>
 * </ul>
 * </p>
 * 
 * <p><b>Note:</b> This test assumes that the application is already deployed 
 * and accessible at the URL specified in the configuration properties.</p>
 * 
 * Author: Maicon Fang
 * Date: 2024-07-09
 * Version: 1.0
 * 
 */
package com.taskmanagerplus.tests;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.taskmanagerplus.pages.InitialPage;
import com.taskmanagerplus.pages.LoginPage;
import com.taskmanagerplus.pages.DashboardPage;
import com.taskmanagerplus.reports.ExtentReportManager;
import com.aventstack.extentreports.Status;
import com.taskmanagerplus.config.ConfigReader;

import java.time.Duration;

public class LoginTest extends BaseTest {

    /**
     * Verifies the login functionality.
     * <p>This test navigates to the initial page, clicks the login link, 
     * enters valid credentials, and verifies that the user is redirected 
     * to the dashboard where the key elements are present.</p>
     */
    @Test
    public void testLogin() {
        ExtentReportManager.getTest().log(Status.INFO, "Navigating to the initial page");
        driver.get(ConfigReader.getProperty("url"));

        InitialPage initialPage = new InitialPage(driver);
        ExtentReportManager.getTest().log(Status.INFO, "Clicking the login link");
        initialPage.clickLogin();

        LoginPage loginPage = new LoginPage(driver);
        ExtentReportManager.getTest().log(Status.INFO, "Entering login credentials");
        loginPage.enterLogin("luna.moon@maif.com");
        loginPage.enterPassword("123");

        ExtentReportManager.getTest().log(Status.INFO, "Clicking the login button");
        loginPage.clickLoginButton();

        // Explicit wait to ensure the URL has changed to the expected one
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(ConfigReader.getProperty("urlApplication")));

        // Getting the expected URL from the config file
        String expectedUrl = ConfigReader.getProperty("urlApplication");
        String actualUrl = driver.getCurrentUrl();

        ExtentReportManager.getTest().log(Status.INFO, "Verifying the login was successful");
        Assert.assertEquals(actualUrl, expectedUrl, "The login was not successful.");

        // Additional check: Verify if the specific elements unique to the dashboard are present
        DashboardPage dashboardPage = new DashboardPage(driver);
        Assert.assertTrue(dashboardPage.isUserLinkPresent(), "The user link on the dashboard was not found.");
        Assert.assertTrue(dashboardPage.isLogoutButtonPresent(), "The logout button on the dashboard was not found.");
    }
}
