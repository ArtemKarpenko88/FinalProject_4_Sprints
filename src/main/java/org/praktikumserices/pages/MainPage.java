package org.praktikumserices.pages;

import io.netty.util.internal.StringUtil;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.praktikumserices.common.AnyComponent;

import java.util.ArrayList;
import java.util.List;


public class MainPage extends AnyComponent {
    WebDriver driver;

    private final By buttonOrder = By.xpath("/html/body/div/div/div/div[1]/div[2]/button[1]");
    private final By bottomButtonOrder = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    By questionListLocator = By.xpath("//div[@class='accordion']//div[@class='accordion__item']");
    By questionButtonLocator = By.xpath(".//div[@role='button']");
    By answerTextLocator = By.xpath((".//div[@class='accordion__panel']/p"));


    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOrderButton() {
        driver.findElement(buttonOrder).click();
    }

    public void clickBottomButtonOrder() {
        WebElement bottom = driver.findElement(bottomButtonOrder);
        scrollIntoView(bottom);
        bottom.click();
    }


    public List<String> collectAnswersInQL(){

        List<String> resultList = new ArrayList<>();
        List<WebElement> questionList = getRow(questionListLocator);
        for (int i = 0; i < questionList.size(); i++) {
            WebElement element = getRow(questionListLocator).get(i).findElement(questionButtonLocator);
            scrollIntoView(element); //Из метода не работает
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();

            WebElement textElement = getRow(questionListLocator).get(i).findElement(answerTextLocator);
            scrollIntoView(textElement); //Из метода не работает
            String text = textElement.getText();
            System.out.println(i + ": " + text);

            if (StringUtil.isNullOrEmpty(text)){
                throw new NotFoundException("Текст внутри вопроса не был найден");
            }

            resultList.add(text);


        }
        return resultList;

    }


    private List<WebElement> getRow(By questionListLocator) {
        return driver.findElements(questionListLocator);
    }

}
