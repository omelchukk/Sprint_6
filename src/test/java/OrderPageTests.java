import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.MainPage;
import pages.OrderPage;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderPageTests {
    private WebDriver driver;

    static Stream<Arguments> personalDetailsTestData() {
        return Stream.of(
                Arguments.of("Эдвард", "Каллен", "Ул. Пушкина, д. Колотушкина", "Сокольники", "88005553535", "07.07.2025", "двое суток", "Позвоните за 5 минут"),
                Arguments.of("Райан", "Гослинг", "Ул. Вязов, д.13", "Черкизовская", "89031234567", "13.09.2025", "трое суток", "Оставьте у двери")
        );
    }

    @ParameterizedTest
    @MethodSource("personalDetailsTestData")
    void checkOrderTest(String name, String surname, String address, String metroName, String phone, String date, String term, String comment) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.get("https://qa-scooter.praktikum-services.ru/");

        MainPage objMainPage = new MainPage(driver);
        objMainPage.waitForMainPageToLoad();
        objMainPage.clickOrderButtonTop();

        OrderPage objOrderPage = new OrderPage(driver);
        objOrderPage.waitForOrderPageToLoad();
        objOrderPage.enterPersonalDetails(name, surname, address, metroName, phone);
        objOrderPage.selectMetro(metroName);
        objOrderPage.pressCookieButton();
        objOrderPage.pressNextButton();
        objOrderPage.enterDate(date);
        objOrderPage.selectTerm(term);
        objOrderPage.selectColor();
        objOrderPage.enterComment(comment);
        objOrderPage.pressOrderButton();
        objOrderPage.pressYesButton();

        assertTrue(objOrderPage.isSuccessScreenDisplayed(), "Не удалось оформить заказ");

    }

    @AfterEach
    void teardown() {
        driver.quit();
    }
}
