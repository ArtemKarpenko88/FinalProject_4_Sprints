
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

import static junit.framework.TestCase.assertTrue;

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
    public static Object[][] createClientTestData(){

        return new Object[][]{
                {"Артем", "Карпенко","Голенева 6", "89284499670"},
                {"Петр", "Иванов", "Шишкова 2", "89284499622"}
        };
    }


    @Test
    @UseDataProvider("createClientTestData")
    public void checkErrorInCaseOfNonexistentOrder(String name, String surname, String address, String phone) {

        Client client = new Client();
        client.setName(name);
        client.setSurname(surname);
        client.setAddress(address);
        client.setPhone(phone);
        client.setMetroStation("Сокольники");

        // Клик на нижнюю кнопку "Заказать"
        mainPage.clickBottomButtonOrder();

        // Первый экран заполнения данных
        whoIsTheScooterForPage.fillInTheDataOnThePage(client);
        whoIsTheScooterForPage.clickNextButton();

        // Второй экран заполнения данных
        aboutOrderPage.clickSelectDate();               // Выбрать срок из выподашки
        aboutOrderPage.comments();                      // ввести комментарий
        aboutOrderPage.selectRentalPeriod("сутки");     // выбрать срок на сутки
        aboutOrderPage.color();                         // выбрать чекбокс цвет
        aboutOrderPage.clickOrderButtonSecondPage();    // Кнопка "Заказать"

        aboutOrderPage.getPlaceAnOrderPopUp().clickButtonYes();  //Клик на "Да"

        //отображения страницы удачного заказа.
        assertTrue(aboutOrderPage.getSuccessfulOrderCreationPopUp().isSuccessfulOrderCreationVisible());
    }

}
