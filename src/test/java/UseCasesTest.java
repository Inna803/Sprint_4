import jdk.jfr.Description;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import qa.pom.MainPage;
import qa.pom.OrderStatusPage;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UseCasesTest extends BaseTest {

    MainPage mainPage = null;

    @Before
    public void beforeUseCases() {
        mainPage = new MainPage(driver);
        mainPage.navigateTo();
    }

    @Test
    @Description("Проверяем результат клика по логотипу 'Самокат'")
    public void logoScooterClickTest() {
        mainPage.logoScooter.click();
        assertEquals(mainPage.pageURL, driver.getCurrentUrl());
    }

    @Test
    @Description("Проверяем результат клика по логотипу 'Яндекс'")
    public void logoYandexClickTest() {
        mainPage.logoYandex.click();
        // Сейчас по факту открывается Дзен
        mainPage.switchToTab("Дзен");
        assertEquals("https://dzen.ru/?yredirect=true", driver.getCurrentUrl());
    }

    @Test
    @Description("Проверяем клик по кнопке 'Заказать' в шапке страницы")
    public void orderButtonsTopOrderCheck() {
        mainPage.buttonOrderHeader.click();
        assertTrue(driver.getCurrentUrl().endsWith("/order"));
    }

    @Test
    @Description("Проверяем клик по кнопке 'Заказать' в конце центрального блока")
    public void orderButtonsBottomOrderCheck() {
        mainPage.scrollDownTo(mainPage.orderBottomButton).click();
        assertTrue(driver.getCurrentUrl().endsWith("/order"));
    }

    @Test
    @Description("Проверяем отображение результатов поиска несуществующего заказа")
    public void wrongOrderSearchTest() {
        mainPage.buttonOrderStatus.click();
        mainPage.inputOrderId.sendKeys("ошибка");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.elementToBeClickable(mainPage.buttonOrderStatusSearch)).click();

        var orderStatusPage = new OrderStatusPage(driver);

        assertTrue(wait.until(ExpectedConditions.visibilityOf(orderStatusPage.imgNotFound)).isDisplayed());
    }
}
