package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AgendaPage {
    private WebDriver webDriver;
    private Actions actions;

    @FindBy(xpath = "//button[@class=' x-btn-text btnAgendaClose']")
    private WebElement backFromQuestionListButton;

    @FindBy(xpath = "//table[@id='btnSecretaryAgendaRefresh']//button")
    private WebElement reformAgendaButton;

    @FindBy(xpath = "//table[@id='btnSecretaryAgendaSend']//button")
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

    @FindBy(xpath = "//div[@class='textLayer']/div")
    private WebElement textFromAgenda;


    public AgendaPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        actions = new Actions(webDriver);
        PageFactory.initElements(webDriver, this);
    }

}
