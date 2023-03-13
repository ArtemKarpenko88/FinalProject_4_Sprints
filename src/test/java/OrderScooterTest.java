import base.AbstractWebTest;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.runner.RunWith;
import org.praktikumserices.extensions.WebDriverFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.praktikumserices.objects.Client;
import org.praktikumserices.objects.Order;

import static junit.framework.TestCase.assertTrue;
import static org.praktikumserices.customerData.TheCurrentDate.theCurrentDate;

@RunWith(DataProviderRunner.class)
public class OrderScooterTest extends AbstractWebTest {

    private WebDriver driver;

    @Before
    public void setup() {
        driver = WebDriverFactory.get();
        createPages();
        checkAndAcceptCookie();
    }

    @After
    public void teardown() {
        driver.quit();
    }


    @DataProvider
    public static Object[][] createClientTestData() {

        Order order1 = new Order(new Client("Артем", "Карпенко", "Голенева 6", "89284499670", "Сокольники"),
                theCurrentDate(), "сутки", "черный жемчуг", "Комментарий 1");
        Order order2 = new Order(new Client("Виктор", "Иванов", "Шишкова 2", "89284499622", "Лубянка"),
                theCurrentDate(), "двое суток", "серая безысходность", "Комментарий 2");


        return new Object[][]{
                {order1, "Top"},
                {order2, "Bottom"}
        };

    }


    @Test
    @UseDataProvider("createClientTestData")
    public void checkErrorInCaseOfNonexistentOrder(Order order, String button ) {

        // Клик на нижнюю кнопку "Заказать"
        mainPage.checkAndClickOrderButton(button);

        // Первый экран заполнения данных
        whoIsTheScooterForPage.fillInTheDataOnThePage(order.getClient());
        whoIsTheScooterForPage.clickNextButton();


        // Второй экран заполнения данных
        aboutOrderPage.clickSelectDate(order.getDateStart());               // Выбрать срок из выподашки
        aboutOrderPage.comments(order.getComment());                      // ввести комментарий
        aboutOrderPage.selectRentalPeriod(order.getRentalPeriod());     // выбрать срок на сутки
        aboutOrderPage.color(order.getScooterColor());    // выбрать чекбокс цвет
        aboutOrderPage.clickOrderButtonSecondPage();    // Кнопка "Заказать"

        aboutOrderPage.getPlaceAnOrderPopUp().clickButtonYes();  //Клик на "Да"

        //отображения страницы удачного заказа.
        assertTrue(aboutOrderPage.getSuccessfulOrderCreationPopUp().isSuccessfulOrderCreationVisible());
    }

}
