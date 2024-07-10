package com.taskmanagerplus.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class EnvironmentUtils {

    public static String getOS() {
        return System.getProperty("os.name");
    }

    public static String getBrowserName(WebDriver driver) {
        return ((RemoteWebDriver) driver).getCapabilities().getBrowserName();
    }

    public static String getBrowserVersion(WebDriver driver) {
        return ((RemoteWebDriver) driver).getCapabilities().getBrowserVersion();
    }
}
