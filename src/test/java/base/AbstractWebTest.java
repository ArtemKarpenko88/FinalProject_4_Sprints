package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.praktikumserices.blocks.PlaceAnOrderPopUp;
import org.praktikumserices.pages.WhoIsTheScooterForPage;
import org.praktikumserices.pages.MainPage;
import org.praktikumserices.pages.AboutOrderPage;
import org.praktikumserices.extensions.WebdriverHolder;

public abstract class AbstractWebTest {
    protected  static PlaceAnOrderPopUp placeAnOrderPopUp;

    protected static WhoIsTheScooterForPage whoIsTheScooterForPage;
    protected static MainPage mainPage;
    protected static AboutOrderPage aboutOrderPage;

    private WebDriver driver;

    public void createPages() {
        driver = WebdriverHolder.getDriver();
        whoIsTheScooterForPage = new WhoIsTheScooterForPage(driver);
        mainPage = new MainPage(driver);
        aboutOrderPage = new AboutOrderPage(driver);
        placeAnOrderPopUp=new PlaceAnOrderPopUp(driver);
    }


    public void checkAndAcceptCookie() {
        By buttonLocator = By.xpath("//button[@id='rcc-confirm-button']");

        if (isElementPresent(buttonLocator)) {
            driver.findElement(buttonLocator).click();
        }

    }

    private boolean isElementPresent(By locator) {
        return !driver.findElements(locator).isEmpty();
    }


}
