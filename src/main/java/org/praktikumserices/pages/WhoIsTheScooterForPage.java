
package org.praktikumserices.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.praktikumserices.customerData.CustomerData;
import org.praktikumserices.objects.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WhoIsTheScooterForPage {
    WebDriver driver;

    private final By fieldName = By.xpath("/html/body/div/div/div[2]/div[2]/div[1]/input");  //Name
    private final By failiaField = By.xpath("/html/body/div/div/div[2]/div[2]/div[2]/input"); // Surname
    private final By addressField = By.xpath("/html/body/div/div/div[2]/div[2]/div[3]/input");// Address


    private final By phoneBox = By.xpath("/html/body/div/div/div[2]/div[2]/div[5]/input"); // phone

    private final By nextButton = By.xpath("/html/body/div/div/div[2]/div[3]/button"); // button "next"


    //посмотри на твой икспас и на мой и попробуй переписать по аналогии с моим
   // private final By fieldStation = By.xpath("/html/body//div[2]/div[2]/div[4]/div/div/input"); //metro station
    private final By fieldStationLocator = By.xpath("//div[@class='select-search__value']");


    private final By selectMetroLocator = By.xpath("//div[@class='select-search__value']/following-sibling::div[@class='select-search__select']//li[@class='select-search__row']");


    public WhoIsTheScooterForPage(WebDriver driver) {
        this.driver = driver;

    }

    public void fillInTheDataOnThePage(Client client){
        driver.findElement(fieldName).sendKeys(client.getName());
        driver.findElement(failiaField).sendKeys(client.getSurname());
        driver.findElement(addressField).sendKeys(client.getAddress());
        selectStation(client.getMetroStation());

        driver.findElement(phoneBox).sendKeys(client.getPhone());
    }


//    public void fillInTheDataOnThePage() {
//        driver.findElement(fieldName).sendKeys(CustomerData.NAME);
//        driver.findElement(failiaField).sendKeys(CustomerData.SUENAME);
//        driver.findElement(addressField).sendKeys(CustomerData.ADDRESS);
//
//        selectStation("Сокольники");
//
//        driver.findElement(phoneBox).sendKeys(CustomerData.TELEPHONE);
//    }

    public void selectStation(String metroStation)  {
        driver.findElement(fieldStationLocator).click();
        List<WebElement> list = driver.findElements(selectMetroLocator);
        By textMetroLocator = By.xpath(".//div[@class='Order_Text__2broi']");

        boolean flag = false;
        for (WebElement element : list) {
            if (metroStation.equals(element.findElement(textMetroLocator).getText())) {
                flag = true;
                element.click();
                break;
            }
        }
        if(!flag){
            throw new IllegalArgumentException("Нет в списке текущего метро = " + metroStation);
        }
    }

    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }
}
