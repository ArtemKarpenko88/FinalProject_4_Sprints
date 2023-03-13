package org.praktikumserices.blocks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PlaceAnOrderPopUp {
    WebDriver driver;

    public PlaceAnOrderPopUp(WebDriver driver) {
        this.driver = driver;
    }

    public final By buttonYes = By.xpath("//button[text()='Да']");

    public void clickButtonYes() {
        driver.findElement(buttonYes).click();
    }

}
