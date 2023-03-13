import base.AbstractWebTest;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.praktikumserices.extensions.WebDriverFactory;

import java.util.Collections;
import java.util.List;

@RunWith(DataProviderRunner.class)
public class QuestionsAboutImportantTest extends AbstractWebTest {
    private WebDriver driver;

    @Before
    public void setup() {
        driver = WebDriverFactory.get();
        createPages();
        checkCookie();
    }

    @DataProvider
    public static Object[][] generateData() {

        return new Object[][]{
                {"Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {"Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать" +
                        " несколько заказов — один за другим."},
                {"Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды " +
                        "начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, " +
                        "суточная аренда закончится 9 мая в 20:30."},
                {"Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {"Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {"Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься " +
                        "без передышек и во сне. Зарядка не понадобится."},
                {"Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
              {"Да, обязательно. Всем самокатов! И Москве, и Московской области."}

        };
    }

    @Test
    @UseDataProvider("generateData")
    public void checkQuestionsAboutImportantTest(String expectedData) {
        List<String> actualData = mainPage.collectAnswersInQL();
       boolean resultOfChecking =  actualData.contains(expectedData);
        System.out.println(resultOfChecking);
        Assert.assertTrue("Что то пошло не так", resultOfChecking);
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
