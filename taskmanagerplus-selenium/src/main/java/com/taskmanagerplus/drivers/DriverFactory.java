package com.taskmanagerplus.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.taskmanagerplus.config.ConfigReader;

public class DriverFactory {
    public static WebDriver getDriver() {
        System.setProperty("webdriver.chrome.driver", ConfigReader.getProperty("chromeDriverPath"));
        return new ChromeDriver();
    }
}