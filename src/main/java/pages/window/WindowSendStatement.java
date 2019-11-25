package pages.window;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.StatementPage;

import java.util.List;

public class WindowSendStatement {

    private WebDriver webDriver;
    private Actions actions;

    private By headerSendStatement = By.xpath("//div[@class='ui-dialog-titlebar ui-widget-header ui-corner-all ui-helper-clearfix']/span");

    @FindBy(xpath = "//div[@class='ui-dialog-titlebar ui-widget-header ui-corner-all ui-helper-clearfix']/a")
    private WebElement closeByXButton;

    @FindBy(xpath = "//div[@class='ui-dialog-content ui-widget-content']//table//input")
    private WebElement inputRecipientsField;

    @FindBy(xpath = "//ul[@class='ui-autocomplete ui-menu ui-widget ui-widget-content ui-corner-all']/li/a")
    private List<WebElement> selectRecipientsDropDown;

    @FindBy(xpath = "//div[@class='executorsList']/div")
    private List<WebElement> listRecipients;

    @FindBy(xpath = "//div[@class='executorsList']/div/a")
    private WebElement deleteRecipient;

    @FindBy(xpath = "(//div[@class='ui-dialog-buttonset']/button/span)[1]")
    private WebElement sendButton;

    @FindBy(xpath = "//button[@id='createDecisionCancel']")
    private WebElement cancelButton;

    public WindowSendStatement(WebDriver webDriver) {
        this.webDriver = webDriver;
        actions = new Actions(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public void typeRecipientFIOField(String name) {
        inputRecipientsField.sendKeys(name);
    }

    public void setRecipientFIO(String recipient) {
        actions.moveToElement(webDriver.findElement(By.xpath(String.format("//ul[@class='ui-autocomplete ui-menu ui-widget ui-widget-content ui-corner-all']/li/a[text() ='%s']", recipient)))).click();
        try {
            Thread.sleep(500L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getFIORecipient() {
        return listRecipients.iterator().next().getText();
    }

    public WindowSendStatement deleteRecipientFromList() {
        actions.moveToElement(deleteRecipient).click().perform();
        return this;
    }

    public WindowSendStatement clickSendButton() {
        actions.moveToElement(sendButton).click().perform();
        return this;
    }

    public StatementPage clickCancelButton() {
        actions.moveToElement(cancelButton).click().perform();
        return new StatementPage(webDriver);
    }

    public By getHeaderSendStatement() {
        return headerSendStatement;
    }
}
