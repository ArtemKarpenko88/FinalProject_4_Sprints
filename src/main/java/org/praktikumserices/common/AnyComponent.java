package org.praktikumserices.common;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.praktikumserices.extensions.WebDriverHolder;

import java.time.Duration;


public class AnyComponent {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public AnyComponent() {
        this.driver = WebDriverHolder.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    public void scrollIntoView(WebElement element) {
        ((Locatable) element).getCoordinates().inViewPort();
        waitElementsReload(1000);
        JavascriptExecutor js = getJavascriptExecutor();
        js.executeScript("return window.scrollBy({top:200, left:0, behavior:'smooth'})");
    }

    private JavascriptExecutor getJavascriptExecutor() {
        return (JavascriptExecutor) driver;
    }

    private void waitElementsReload(int milliseconds) {

        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
