package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.window.WindowMailingNotificationInvitations;
import pages.window.WindowUploadFile;
import pages.window.WindowsChooseQuestions;
import sittingPage.CurrentMeetingPage;

public class ProtocolPage {

    WebDriver webDriver;
    Actions actions;

    By headerProtocolPage = By.xpath("//div[@id='protocolContent']/div/div/div/div/span");

    @FindBy(xpath = "//table[@id='btnSecretaryProtocolClose']//button")
    private WebElement closeProtocol;

    @FindBy(xpath = "//table[@id='btnSecretaryProtocolRefresh']//button")
    private WebElement refreshProtocol;

    @FindBy(xpath = "//table[@id='btnSecretarySignedProtocolSend']//button")
    private WebElement sendProtocolButton;

    @FindBy(xpath = "//table[@id='btnSecretaryProtocolRecord']//button")
    private WebElement createStatementButton;

    @FindBy(xpath = "//table[@id='btnSecretaryProtocolEndorsement']//button")
    private WebElement setMeetingStatusProtocolUnderApprovalButton;

    @FindBy(xpath = "//table[@id='btnSecretaryProtocolConfirm']//button")
    private  WebElement setMeetingStatusProtocolApprovalButton;

    @FindBy(xpath = "//table[@id='btnSecretarySignedProtocolSend']//button")
    private WebElement sendForReviewButton;

    @FindBy(xpath = "//table[@id='protocolDownloadBtn']//button")
    private WebElement downloadThisTextButton;

    @FindBy(xpath = "//table[@id='protocolUploadBtn']//button")
    private WebElement uploadEditedTextButton;

    @FindBy(xpath = "//table[@id='agendaEditBtn']//button")
    private WebElement editInWordButton;

    @FindBy(xpath = "//div[@class='textLayer']/div")
    private WebElement textFromProtocol;

    public ProtocolPage(WebDriver webDriver){
        this.webDriver = webDriver;
        actions = new Actions(webDriver);
        PageFactory.initElements(webDriver,this);
    }

    public CurrentMeetingPage clickCloseProtocolButton(){
        actions.moveToElement(closeProtocol).click().perform();
        return new CurrentMeetingPage(webDriver);
    }

    public ProtocolPage clickRefreshProtocolButton(){
        actions.moveToElement(refreshProtocol).click().perform();
        return this;
    }

    public WindowMailingNotificationInvitations clickSendProtocolButton(){
        actions.moveToElement(sendProtocolButton).click().perform();
        return new WindowMailingNotificationInvitations(webDriver);
    }

    public WindowsChooseQuestions clickCreateStatementButton(){
        actions.moveToElement(createStatementButton).click().perform();
        return new WindowsChooseQuestions(webDriver);
    }

    public ProtocolPage clickDownloadThisTextButton(){
        actions.moveToElement(downloadThisTextButton).click().perform();
        return this;
    }

    public WindowUploadFile clickUploadEditedTextButton(){
        actions.moveToElement(uploadEditedTextButton).click().perform();
        return new WindowUploadFile(webDriver);
    }

    public ProtocolPage clickEditInWordButton(){
        actions.moveToElement(editInWordButton).click().perform();
        return this;
    }

    public WebElement getTextFromProtocol() {
        return textFromProtocol;
    }

    public By getHeaderProtocolPage() {
        return headerProtocolPage;
    }
}
