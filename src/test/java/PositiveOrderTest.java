import jdk.jfr.Description;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import qa.pom.MainPage;
import qa.pom.OrderPage;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class PositiveOrderTest extends BaseTest {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { "Александра", "Иванова", "Красная Площадь, 2", "Площадь Революции", "11111111111", "сутки", false, false, "" },
                { "Евгений", "Киреев", "Кутузова, 226", "Строгино", "222222222222", "семеро суток", true, true, "" },
                { "Юлия", "Викторовна", "8 Марта, 5", "Сокольники", "3333333333333", "четверо суток", true, true, "Позвонить три раза" },
                { "Марина", "Семенова", "27 Марта, 41", "Южная", "3333333333333", "сутки", false, false, "Постучать четыре раза" }
        });
    }

    @Parameterized.Parameter
    public String fName;

    @Parameterized.Parameter(1)
    public String fLastname;

    @Parameterized.Parameter(2)
    public String fAddress;

    @Parameterized.Parameter(3)
    public String fStation;

    @Parameterized.Parameter(4)
    public String fPhone;

    @Parameterized.Parameter(5)
    public String fRentalPeriod;

    @Parameterized.Parameter(6)
    public boolean fColorBlack;

    @Parameterized.Parameter(7)
    public boolean fColorGrey;

    @Parameterized.Parameter(8)
    public String fComment;

    @Test
    @Description("Успешный флоу заказа самоката")
    public void ScooterOrderTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.navigateTo();
        mainPage.ButtonOrderHeader.click();

        OrderPage orderPage = new OrderPage(driver);
        orderPage.InputName.sendKeys(fName);
        orderPage.InputLastName.sendKeys(fLastname);
        orderPage.InputAddress.sendKeys(fAddress);
        orderPage.SelectMetroStation(fStation);
        orderPage.InputPhone.sendKeys(fPhone);

        orderPage.ButtonNext.click();

        orderPage.SetDateToday();
        orderPage.SelectRentalPeriod(fRentalPeriod);
        if (fColorBlack) orderPage.InputCheckboxBlack.click();
        if (fColorGrey) orderPage.InputCheckboxGrey.click();
        if (fComment.length() > 0) orderPage.InputComment.sendKeys(fComment);

        orderPage.ButtonPlaceOrder.click();
        orderPage.ButtonOrderConfirm.click();

        assertTrue(orderPage.HeaderModalOrder.getText().contains("Заказ оформлен"));
    }
}
