import jdk.jfr.Description;
import org.junit.Before;
import org.junit.Test;
import qa.pom.OrderPage;

import static org.junit.Assert.*;

public class OrderPageTest extends BaseTest {

    OrderPage orderPage = null;

    @Before
    public void beforeOrderTest() {
        orderPage = new OrderPage(driver);
        orderPage.navigateTo();
    }

    @Test
    @Description("Проверка отображения сообщений об ошибке в поле Имя")
    public void fieldErrorPageOneNameCheck() {
        orderPage.inputLastName.sendKeys("Малышева");
        orderPage.inputAddress.sendKeys("Проспект Науки 1");
        orderPage.selectMetroStation("Алексеевская");
        orderPage.inputPhone.sendKeys("11111111111");

        orderPage.buttonNext.click();

        assertTrue(orderPage.inputNameError.isDisplayed());
        assertEquals("Введите корректное имя", orderPage.inputNameError.getText());
    }

    @Test
    @Description("Проверка отображения сообщений об ошибке в поле Фамилия")
    public void fieldErrorPageOneLastNameCheck() {
        orderPage.inputName.sendKeys("Инна");
        orderPage.inputAddress.sendKeys("Проспект Науки 1");
        orderPage.selectMetroStation("Алексеевская");
        orderPage.inputPhone.sendKeys("11111111111");

        orderPage.buttonNext.click();

        assertTrue(orderPage.inputLastNameError.isDisplayed());
        assertEquals("Введите корректную фамилию", orderPage.inputLastNameError.getText());
    }

    @Test
    @Description("Проверка отображения сообщений об ошибке в поле Станция метро")
    public void fieldErrorPageOneStationCheck() {
        orderPage.inputName.sendKeys("Инна");
        orderPage.inputLastName.sendKeys("Малышева");
        orderPage.inputAddress.sendKeys("Проспект Науки 1");
        orderPage.inputPhone.sendKeys("11111111111");

        orderPage.buttonNext.click();

        assertTrue(orderPage.inputStationError.isDisplayed());
        assertEquals("Выберите станцию", orderPage.inputStationError.getText());
    }

    @Test
    @Description("Проверка отображения сообщений об ошибке в поле Телефон")
    public void fieldErrorPageOnePhoneCheck() {
        orderPage.inputName.sendKeys("Инна");
        orderPage.inputLastName.sendKeys("Малышева");
        orderPage.inputAddress.sendKeys("Проспект Науки 1");
        orderPage.selectMetroStation("Алексеевская");

        orderPage.buttonNext.click();

        assertTrue(orderPage.inputPhoneError.isDisplayed());
        assertEquals("Введите корректный номер", orderPage.inputPhoneError.getText());
    }

    @Test
    @Description("Проверка отображения сообщений об ошибке в поле Адрес")
    public void fieldErrorPageOneAddressCheck() {
        orderPage.inputName.sendKeys("Инна");
        orderPage.inputLastName.sendKeys("Малышева");
        orderPage.selectMetroStation("Алексеевская");
        orderPage.inputPhone.sendKeys("11111111111");

        orderPage.buttonNext.click();

        // На сайте баг
        assertTrue(orderPage.inputAddressError.isDisplayed());
        assertEquals("Введите корректный адрес", orderPage.inputAddressError.getText());
    }

    @Test
    @Description("Проверка отображения сообщений об ошибке в поле Дата доставки")
    public void fieldErrorPageTwoDateCheck() {

        orderPage.inputName.sendKeys("Инна");
        orderPage.inputLastName.sendKeys("Малышева");
        orderPage.inputAddress.sendKeys("Проспект Науки 1");
        orderPage.selectMetroStation("Алексеевская");
        orderPage.inputPhone.sendKeys("11111111111");
        orderPage.buttonNext.click();

        orderPage.selectRentalPeriod("сутки");

        orderPage.buttonPlaceOrder.click();

        // На сайте отсутствут ошибка заполнения у обязательного поля даты доставки самоката
        assertTrue(orderPage.inputDateError.isDisplayed());
    }

    @Test
    @Description("Проверка отображения сообщений об ошибке в полe Период аренды")
    public void fieldErrorPageTwoPeriodCheck() {

        orderPage.inputName.sendKeys("Инна");
        orderPage.inputLastName.sendKeys("Малышева");
        orderPage.inputAddress.sendKeys("Проспект Науки 1");
        orderPage.selectMetroStation("Алексеевская");
        orderPage.inputPhone.sendKeys("11111111111");
        orderPage.buttonNext.click();

        orderPage.setDateToday();

        orderPage.buttonPlaceOrder.click();

        // На сайте отсутствут ошибка заполнения у обязательного поля срока аренды самоката
        assertTrue(orderPage.dropdownRentalPeriodError.isDisplayed());
    }
}
