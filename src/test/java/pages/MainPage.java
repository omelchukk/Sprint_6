package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;


public class MainPage {

    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    //Вопросы в выпадающем списке
    private final By accordionHeadings = By.cssSelector("div.accordion__button");
    //Ответы в выпадающем списке
    private final By accordionItems = By.cssSelector("div.accordion__panel");
    //Кнопка Заказать Верхняя
    private final By orderButtonTop = By.xpath(".//div[starts-with(@class, 'Header_Nav')]//button[starts-with(@class, 'Button_Button')]");
    //Кнопка Заказать Нижняя
    private final By orderButtonBottom = By.xpath(".//div[starts-with(@class, 'Home_RoadMap')]//button[starts-with(@class, 'Button_Button')]");
    //Форма заказа
    private final By orderForm = By.xpath(".//div[starts-with(@class, 'Order_Form')]");

    public void waitForMainPageToLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.visibilityOfElementLocated(accordionHeadings));
    }

    public void scrollToAccordion() {
        WebElement element = driver.findElement(By.className("accordion"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }


    public void accordionHeadingClick(int index) {
        List<WebElement> items = driver.findElements(accordionHeadings);
        items.get(index).click();
    }

    public void waitForAccordionText(int index) {
        List<WebElement> panels = driver.findElements(accordionItems);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(panels.get(index)));
    }

    public String accordionItemGetText(int index) {
        List<WebElement> panels = driver.findElements(accordionItems);
        if (index < panels.size()) {
            return panels.get(index).getText();
        }
       else return "";
    }


    public boolean isCorrectTextDisplayed(int index) {
        List<WebElement> panels = driver.findElements(accordionItems);
        if (index < panels.size()) {
            panels.get(index).isDisplayed();
        }
        return true;
    }

    public void clickOrderButtonTop() {
        driver.findElement(orderButtonTop).click();

    }

    public void scrollToBottomOrderButton() {
        WebElement element = driver.findElement(By.xpath(".//div[starts-with(@class, 'Home_RoadMap')]//button[starts-with(@class, 'Button_Button')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    public void clickOrderButtonBottom() {
        driver.findElement(orderButtonBottom).click();
    }

    public void waitForOrderPageToLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(orderForm));
    }
}


