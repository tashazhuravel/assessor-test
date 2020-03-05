package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.mainPageTab.ArchiveTabPage;
import pages.mainPageTab.ManageTabPage;
import pages.mainPageTab.PlanningTabPage;
import pages.window.WindowAboutSystem;
import pages.window.WindowNotification;
import pages.window.WindowUserAccount;

public class MainPage {

    protected WebDriver webDriver;

    protected Actions actions;

    private By notificationMessageButton = By.xpath("//button[@class=' x-btn-text notificationBtn']");

    private By divMainPage = By.xpath("//div[@id='layoutTabs']");

    @FindBy(xpath = "//ul/li[@id='layoutTabs__planning']/a[2]")
    private WebElement planningTab;

    @FindBy(xpath = "//ul/li[@id='layoutTabs__archivesearch']/a[2]")
    private WebElement archiveSearchTab;

    @FindBy(xpath = "//ul/li[@id='layoutTabs__manage']/a[2]")
    private WebElement manageTab;

    @FindBy(xpath = "//button[text()='Завершение сеанса']")
    private WebElement logOutButton;

    @FindBy(xpath = "//table/tbody//td//table//em/button")
    private WebElement userFIOButton;

    @FindBy(xpath = "//button[@class=' x-btn-text notificationBtn']")
    private WebElement notificationButton;

    @FindBy(xpath = "//div[@id='windowNotifications']")
    private WebElement notificationWindow;

    @FindBy(xpath = "//button[@class=' x-btn-text hasNews']")
    private WebElement notificationButtonHaveMessage;

    @FindBy(xpath = "//button[text()='О системе']")
    private WebElement aboutSystemButton;

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
        userFIOButton.click();
        return new WindowUserAccount(webDriver);
    }

    public WindowNotification clickButtonNotification() {
        actions.moveToElement(notificationButton).click().perform();
        return new WindowNotification(webDriver);
    }

    public WindowNotification clickNotificationButtonHaveNewMessage() {
        notificationButtonHaveMessage.click();
        return new WindowNotification(webDriver);
    }

    //Кнопка О системе
    public WindowAboutSystem clickButtonAboutSystem() {
        actions.moveToElement(aboutSystemButton).click().perform();
        return new WindowAboutSystem(webDriver);
    }

    //Кнопка Уведомления
    public WebElement getNotificationButtonHaveMessage() {
        return notificationButtonHaveMessage;
    }

    public WebElement getNotificationWindow() {
        return notificationWindow;
    }

    public By getNotificationMessageButton() {
        return notificationMessageButton;
    }

    public WebElement getAboutSystemButton() {
        return aboutSystemButton;
    }

    public By getDivMainPage() {
        return divMainPage;
    }

    public boolean isNotificationMessageButtonDisplay() {
        return webDriver.findElement(By.xpath("//table[@id='btnNotifications']/parent::td")).getCssValue("display").equals("none");
    }

    public WebElement getUserFIOButton() {
        return userFIOButton;
    }

    public enum ETab {
        PLANNING, ARCHIVE, MANAGER
    }
}
