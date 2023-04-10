package qa.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OrderPage extends BasePage {

    public OrderPage(WebDriver driver) {
        super(driver);
        PageURL = "https://qa-scooter.praktikum-services.ru/order";
    }

    // Кнопка 'Далее' на первой странице заказа
    @FindBy(xpath="//button[contains(text(), 'Далее')]")
    public WebElement ButtonNext;

    // Инпут для поля 'Имя'
    @FindBy(xpath="//input[contains(@placeholder, '* Имя')]")
    public WebElement InputName;
    // Сообщение об ошибке для поля 'Имя'
    @FindBy(xpath="//input[contains(@placeholder, '* Имя')]//following-sibling::div")
    public WebElement InputNameError;

    // Инпут для поля 'Фамилия'
    @FindBy(xpath="//input[contains(@placeholder, '* Фамилия')]")
    public WebElement InputLastName;
    // Сообщение об ошибке для поля 'Фамилия'
    @FindBy(xpath="//input[contains(@placeholder, '* Фамилия')]//following-sibling::div")
    public WebElement InputLastNameError;

    // Инпут для поля 'Адрес'
    @FindBy(xpath="//input[contains(@placeholder, '* Адрес: куда привезти заказ')]")
    public WebElement InputAddress;
    // Сообщение об ошибке для поля 'Адрес'
    @FindBy(xpath="//input[contains(@placeholder, '* Адрес: куда привезти заказ')]/following-sibling::div")
    public WebElement InputAddressError;

    // Инпут для поля 'Станция метро'
    @FindBy(xpath="//input[contains(@placeholder, '* Станция метро')]")
    public WebElement InputStation;
    // Сообщение об ошибке для поля 'Станция метро'
    @FindBy(xpath="//div[contains(@class, 'Order_MetroError')]")
    public WebElement InputStationError;

    // Инпут для поля 'Телефон'
    @FindBy(xpath="//input[contains(@placeholder, '* Телефон: на него позвонит курьер')]")
    public WebElement InputPhone;
    // Сообщение об ошибке для поля 'Телефон'
    @FindBy(xpath="//input[contains(@placeholder, '* Телефон: на него позвонит курьер')]//following-sibling::div")
    public WebElement InputPhoneError;

    // Инпут для поля 'Когда привезти самокат'
    @FindBy(xpath="//input[contains(@placeholder, '* Когда привезти самокат')]")
    public WebElement InputDate;

    // Инпут для выпадающего списка 'Срок аренды'
    @FindBy(xpath="//div[contains(@class, 'Dropdown-root')]")
    public WebElement DropdownRentalPeriod;

    // Инпут для чекбокса 'Чёрный жемчуг'
    @FindBy(xpath="//input[@id='black']")
    public WebElement InputCheckboxBlack;

    // Инпут для чекбокса 'Серая безысходность'
    @FindBy(xpath="//input[@id='grey']")
    public WebElement InputCheckboxGrey;

    // Инпут для поля 'Комментарий для курьера'
    @FindBy(xpath="//input[contains(@placeholder, 'Комментарий для курьера')]")
    public WebElement InputComment;

    // Кнопка 'Заказать' на второй странице заказа
    @FindBy(xpath="//div[contains(@class, 'Order_Buttons')]/button[contains(text(), 'Заказать')]")
    public WebElement ButtonPlaceOrder;

    // Кнопка 'Да' для подтверждения заказа на модальном окне
    @FindBy(xpath="//button[contains(text(), 'Да')]")
    public WebElement ButtonOrderConfirm;

    // Заголовок модального окна завершения заказа
    @FindBy(xpath="//div[contains(@class, 'Order_ModalHeader')]")
    public WebElement HeaderModalOrder;

    public void SelectMetroStation(String stationName) {
        InputStation.sendKeys(stationName);
        InputStation.sendKeys(Keys.SPACE);
        _Driver.findElement(By.xpath(String.format("//div[contains(@class, 'select-search__select')]//div[contains(text(), '%s')]", stationName))).click();
    }

    public void SetDateToday() {
        var date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        InputDate.sendKeys(date);
        InputDate.sendKeys(Keys.ENTER);
    }

    public void SelectRentalPeriod(String period) {
        DropdownRentalPeriod.click();
        _Driver.findElement(By.xpath(String.format("//div[contains(@class, 'Dropdown-option') and contains(text(), '%s')]", period))).click();
    }
}
