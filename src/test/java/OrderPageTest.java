import jdk.jfr.Description;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.Theory;
import qa.pom.OrderPage;

import static org.junit.Assert.*;

public class OrderPageTest extends BaseTest {

    OrderPage orderPage = null;

    @Before
    public void BeforeOrderTest() {
        orderPage = new OrderPage(driver);
        orderPage.navigateTo();
    }

    @Test
    @Description("Проверка отображения сообщений об ошибке в полях оформления заказа первой страницы")
    public void FieldErrorPageOneCheck() {
        orderPage.ButtonNext.click();

        assertTrue(orderPage.InputNameError.isDisplayed());
        assertEquals("Введите корректное имя", orderPage.InputNameError.getText());

        assertTrue(orderPage.InputLastNameError.isDisplayed());
        assertEquals("Введите корректную фамилию", orderPage.InputLastNameError.getText());

        assertTrue(orderPage.InputStationError.isDisplayed());
        assertEquals("Выберите станцию", orderPage.InputStationError.getText());

        assertTrue(orderPage.InputPhoneError.isDisplayed());
        assertEquals("Введите корректный номер", orderPage.InputPhoneError.getText());

        // На сайте баг
        assertTrue(orderPage.InputAddressError.isDisplayed());
        assertEquals("Введите корректный адрес", orderPage.InputAddressError.getText());
    }

    @Test
    @Description("Проверка отображения сообщений об ошибке в полях оформления заказа второй страницы")
    public void FieldErrorPageTwoCheck() {

        orderPage.InputName.sendKeys("Инна");
        orderPage.InputLastName.sendKeys("Малышева");
        orderPage.InputAddress.sendKeys("Проспект Науки 1");
        orderPage.SelectMetroStation("Алексеевская");
        orderPage.InputPhone.sendKeys("11111111111");

        orderPage.ButtonNext.click();
        orderPage.ButtonPlaceOrder.click();

        // На сайте отсутствут ошибка заполнения у обязательного поля даты доставки самоката
        //assertTrue(orderPage.InputDateError.isDisplayed());

        // На сайте отсутствут ошибка заполнения у обязательного поля срока аренды самоката
        //assertTrue(orderPage.DropdownRentalPeriodError.isDisplayed());
        fail();
    }
}
