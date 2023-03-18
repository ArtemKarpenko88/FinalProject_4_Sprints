package org.praktikumserices.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.praktikumserices.blocks.PlaceAnOrderPopUp;
import org.praktikumserices.blocks.SuccessfulOrderCreationPopUp;
import org.praktikumserices.common.AnyComponent;
import org.praktikumserices.extensions.WebDriverHolder;

import java.util.List;

public class AboutOrderPage extends AnyComponent {

    WebDriver driver;


    private final By whenToDeliverTheOrder = By.xpath("/html/body/div/div/div[2]/div[2]/div[1]/div/div/input"); // Выподает календарь

    private final By comments = By.xpath("/html/body/div/div/div[2]/div[2]/div[4]/input"); // Поле комментарии
    private final By color = By.xpath("/html/body/div/div/div[2]/div[2]/div[3]/label[1]"); //там 2 чекбокса, выбираю черный жемчуг
    private final By clickNextButton = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM']"); // Кнопка "Заказать"

    private PlaceAnOrderPopUp placeAnOrderPopUp = new PlaceAnOrderPopUp(WebDriverHolder.getDriver());
    private SuccessfulOrderCreationPopUp successfulOrderCreationPopUp = new SuccessfulOrderCreationPopUp(WebDriverHolder.getDriver());

    public PlaceAnOrderPopUp getPlaceAnOrderPopUp() {
        return placeAnOrderPopUp;
    }

    public SuccessfulOrderCreationPopUp getSuccessfulOrderCreationPopUp() {
        return successfulOrderCreationPopUp;
    }

    public AboutOrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickSelectDate(String currentDate) {
        By locator = By.xpath("//div[@class='react-datepicker__month-container']//div[contains(@class,'selected')]");

        driver.findElement(whenToDeliverTheOrder).click();//клик на поле дата
        driver.findElement(whenToDeliverTheOrder).sendKeys(currentDate);
        driver.findElement(locator).click();
    }


    public void selectRentalPeriod(String period) {
        By rentalPeriodLocator = By.xpath("//div[@class='Dropdown-control']");
        By rentalPeriodListLocator = By.xpath("//div[@class='Dropdown-control']/following-sibling::div/div[@class='Dropdown-option']");

        driver.findElement(rentalPeriodLocator).click();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(rentalPeriodListLocator));
        List<WebElement> rentalList = driver.findElements(rentalPeriodListLocator);

        boolean flag = false;
        for (WebElement element : rentalList) {
            if (period.equals(element.getText())) {
                flag = true;
                element.click();
                break;
            }
        }
        if (!flag) {
            throw new IllegalArgumentException("Нет в списке текущего срока аренды = " + period);
        }
    }


    public void color(String scooterColor) {
        driver.findElement(color).click();
    }

    public void comments(String comment) {
        driver.findElement(comments).sendKeys(comment);
    }

    public void clickOrderButtonSecondPage() {
        driver.findElement(clickNextButton).click();
    }


}
