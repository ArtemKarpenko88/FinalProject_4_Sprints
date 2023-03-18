package org.praktikumserices.extensions;

import org.openqa.selenium.chrome.ChromeOptions;
import org.praktikumserices.config.AppConfig;
import org.praktikumserices.config.WebDriverConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class WebDriverFactory {
    public static WebDriver get() {
        String browserName = System.getenv().get("browser");

        WebDriver driver;
        switch (browserName) {
            case "chrome":
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");
                driver = new ChromeDriver(options);
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            default:
                throw new RuntimeException(("Browser" + browserName + " not exist"));
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(WebDriverConfig.WAIT_SECONDS_TIMEOUT));
        driver.navigate().to(AppConfig.BASE_URL);
        driver.manage().window().maximize();
        WebDriverHolder.setDriver(driver);
        return driver;
    }
}
