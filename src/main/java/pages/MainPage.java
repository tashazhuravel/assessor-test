package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import pages.mainPageTab.ArchiveTabPage;
import pages.mainPageTab.ManageTabPage;
import pages.mainPageTab.PlanningTabPage;
import pages.window.WindowAboutSystem;
import pages.window.WindowNotification;
import pages.window.WindowUserAccount;

import java.time.Duration;

import static java.time.temporal.ChronoUnit.SECONDS;

public class MainPage {

    protected WebDriver webDriver;
    private Actions actions;

    @FindBy(xpath = "//ul/li[@id='layoutTabs__planning']/a[2]")
    WebElement planningTab;

    @FindBy(xpath = "//ul/li[@id='layoutTabs__archivesearch']/a[2]")
    WebElement archiveSearchTab;

    @FindBy(xpath = "//ul/li[@id='layoutTabs__manage']/a[2]")
    WebElement manageTab;

    @FindBy(xpath = "//button[text()='Завершение сеанса']")
    WebElement logOutButton;

    @FindBy(xpath = "//table/tbody//td//table//em/button")
    WebElement userFIOButton;

    @FindBy(xpath = "//button[@class=' x-btn-text notificationBtn']")
    WebElement notificationButton;

    @FindBy(xpath = "//div[@id='windowNotifications']")
    WebElement notificationWindow;

    By notificationButtonHaveMessage = By.xpath("//button[@class=' x-btn-text hasNews']");
    By notificationMessageButton = By.xpath("//button[@class=' x-btn-text notificationBtn']");
   // By findUserFIOButton = By.xpath("//table/tbody//td//table//em/button");


    @FindBy(xpath = "//button[text()='О системе']")
    WebElement aboutSystemButton;

    public MainPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        actions = new Actions(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public void logOut() {
        actions.moveToElement(logOutButton).click().perform();
    }

    //Выбор вкладок
    public MainPage clickTab(ETab eTab) {
        switch (eTab) {
            case PLANNING:
                actions.moveToElement(planningTab).click().perform();
                return new PlanningTabPage(webDriver);
            case ARCHIVE:
                actions.moveToElement(archiveSearchTab).click().perform();
                return new ArchiveTabPage(webDriver);
            case MANAGER:
                actions.moveToElement(manageTab).click().perform();
                return new ManageTabPage(webDriver);
        }
        return null;
    }

    // Кнопка Учетная запись пользователя
    public WindowUserAccount clickButtonUserAccount() {
       actions.moveToElement(userFIOButton).click().perform();
        return new WindowUserAccount(webDriver);
    }

    public WindowNotification clickButtonNotification() {
        actions.moveToElement(notificationButton).click().perform();
        return new WindowNotification(webDriver);
    }

    public WindowNotification clickNotificationButtonHaveNewMessage() {
        webDriver.findElement(notificationButtonHaveMessage).click();
        return new WindowNotification(webDriver);
    }

    //Кнопка О системе
    public WindowAboutSystem clickButtonAboutSystem() {
        actions.moveToElement(aboutSystemButton).click().perform();
        return new WindowAboutSystem(webDriver);
    }

    public By getNotificationButtonHaveMessage() {
        return notificationButtonHaveMessage;
    }

    public WebElement getNotificationWindow() {
        return notificationWindow;
    }

    public By getNotificationMessageButton() {
        return notificationMessageButton;
    }

    public WebElement getUserFIOButton() {
        return userFIOButton;
    }

    public enum ETab {
        PLANNING, ARCHIVE, MANAGER
    }
}
