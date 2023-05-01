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
        pageURL = "https://qa-scooter.praktikum-services.ru/order";
    }

    // Кнопка 'Далее' на первой странице заказа
    @FindBy(xpath="//button[contains(text(), 'Далее')]")
    public WebElement buttonNext;

    // Инпут для поля 'Имя'
    @FindBy(xpath="//input[contains(@placeholder, '* Имя')]")
    public WebElement inputName;
    // Сообщение об ошибке для поля 'Имя'
    @FindBy(xpath="//input[contains(@placeholder, '* Имя')]//following-sibling::div")
    public WebElement inputNameError;

    // Инпут для поля 'Фамилия'
    @FindBy(xpath="//input[contains(@placeholder, '* Фамилия')]")
    public WebElement inputLastName;
    // Сообщение об ошибке для поля 'Фамилия'
    @FindBy(xpath="//input[contains(@placeholder, '* Фамилия')]//following-sibling::div")
    public WebElement inputLastNameError;

    // Инпут для поля 'Адрес'
    @FindBy(xpath="//input[contains(@placeholder, '* Адрес: куда привезти заказ')]")
    public WebElement inputAddress;
    // Сообщение об ошибке для поля 'Адрес'
    @FindBy(xpath="//input[contains(@placeholder, '* Адрес: куда привезти заказ')]/following-sibling::div")
    public WebElement inputAddressError;

    // Инпут для поля 'Станция метро'
    @FindBy(xpath="//input[contains(@placeholder, '* Станция метро')]")
    public WebElement inputStation;
    // Сообщение об ошибке для поля 'Станция метро'
    @FindBy(xpath="//div[contains(@class, 'Order_MetroError')]")
    public WebElement inputStationError;

    // Инпут для поля 'Телефон'
    @FindBy(xpath="//input[contains(@placeholder, '* Телефон: на него позвонит курьер')]")
    public WebElement inputPhone;
    // Сообщение об ошибке для поля 'Телефон'
    @FindBy(xpath="//input[contains(@placeholder, '* Телефон: на него позвонит курьер')]//following-sibling::div")
    public WebElement inputPhoneError;

    // Инпут для поля 'Когда привезти самокат'
    @FindBy(xpath="//input[contains(@placeholder, '* Когда привезти самокат')]")
    public WebElement inputDate;
    // Сообщение об ошибке для поля 'Когда привезти самокат'
    @FindBy(xpath="//input[contains(@placeholder, '* Когда привезти самокат')]//following-sibling::div[contains(@class, 'Input_ErrorMessage')")
    public WebElement inputDateError;

    // Инпут для выпадающего списка 'Срок аренды'
    @FindBy(xpath="//div[contains(@class, 'Dropdown-root')]")
    public WebElement dropdownRentalPeriod;
    // Сообщение об ошибке для поля 'Срок аренды'
    @FindBy(xpath="//div[contains(@class, 'Dropdown-root')]//following-sibling::div[contains(@class, 'Input_ErrorMessage')]")
    public WebElement dropdownRentalPeriodError;

    // Инпут для чекбокса 'Чёрный жемчуг'
    @FindBy(xpath="//input[@id='black']")
    public WebElement inputCheckboxBlack;

    // Инпут для чекбокса 'Серая безысходность'
    @FindBy(xpath="//input[@id='grey']")
    public WebElement inputCheckboxGrey;

    // Инпут для поля 'Комментарий для курьера'
    @FindBy(xpath="//input[contains(@placeholder, 'Комментарий для курьера')]")
    public WebElement inputComment;

    // Кнопка 'Заказать' на второй странице заказа
    @FindBy(xpath="//div[contains(@class, 'Order_Buttons')]/button[contains(text(), 'Заказать')]")
    public WebElement buttonPlaceOrder;

    // Кнопка 'Да' для подтверждения заказа на модальном окне
    @FindBy(xpath="//button[contains(text(), 'Да')]")
    public WebElement buttonOrderConfirm;

    // Заголовок модального окна завершения заказа
    @FindBy(xpath="//div[contains(@class, 'Order_ModalHeader')]")
    public WebElement headerModalOrder;

    public void selectMetroStation(String stationName) {
        inputStation.sendKeys(stationName);
        inputStation.sendKeys(Keys.SPACE);
        _driver.findElement(By.xpath(String.format("//div[contains(@class, 'select-search__select')]//div[contains(text(), '%s')]", stationName))).click();
    }

    public void setDateToday() {
        var date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        inputDate.sendKeys(date);
        inputDate.sendKeys(Keys.ENTER);
    }

    public void selectRentalPeriod(String period) {
        dropdownRentalPeriod.click();
        _driver.findElement(By.xpath(String.format("//div[contains(@class, 'Dropdown-option') and contains(text(), '%s')]", period))).click();
    }
}
