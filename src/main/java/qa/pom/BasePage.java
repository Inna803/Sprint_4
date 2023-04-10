package qa.pom;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;

public abstract class BasePage {

    public String PageURL = null;
    protected WebDriver _Driver = null;

    public BasePage(WebDriver driver) {
        _Driver = driver;
        PageFactory.initElements(_Driver, this);
    }

    // Логотип 'Самокат'
    @FindBy(xpath="//a[contains(@class, 'Header_LogoScooter')]")
    public WebElement LogoScooter;

    // Логотип 'Яндекс'
    @FindBy(xpath="//a[contains(@class, 'Header_LogoYandex')]")
    public WebElement LogoYandex;

    // Кнопка 'Заказать' в хэдере
    @FindBy(xpath="//div[contains(@class, 'Header_Nav')]/button[contains(text(), 'Заказать')]")
    public WebElement ButtonOrderHeader;

    // Кнопка 'Статус заказа' в хэдере
    @FindBy(xpath="//div[contains(@class, 'Header_Nav')]/button[contains(text(), 'Статус заказа')]")
    public WebElement ButtonOrderStatus;

    // Инпут для поля поиска заказа в хэдере
    @FindBy(xpath="//input[contains(@placeholder, 'Введите номер заказа')]")
    public WebElement InputOrderId;

    // Кнопка для поиска заказа в хэдере
    @FindBy(xpath="//button[contains(@class, 'Header_Button') and contains(text(), 'Go!')]")
    public WebElement ButtonOrderStatusSearch;

    public void switchToTab(String title) {
        WebDriverWait wait = new WebDriverWait(_Driver, Duration.ofSeconds(6));
        wait.pollingEvery(Duration.ofSeconds(2));
        wait.until(driver -> TryCheckTab(title));
    }

    private boolean TryCheckTab(String title) {
        ArrayList<String> tabs = new ArrayList<String> (_Driver.getWindowHandles());
        for (String handle : tabs) {
            _Driver.switchTo().window(handle);
            if (_Driver.getTitle().equals(title)) {
                return true;
            }
        }
        return false;
    }

    public void navigateTo() {
        openURL(PageURL);
    }

    private void openURL(String url) {
        _Driver.get(url);
    }

    public void scrollBottom() {
        JavascriptExecutor js = (JavascriptExecutor) _Driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public WebElement scrollDownTo(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) _Driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        return element;
    }
}
