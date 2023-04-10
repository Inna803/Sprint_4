package qa.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OrderStatusPage extends BasePage {
    public OrderStatusPage(WebDriver driver) {
        super(driver);
        PageURL = "https://qa-scooter.praktikum-services.ru/track";
    }

    // Картинка с сообщением об отсутствующем заказе
    @FindBy(xpath="//img[@alt='Not found']")
    public WebElement ImgNotFound;
}
