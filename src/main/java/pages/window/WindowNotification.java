package pages.window;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;
import pages.CurrentMeetingPage;
import pages.mainPageTab.PlanningTabPage;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WindowNotification {
    private WebDriver webDriver;

    private Actions actions;

    private By headerNotificationWindow = By.xpath("//span[text()='Оповещения']");
    private By haveNewNotificationMessage = By.xpath("//div[@class='event-item new-event-item']");
    private By haveOldNotificationMessage = By.xpath("//div[@class='event-item old-event-item']");

    @FindBy(xpath = "//div[@class='event-item old-event-item']")
    private List<WebElement> oldNotificationMessages;

    @FindBy(xpath = "//div[@class='event-item new-event-item']//span[@class ='red-point']")
    private List<WebElement> newNotificationMessage;

    @FindBy(xpath = "//div[@class='event-item old-event-item']//p/span[text()]")
    private List<WebElement> sittingLinkNotificationMessage;

    @FindBy(xpath = "//div[@class='event-item old-event-item']//p[text()]")
    private WebElement oldNotificationMessageText;

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

    public PlanningTabPage closeWindowNotificationByX() {
        actions.moveToElement(closeWindowButton).click().perform();
        return new PlanningTabPage(webDriver);
    }

    public WindowNotification clickNewNotificationMessage() {
        newNotificationMessage.iterator().next().click();
        return this;
    }

    public CurrentMeetingPage clickLinkSittingNotificationMessage() {
        sittingLinkNotificationMessage.iterator().next().click();
        return new CurrentMeetingPage(webDriver);
    }

    public WindowNotification clickClearButton() {
        actions.moveToElement(clearWindowButton).click().perform();
        return this;
    }

    public PlanningTabPage clickCloseButton() {
        actions.moveToElement(closeButton).click().perform();
        return new PlanningTabPage(webDriver);
    }

    public String getTextOldNotificationMessage() {
        return oldNotificationMessageText.getText();
    }

    public String getNumberSittingFromNottificationMessage() {
        String result = StringUtils.EMPTY;
        Pattern regex = Pattern.compile("№/d+");
        Matcher m = regex.matcher(oldNotificationMessageText.getText());
        if (m.find()) {
            result = m.group();
        }
        return result;
    }

    public By getHeaderNotificationWindow() {
        return headerNotificationWindow;
    }

    public By getHaveNewNotificationMessage() {
        return haveNewNotificationMessage;
    }

    public By getHaveOldNotificationMessage() {
        return haveOldNotificationMessage;
    }

}
