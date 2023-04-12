import jdk.jfr.Description;
import org.junit.Before;
import org.junit.Test;
import qa.pom.MainPage;

import static org.junit.Assert.*;

public class MainPageTest extends BaseTest {

    MainPage mainPage = null;

    @Before
    public void beforeMainTest() {
        mainPage = new MainPage(driver);
        mainPage.navigateTo();
    }

    @Test
    @Description("Проверяем отображение заголовка блока FAQ")
    public void faqBlockHeaderTest() {
        mainPage.scrollBottom();
        assertTrue("Заголовок блока FAQ не виден!", mainPage.questionsHeaderText.isDisplayed());
    }

    @Test
    @Description("Проверяем отображение пояснений по стоимости в блоке FAQ")
    public void faqBlockAccordionCostTextTest() {
        mainPage.scrollBottom();
        testFAQPanelTextAppear("Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой.");
    }

    @Test
    @Description("Проверяем отображение пояснений по количеству самокатов в блоке FAQ")
    public void faqBlockAccordionAmountTextTest() {
        mainPage.scrollBottom();
        testFAQPanelTextAppear("Хочу сразу несколько самокатов! Так можно?","Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.");
    }

    @Test
    @Description("Проверяем отображение пояснений по времени аренды в блоке FAQ")
    public void faqBlockAccordionTimeTextTest() {
        mainPage.scrollBottom();
        testFAQPanelTextAppear("Как рассчитывается время аренды?","Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.");

    }

    @Test
    @Description("Проверяем отображение пояснений по заказу день-в-день в блоке FAQ")
    public void faqBlockAccordionTodayTextTest() {
        mainPage.scrollBottom();
        testFAQPanelTextAppear("Можно ли заказать самокат прямо на сегодня?","Только начиная с завтрашнего дня. Но скоро станем расторопнее.");

    }

    @Test
    @Description("Проверяем отображение пояснений по срокам заказа в блоке FAQ")
    public void faqBlockAccordionDurationTextTest() {
        mainPage.scrollBottom();
        testFAQPanelTextAppear("Можно ли продлить заказ или вернуть самокат раньше?","Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.");
    }

    @Test
    @Description("Проверяем отображение пояснений по Зарядке в блоке FAQ")
    public void faqBlockAccordionChargeTextTest() {
        mainPage.scrollBottom();
        testFAQPanelTextAppear("Вы привозите зарядку вместе с самокатом?","Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.");
    }

    @Test
    @Description("Проверяем отображение пояснений по Отмене заказа в блоке FAQ")
    public void faqBlockAccordionCancelOrderTextTest() {
        mainPage.scrollBottom();
        testFAQPanelTextAppear("Можно ли отменить заказ?","Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.");
    }

    @Test
    @Description("Проверяем отображение пояснений по районам доставки в блоке FAQ")
    public void faqBlockAccordionMKADTextTest() {
        mainPage.scrollBottom();
        testFAQPanelTextAppear("Я жизу за МКАДом, привезёте?","Да, обязательно. Всем самокатов! И Москве, и Московской области.");
    }


    private void testFAQPanelTextAppear(String buttonText, String panelText) {
        // Проверяем что изначально панель с текстом не видна
        assertFalse("Панель с текстом видна!", mainPage.findAccordionPanelByText(panelText).isDisplayed());
        // Кликаем на заголовок
        mainPage.findAccordionButtonByText(buttonText).click();
        // Проверяем что теперь панель с текстом видна
        assertTrue("Панель с текстом не видна!", mainPage.findAccordionPanelByText(panelText).isDisplayed());
    }
}
