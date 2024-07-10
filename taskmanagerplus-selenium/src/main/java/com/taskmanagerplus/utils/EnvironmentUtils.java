package com.taskmanagerplus.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class EnvironmentUtils {

    /**
     * Gets the operating system name.
     *
     * @return the operating system name
     */
    public static String getOS() {
        return System.getProperty("os.name");
    }

    /**
     * Gets the browser name.
     *
     * @param driver the WebDriver instance
     * @return the browser name
     */
    public static String getBrowserName(WebDriver driver) {
        Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
        return caps.getBrowserName();
    }

    /**
     * Gets the browser version.
     *
     * @param driver the WebDriver instance
     * @return the browser version
     */
    public static String getBrowserVersion(WebDriver driver) {
        Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
        return caps.getBrowserVersion();
    }
}
