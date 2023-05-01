package qa.pom;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;

public abstract class BasePage {

    public String pageURL = null;
    protected WebDriver _driver = null;

    public BasePage(WebDriver driver) {
        _driver = driver;
        PageFactory.initElements(_driver, this);
    }

    // Логотип 'Самокат'
    @FindBy(xpath="//a[contains(@class, 'Header_LogoScooter')]")
    public WebElement logoScooter;

    // Логотип 'Яндекс'
    @FindBy(xpath="//a[contains(@class, 'Header_LogoYandex')]")
    public WebElement logoYandex;

    // Кнопка 'Заказать' в хэдере
    @FindBy(xpath="//div[contains(@class, 'Header_Nav')]/button[contains(text(), 'Заказать')]")
    public WebElement buttonOrderHeader;

    // Кнопка 'Статус заказа' в хэдере
    @FindBy(xpath="//div[contains(@class, 'Header_Nav')]/button[contains(text(), 'Статус заказа')]")
    public WebElement buttonOrderStatus;

    // Инпут для поля поиска заказа в хэдере
    @FindBy(xpath="//input[contains(@placeholder, 'Введите номер заказа')]")
    public WebElement inputOrderId;

    // Кнопка для поиска заказа в хэдере
    @FindBy(xpath="//button[contains(@class, 'Header_Button') and contains(text(), 'Go!')]")
    public WebElement buttonOrderStatusSearch;

    public void switchToTab(String title) {
        WebDriverWait wait = new WebDriverWait(_driver, Duration.ofSeconds(6));
        wait.pollingEvery(Duration.ofSeconds(2));
        wait.until(driver -> tryCheckTab(title));
    }

    private boolean tryCheckTab(String title) {
        ArrayList<String> tabs = new ArrayList<String> (_driver.getWindowHandles());
        for (String handle : tabs) {
            _driver.switchTo().window(handle);
            if (_driver.getTitle().equals(title)) {
                return true;
            }
        }
        return false;
    }

    public void navigateTo() {
        openURL(pageURL);
    }

    private void openURL(String url) {
        _driver.get(url);
    }

    public void scrollBottom() {
        JavascriptExecutor js = (JavascriptExecutor) _driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public WebElement scrollDownTo(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) _driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        return element;
    }
}
