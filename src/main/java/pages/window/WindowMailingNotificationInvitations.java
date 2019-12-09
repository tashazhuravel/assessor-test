package pages.window;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.AgendaPage;

import java.util.List;

public class WindowMailingNotificationInvitations {

    private WebDriver webDriver;
    private Actions actions;

    @FindBy(xpath = "//div[@class='x-window-header x-unselectable x-window-draggable']//span")
    private WebElement headerNotificationInvitations;
    private By headerMailing = By.xpath("//div[@class='x-window-header x-unselectable']//span");

    @FindBy(xpath = "//div[@id='checkboxgroup']//input")
    private List<WebElement> checkboxFIOParticipant;

    @FindBy(xpath = "//div[@id='checkboxgroup']//label")
    private List<WebElement> listFIOParticipant;

    @FindBy(xpath = "//span[@id='idSelectAll']")
    private WebElement checkboxSelectAll;

    @FindBy(xpath = "//span[@id='idUnselectAll']")
    private WebElement checkboxUnselectAll;

    @FindBy(xpath = "//span[@id='idInvert']")
    private WebElement checkboxInvertSelect;

    @FindBy(xpath = "//div[@id='checkboxgroup']//input[@name='material[]']")
    private List<WebElement> checkboxMaterials;

    @FindBy(xpath = "(//div[@id='checkboxgroup'])[2]//label")
    private List<WebElement> listMaterials;

    @FindBy(xpath = "//textarea[@name='comment']")
    private WebElement commentField;

    @FindBy(xpath = "//table[@id='agendaSendInvitationBtn']//button")
    private WebElement sendButton;

    @FindBy(xpath = "(//div[@class=' x-window']//td[@class='x-toolbar-cell']//button)[2]")
    private WebElement cancelButton;

    @FindBy(xpath = "//div[@class=' x-window']//div[@class='x-tool x-tool-close']")
    private WebElement closeByXButton;

    public WindowMailingNotificationInvitations(WebDriver webDriver) {
        this.webDriver = webDriver;
        actions = new Actions(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public WindowMailingNotificationInvitations clickCheckboxFIOParticipant() {
        checkboxFIOParticipant.iterator().next().click();
        return this;
    }

    public List<WebElement> getFIOParticipantText() {
        return listFIOParticipant;
    }

    public WindowMailingNotificationInvitations clickCheckboxSelectAll() {
        actions.moveToElement(checkboxSelectAll).click().perform();
        return this;
    }

    public WindowMailingNotificationInvitations clickCheckboxUnselectAll() {
        actions.moveToElement(checkboxUnselectAll).click().perform();
        return this;
    }

    public WindowMailingNotificationInvitations clickCheckboxInvertSelect() {
        actions.moveToElement(checkboxInvertSelect).click().perform();
        return this;
    }

    public WindowMailingNotificationInvitations clickCheckboxMaterials() {
        checkboxMaterials.iterator().next().click();
        return this;
    }

    public String getTextListMaterials() {
        return listMaterials.iterator().next().getText();
    }

    public void typeCommentField(String comment) {
        commentField.sendKeys(comment);
    }

    public AgendaPage clickSendButton() {
        actions.moveToElement(sendButton).click().perform();
        return new AgendaPage(webDriver);
    }

    public AgendaPage clickCancelButton() {
        actions.moveToElement(cancelButton).click().perform();
        return new AgendaPage(webDriver);
    }

    public AgendaPage clickCloseByXButton() {
        actions.moveToElement(closeByXButton).click().perform();
        return new AgendaPage(webDriver);
    }

    public String getHeaderNotificationInvitations() {
        return headerNotificationInvitations.getText();
    }

    public List<WebElement> getCheckboxAllFIOParticipant(){
        return checkboxFIOParticipant;
    }

    public By getHeaderMailing() {
        return headerMailing;
    }

    public List<WebElement> getListMaterials() {
        return listMaterials;
    }


    public List<WebElement> getCheckboxMaterials() {
        return checkboxMaterials;
    }

    public String getTextCommentField() {
        return commentField.getText();
    }
}
