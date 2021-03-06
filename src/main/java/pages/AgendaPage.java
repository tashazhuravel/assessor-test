package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.attentionWindow.AttentionWindow;
import pages.messageWindow.MessageWindow;
import pages.window.WindowMailingNotificationInvitations;
import pages.window.WindowPreliminaryAcquaintanceWithAgenda;
import pages.window.WindowUploadFile;
import pages.sittingPage.CurrentMeettingPage;

import java.util.List;

public class AgendaPage {

    private WebDriver webDriver;

    private Actions actions;

    @FindBy(xpath = "//button[@class=' x-btn-text btnAgendaClose ru']")
    private WebElement backToQuestionListButton;

    @FindBy(xpath = "//table[@id='btnSecretaryAgendaRefresh']//button")
    private WebElement reformAgendaButton;

    @FindBy(xpath = "//table[@id= 'btnSecretaryAgendaSend']//button")
    private WebElement sendAgendaButton;

    @FindBy(xpath = "//table[@id='btnSecretaryAgendaEndorsementSetSended']//button")
    private WebElement setMeetingStatusAgendaUnderApprovalButton;

    @FindBy(xpath = "//table[@id='btnSecretaryAgendaEndorsementSetApproved']//button")
    private WebElement setMeetingStatusAgendaApprovedButton;

    @FindBy(xpath = "//table[@id='btnSecretaryAgendaSendInvitation']//button")
    private WebElement sendInvitationButton;

    @FindBy(xpath = "//table[@id='protocolDownloadBtn']//button")
    private WebElement downloadThisTextButton;

    @FindBy(xpath = "//table[@id='protocolUploadBtn']//button")
    private WebElement uploadEditedTextButton;

    @FindBy(xpath = "//table[@id='agendaEditBtn']//button")
    private WebElement editInWordButton;

    @FindBy(xpath = "//div[@id='agendaContent']/div/div/div/div/span")
    private WebElement headerAgenda;

    public AgendaPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        actions = new Actions(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public CurrentMeettingPage clickBackFromQuestionListButton() {
        actions.moveToElement(backToQuestionListButton).click().perform();
        return new CurrentMeettingPage(webDriver);
    }

    public AgendaPage clickReformAgendaButton() {
        actions.moveToElement(reformAgendaButton).click().perform();
        return this;
    }

    public WindowPreliminaryAcquaintanceWithAgenda clickSendAgendaButton() {
        actions.moveToElement(sendAgendaButton).click().perform();
        return new WindowPreliminaryAcquaintanceWithAgenda(webDriver);
    }

    public MessageWindow clickSetMeetingStatusAgendaUnderApprovalButton() {
        actions.moveToElement(setMeetingStatusAgendaUnderApprovalButton).click().perform();
        return new MessageWindow(webDriver);
    }

    public AttentionWindow clickSetMeetingStatusAgendaApprovedButton() {
        actions.moveToElement(setMeetingStatusAgendaApprovedButton).click().perform();
        return new AttentionWindow(webDriver);
    }

    public WindowMailingNotificationInvitations clickSendInvitationButton() {
        actions.moveToElement(sendInvitationButton).click().perform();
        return new WindowMailingNotificationInvitations(webDriver);
    }

    public AgendaPage clickDownloadThisTextButton() {
        actions.moveToElement(downloadThisTextButton).click().perform();
        return this;
    }

    public WindowUploadFile clickUploadEditedTextButton() {
        actions.moveToElement(uploadEditedTextButton).click().perform();
        return new WindowUploadFile(webDriver);
    }

    public AgendaPage clickEditInWordButton() {
        actions.moveToElement(editInWordButton).click().perform();
        return this;
    }

    public String getHeaderAgenda() {
        return headerAgenda.getText();
    }


}
