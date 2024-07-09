package com.taskmanagerplus.tests;

//import org.testng.annotations.Test;

import com.taskmanagerplus.pages.LoginPage;
// import org.testng.annotations.Test;

import org.junit.jupiter.api.Test;

public class LoginTest extends BaseTest {

    public static void main(String[] args) {
        System.out.println("Hello World");
    }

    @Test
    public void testLogin() {
        driver.get("http://localhost:4200/#/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterLogin("luna.moon@maif.com");
        loginPage.enterPassword("123");
        loginPage.clickLoginButton();
    }
}

