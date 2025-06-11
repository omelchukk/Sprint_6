package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPage {

    private WebDriver driver;

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    //Форма заказа
    private final By orderForm = By.xpath(".//div[starts-with(@class, 'Order_Form')]");
    //Имя
    private final By nameInput = By.xpath("//input[@placeholder='* Имя']");
    //Фамилия
    private final By surnameInput = By.xpath("//input[@placeholder='* Фамилия']");
    //Адрес
    private final By addressInput = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    //Поле станций метро
    private final By metroInput = By.className("select-search__input");
    //Кнопка куки
    private final By cookieButton = By.xpath("//*[@id=\"rcc-confirm-button\"]");
    //Телефон
    private final By phoneInput = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    //Кнопка Далее
    private final By nextButton = By.xpath("//button[text()='Далее']");
    //Выбор даты
    private final By dateInput = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    //Выбор срока аренды
    private final By termDropdown = By.className("Dropdown-root");
    //Цвет самоката
    private final By colorInput = By.xpath(".//div[starts-with(@class, 'Order_Checkboxes')]//label");
    //Комментарий
    private final By commentInput = By.xpath("//input[@placeholder='Комментарий для курьера']");
    //Кнопка Заказать
    private final By orderButton = By.xpath("(//button[contains(text(),'Заказать')])[2]");
    //Окно подтверждения заказа
    private final By confirmationModal = By.className("Order_ModalHeader__3FDaJ");
    //Кнопка Да
    private final By yesButton = By.xpath("(//button[contains(text(),'Заказать')])");
    //Окно подтверждения заказа
    private final By orderConfirmation = By.className("Order_Modal");

    public void waitForOrderPageToLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(orderForm));
    }

    public void enterPersonalDetails(String name, String surname, String address, String phone) {
        driver.findElement(nameInput).sendKeys(name);
        driver.findElement(surnameInput).sendKeys(surname);
        driver.findElement(addressInput).sendKeys(address);
        driver.findElement(phoneInput).sendKeys(phone);
    }
    public void selectMetro(String metroName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(metroInput));
        input.click();
        input.sendKeys(metroName);

        By metroOption = By.xpath(String.format("//div[@class='select-search__select']//div[text()='%s']", metroName));
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(metroOption));
        option.click();
    }

    public void pressNextButton() {
        driver.findElement(nextButton).click();
    }

    public void enterDate(String date) {
        driver.findElement(dateInput).sendKeys(date);
        driver.findElement(dateInput).sendKeys(Keys.ENTER);
    }

    public void pressCookieButton() {
        driver.findElement(cookieButton).click();
    }

    public void selectTerm(String term) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(termDropdown));
        dropdown.click();

        By termOption = By.xpath(String.format("//div[contains(@class, 'Dropdown-option') and text()='%s']", term));
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(termOption));
        option.click();
    }

    public void selectColor() {
        driver.findElement(colorInput).click();
    }

    public void enterComment(String comment) {
        driver.findElement(commentInput).sendKeys(comment);
    }

    public void pressOrderButton() {
        driver.findElement(orderButton).click();
    }


    public void pressYesButton() {
        driver.findElement(confirmationModal).getText();
        driver.findElement(yesButton).click();
    }

    public boolean isSuccessScreenDisplayed() {
        return driver.findElements(orderConfirmation).isEmpty();
    }
}


