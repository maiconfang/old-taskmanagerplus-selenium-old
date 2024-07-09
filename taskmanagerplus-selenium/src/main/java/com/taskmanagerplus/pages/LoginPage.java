/**
 * Page object class for the login page in the Task Manager Plus application.
 * 
 * <p>This class provides methods to interact with the login page elements, 
 * including entering login credentials and clicking the login button.</p>
 * 
 * <p>Example usage:</p>
 * <pre>
 * {@code
 * LoginPage loginPage = new LoginPage(driver);
 * loginPage.enterLogin("username");
 * loginPage.enterPassword("password");
 * loginPage.clickLoginButton();
 * }
 * </pre>
 * 
 * <p><b>Note:</b> This class should be used to encapsulate the interactions 
 * with the login page elements, ensuring that tests remain clean and maintainable.</p>
 * 
 * Author: Maicon Fang
 * Date: 2024-07-09
 * Version: 1.0
 */
package com.taskmanagerplus.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    WebDriver driver;

    @FindBy(id = "login_username") // ID of the username input field
    WebElement loginInput;

    @FindBy(id = "login_password") // ID of the password input field
    WebElement passwordInput;

    @FindBy(id = "login_submit") // ID of the login button
    WebElement loginButton;

    /**
     * Constructor to initialize the WebDriver and page elements.
     * 
     * <p>This constructor sets the WebDriver instance and initializes the web 
     * elements using the PageFactory.</p>
     * 
     * @param driver the WebDriver instance to be used by this page object
     */
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Enters the specified login in the username input field.
     * 
     * @param login the login to be entered
     */
    public void enterLogin(String login) {
        loginInput.sendKeys(login);
    }

    /**
     * Enters the specified password in the password input field.
     * 
     * @param password the password to be entered
     */
    public void enterPassword(String password) {
        passwordInput.sendKeys(password);
    }

    /**
     * Clicks the login button to submit the login form.
     */
    public void clickLoginButton() {
        loginButton.click();
    }
}
