package pages.window;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WindowNotification {
    private WebDriver webDriver;

    private Actions actions;

    private By headerNotificationWindow = By.xpath("//span[text()='Оповещения']");

    @FindBy(xpath = "//div[@class='event-item old-event-item']")
    private WebElement oldNotificationMessage;

    @FindBy(xpath = "//div[@class='event-item new-event-item']")
    private WebElement newNotificationMessage;

    @FindBy(xpath = "//div[@class='x-tool x-tool-close']")
    private WebElement closeWindowButton;

    @FindBy(xpath = "//button[text()='Очистить']")
    private WebElement clearWindowButton;

    @FindBy(xpath = "//button[text()='Закрыть']")
    private WebElement closeButton;


    public WindowNotification(WebDriver webDriver) {
        this.webDriver = webDriver;
        actions = new Actions(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public By getHeaderNotificationWindow() {
        return headerNotificationWindow;
    }
}
