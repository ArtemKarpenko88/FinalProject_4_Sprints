package org.praktikumserices.pages;

import io.netty.util.internal.StringUtil;
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

    public void checkAndClickOrderButton(String button) {
        if ("Top".equals(button)) {
            clickOrderButton();
        } else if ("Bottom".equals(button)) {
            clickBottomButtonOrder();
        } else {
            throw new IllegalArgumentException("Неправильный выбор нажатия кнопки = " + button);
        }

    }

    public List<String> collectAnswersInQL() {

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

            if (StringUtil.isNullOrEmpty(text)) {
                throw new NotFoundException("Текст внутри вопроса не был найден");
            }

            resultList.add(text);

        }
        return resultList;

    }

    /*
   public List<String> collectAnswers(){
       ArrayList<String> expectedString = new ArrayList<String>();
       expectedString.add("Сутки — 400 рублей. Оплата курьеру — наличными или картой.");
       expectedString.add("Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать" +
               " несколько заказов — один за другим.");
       expectedString.add("Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды " +
               " начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30," +
               " суточная аренда закончится 9 мая в 20:30.");
       expectedString.add("Только начиная с завтрашнего дня. Но скоро станем расторопнее.");
       expectedString.add("Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.");
       expectedString.add("Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься " +
               "без передышек и во сне. Зарядка не понадобится.");
       expectedString.add("Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.");
       expectedString.add("Да, обязательно. Всем самокатов! И Москве, и Московской области.");
       return expectedString;
   }
*/

    private List<WebElement> getRow(By questionListLocator) {
        return driver.findElements(questionListLocator);
    }

}
