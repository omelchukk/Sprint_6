import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.MainPage;

public class MainPageTests {

private WebDriver driver;


    @Test

void checkAccordionButtons() {
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
    driver = new ChromeDriver(options);
    driver.get("https://qa-scooter.praktikum-services.ru/");

    MainPage objMainPage = new MainPage(driver);

    objMainPage.waitForMainPageToLoad();
    objMainPage.scrollToAccordion();

    objMainPage.waitForMainPageToLoad();
    objMainPage.scrollToAccordion();
    objMainPage.accordionHeadingClick(3);
    objMainPage.waitForAccordionText(3);
    objMainPage.accordionItemGetText(3);
    objMainPage.isAccordionTextDisplayed(3);
}

@Test
void checkTopOrderButton() {
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
    driver = new ChromeDriver(options);
    driver.get("https://qa-scooter.praktikum-services.ru/");

    MainPage objMainPage = new MainPage(driver);

    objMainPage.waitForMainPageToLoad();
    objMainPage.clickOrderButtonTop();
    objMainPage.waitForOrderPageToLoad();
}

@Test
void checkBottomOrderButton() {
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
    driver = new ChromeDriver(options);
    driver.get("https://qa-scooter.praktikum-services.ru/");

    MainPage objMainPage = new MainPage(driver);

    objMainPage.waitForMainPageToLoad();
    objMainPage.scrollToBottomOrderButton();
    objMainPage.clickOrderButtonBottom();
    objMainPage.waitForOrderPageToLoad();
}

@AfterEach
void teardown() {
    driver.quit();
}

}