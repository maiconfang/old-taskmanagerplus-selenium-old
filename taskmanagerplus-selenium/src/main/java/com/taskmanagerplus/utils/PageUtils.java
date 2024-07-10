package com.taskmanagerplus.utils;

import org.openqa.selenium.WebDriver;

public class PageUtils {
    public static String getPageSource(WebDriver driver) {
        return driver.getPageSource();
    }
}

