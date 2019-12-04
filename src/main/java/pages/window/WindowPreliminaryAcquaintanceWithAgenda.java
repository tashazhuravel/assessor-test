package pages.window;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.AgendaPage;

import java.util.List;

public class WindowPreliminaryAcquaintanceWithAgenda {
    private WebDriver webDriver;
    private Actions actions;

    private By headerWindowPreliminaryAcquaintanceWithAgenda = By.xpath("//div[@class='x-window-header x-unselectable']//span");

    @FindBy(xpath = "//div[@id='checkboxgroup']//input")
    private List<WebElement> checkboxFIOParticipants;

    @FindBy(xpath = "//div[@id='checkboxgroup']//label")
    private List<WebElement> listFIOParticipants;

    @FindBy(xpath = "//input[@name='notify']")
    private WebElement checkboxNotifyOfReceipt;

    @FindBy(xpath = "//table[@id='agendaSendBtn']//button")
    private WebElement sendButton;

    @FindBy(xpath = "//table[@class='x-btn x-btn-noicon x-item-disabled']//button")
    private WebElement disabledSendButton;

    @FindBy(xpath = "(//table[@class='x-btn x-btn-noicon']//button)[2]")
    private WebElement cancelButton;

    public WindowPreliminaryAcquaintanceWithAgenda(WebDriver webDriver) {
        this.webDriver = webDriver;
        actions = new Actions(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public List<WebElement> getListFIOParticipants(){
        return listFIOParticipants;
    }

    public WindowPreliminaryAcquaintanceWithAgenda selectedCheckboxFIOParticipants(){
        checkboxFIOParticipants.iterator().next().click();
        return this;
    }

    public WindowPreliminaryAcquaintanceWithAgenda selectedCheckboxNotifyOfReceipt(){
        actions.moveToElement(checkboxNotifyOfReceipt).click().perform();
        return this;
    }

    public AgendaPage clickSendButton() {
        actions.moveToElement(sendButton).click().perform();
        return new AgendaPage(webDriver);
    }
    public AgendaPage clickCancelButton(){
        actions.moveToElement(cancelButton).click().perform();
        return new AgendaPage(webDriver);
        }

    public WebElement getDisabledSendButton() {
        return disabledSendButton;
    }

    public List<WebElement> getCheckboxFIOParticipants() {
        return checkboxFIOParticipants;
    }

    public WebElement getSendButton() {
        return sendButton;
    }
}
