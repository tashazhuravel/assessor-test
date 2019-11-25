package pages.window;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.MainPage;
import pages.attentionWindow.AttentionWindow;

public class WindowEditSittingRequisiteArchive {

    private WebDriver webDriver;
    private Actions actions;

    private By headerWindow = By.xpath("//div[@class=' x-window']/div/div/div/div/span");

    @FindBy(xpath = "//input[@name='dmsregn']")
    private WebElement numberProtocolField;

    @FindBy(xpath = "//input[@name='dmsregdate']")
    private WebElement dateProtocolField;

    @FindBy(xpath = "//div[@class=' x-window']//img")
    private WebElement calendarButton;

    @FindBy(xpath = "//div[@class='x-menu x-menu-floating x-layer x-date-menu x-menu-plain x-menu-nosep']")
    private WebElement calendar;

    @FindBy(xpath = "//td[@class='x-date-bottom']//button")
    private WebElement todayButton;

    @FindBy(xpath = "//table[@id='btnAttributesSave']//button")
    private WebElement saveButton;

    @FindBy(xpath = "(//div[@class=' x-window']//table[@class='x-btn x-btn-noicon']//button)[2]")
    private WebElement cancelButton;

    public WindowEditSittingRequisiteArchive(WebDriver webDriver) {
        this.webDriver = webDriver;
        actions = new Actions(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public void typeNumberProtocol(String number) {
        numberProtocolField.sendKeys(number);
    }

    public String getNumberProtocol() {
        return numberProtocolField.getText();
    }

    public void typeDateProtocol(String date) {
        dateProtocolField.sendKeys(date);
    }

    public String getDateProtocol() {
        return dateProtocolField.getText();
    }

    public WindowEditSittingRequisiteArchive clickCalendarButton() {
        actions.moveToElement(calendar).click().perform();
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }

    public WindowEditSittingRequisiteArchive clickTodayButton() {
        actions.moveToElement(todayButton).click().perform();
        return this;
    }

    public AttentionWindow clickSaveButton() {
        actions.moveToElement(saveButton).click().perform();
        return new AttentionWindow(webDriver);
    }

    public MainPage clickCancelButton() {
        actions.moveToElement(cancelButton).click().perform();
        return new MainPage(webDriver);
    }

    public By getHeaderWindow() {
        return headerWindow;
    }
}
