package com.taskmanagerplus.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

    @FindBy(id = "login_login")
    private WebElement loginInput;

    @FindBy(id = "login_password")
    private WebElement passwordInput;

    @FindBy(id = "login_btn_enter")
    private WebElement loginButton;

    @FindBy(id = "flexCheckDefault")
    private WebElement rememberMeCheckbox;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // Métodos para interagir com os elementos, se necessário
    public void enterLogin(String login) {
        loginInput.sendKeys(login);
    }

    public void enterPassword(String password) {
        passwordInput.sendKeys(password);
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public void clickRememberMeCheckbox() {
        rememberMeCheckbox.click();
    }
}