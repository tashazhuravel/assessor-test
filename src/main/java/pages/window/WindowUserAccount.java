package pages.window;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.mainPageTab.PlanningTabPage;

import java.util.List;

public class WindowUserAccount {

    private WebDriver webDriver;

    private Actions actions;

    private By headerWindowUserAccount = By.xpath("//div/span[text()='Учётная запись пользователя']");
    private By labelEnableNotifications = By.xpath("//label[text()='Включить уведомления:']");

    @FindBy(xpath = "//div[contains(text(),'Секретарева')]")
    private List<WebElement> userFIOFieldText;

    @FindBy(xpath = "//button[text()='Сохранить']")
    private WebElement saveButton;

    @FindBy(xpath = "//button[text()='Закрыть']")
    private WebElement closeButton;

    @FindBy(xpath = "//span[text()='Учётная запись пользователя']/parent::div/child::div")
    private WebElement closeWindowButton;

    @FindBy(xpath = "//input[@id='checkboxNotifications']")
    private WebElement checkboxEnabledNotifications;

    @FindBy(xpath = "//input[@id='checkboxNotificationsMessages']")
    private WebElement checkboxEnabledShowNewNotificationsMessages;

    public WindowUserAccount(WebDriver webDriver) {
        this.webDriver = webDriver;
        actions = new Actions(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public String getTextByUserFIOField() {
        return userFIOFieldText.iterator().next().getText();
    }

    public WindowUserAccount clickCheckboxEnabledNotifications(){
        actions.moveToElement(checkboxEnabledNotifications).click().perform();
        return this;
    }

    public WindowUserAccount clickCheckboxEnabledShowNewNotificationsMessages(){
        actions.moveToElement(checkboxEnabledShowNewNotificationsMessages).click().perform();
        return this;
    }

    public PlanningTabPage saveUserAccount() {
        actions.moveToElement(saveButton).click().perform();
        return new PlanningTabPage(webDriver);
    }

    public PlanningTabPage closeWindowUserAccountByButton() {
        actions.moveToElement(closeButton).click().perform();
        return new PlanningTabPage(webDriver);
    }

    public PlanningTabPage closeWindowUserAccountByX() {
        actions.moveToElement(closeWindowButton).click().perform();
        return new PlanningTabPage(webDriver);
    }
    //ToDo добавить методы для работы с чекбоксами уведомлений

    public By getHeaderWindowUserAccount() {
        return headerWindowUserAccount;
    }

    public WebElement getSaveButton() {
        return saveButton;
    }

    public WebElement getCloseButton() {
        return closeButton;
    }

    public WebElement getCloseWindowButton() {
        return closeWindowButton;
    }

    public By getLabelEnableNotifications() {
        return labelEnableNotifications;
    }

    public List<WebElement> getUserFIOFieldText() {
        return userFIOFieldText;
    }
}



