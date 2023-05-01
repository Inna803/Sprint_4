package qa.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage {
    public MainPage(WebDriver driver) {
        super(driver);
        pageURL = "https://qa-scooter.praktikum-services.ru/";
    }

    // Заголовок блока FAQ
    @FindBy(xpath="//div[contains(text(), 'Вопросы о важном')]")
    public WebElement questionsHeaderText;

    // Кнопка 'Заказать' в центральном блоке
    @FindBy(xpath="//div[contains(@class, 'Home_FinishButton')]/button[contains(text(), 'Заказать')]")
    public WebElement orderBottomButton;

    public WebElement findAccordionButtonByText(String text) {
        return _driver.findElement(By.xpath(String.format("//div[@data-accordion-component='AccordionItemHeading']/div[text()='%s']", text)));
    }

    public WebElement findAccordionPanelByText(String text) {
        return _driver.findElement(By.xpath(String.format("//div[@data-accordion-component='AccordionItemPanel']/p[text()='%s']", text)));
    }
}
