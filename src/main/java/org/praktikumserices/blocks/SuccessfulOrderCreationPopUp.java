package org.praktikumserices.blocks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SuccessfulOrderCreationPopUp {
    private final WebDriver driver;
    private final By popUpWindowAppeared = By.xpath("//div[text()='Заказ оформлен']"); //Всплывающее окно успешного заказа.

    public SuccessfulOrderCreationPopUp(WebDriver driver) {
        this.driver = driver;

    }

    public boolean isSuccessfulOrderCreationVisible() {
        return driver.findElement(popUpWindowAppeared).isDisplayed();
    }


}
