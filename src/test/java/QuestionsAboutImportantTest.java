import base.AbstractWebTest;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.praktikumserices.extensions.WebDriverFactory;

import java.util.Arrays;
import java.util.List;


public class QuestionsAboutImportantTest extends AbstractWebTest {
    private WebDriver driver;
    private WebDriverWait wait;


    @Before
    public void setup() {
        driver = WebDriverFactory.get();
        createPages();
        checkCookie();
    }

    @Test
    public void checkQuestionsAboutImportantTest() {
        List<String> expectedData = generateData();
        List<String> actualData = mainPage.collectAnswersInQL();

        //Assert.assertFalse(StringUtil.isNullOrEmpty(text));
        Assert.assertEquals(expectedData, actualData);

    }

    private List<String> generateData() {
        return Arrays.asList(
                "Сутки — 400 рублей. Оплата курьеру — наличными или картой.",
                "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать" +
                        " несколько заказов — один за другим.",
                "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды " +
                        "начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, " +
                        "суточная аренда закончится 9 мая в 20:30.",
                "Только начиная с завтрашнего дня. Но скоро станем расторопнее.",
                "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.",
                "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься " +
                        "без передышек и во сне. Зарядка не понадобится.",
                "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.",
                "Да, обязательно. Всем самокатов! И Москве, и Московской области.");
    }


    private void checkCookie() {
        By buttonLocator = By.xpath("//button[@id='rcc-confirm-button']");

        if (isElementPresent(buttonLocator)) {
            driver.findElement(buttonLocator).click();
        }

    }

    private boolean isElementPresent(By locator) {
        return !driver.findElements(locator).isEmpty();
    }


    @After
    public void teardown() {
        driver.quit();
    }


}
