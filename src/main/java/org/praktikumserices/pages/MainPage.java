package org.praktikumserices.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.praktikumserices.common.AnyComponent;


public class MainPage extends AnyComponent {
    WebDriver driver;

    private final By buttonOrder = By.xpath("/html/body/div/div/div/div[1]/div[2]/button[1]");
    private final By bottomButtonOrder = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

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

    public void checkAndClickOrderButton(String button) {
        if ("Top".equals(button)) {
            clickOrderButton();
        } else if ("Bottom".equals(button)) {
            clickBottomButtonOrder();
        } else {
            throw new IllegalArgumentException("Неправильный выбор нажатия кнопки = " + button);
        }

    }


    public String collectAnswersInQLTwo(String questionText) {

        By accordionItemLocator = By.xpath("//div[@data-accordion-component='AccordionItem' and .//div[text()='" + questionText + "']]");
        WebElement accordionItem = getRow(accordionItemLocator);
        scrollIntoView(accordionItem);
        accordionItem.click();

        By answerLocator = By.xpath(".//p");
        return accordionItem.findElement(answerLocator).getText();
    }

    private WebElement getRow(By questionListLocator) {
        return driver.findElement(questionListLocator);
    }

}

