package org.praktikumserices.extensions;

import org.openqa.selenium.WebDriver;

public class WebdriverHolder {

    private static WebDriver driver;

    public static WebDriver getDriver(){
        return driver;
    }

    public static void setDriver(WebDriver driver){
        WebdriverHolder.driver = driver;
    }

}
