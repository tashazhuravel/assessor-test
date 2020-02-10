package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.window.WindowUploadFile;
import pages.sittingPage.CurrentMeettingPage;


public class VotingResultsProtocolPage {

    private WebDriver webDriver;

    private Actions actions;

    private By headerVotingResultsProtocol = By.xpath("(//div[@id='protocolVotingContent']//span)[1]");

    @FindBy(xpath = "//table[@id='btnSecretaryProtocolClose']//button")
    private WebElement closeVotingResultsProtocol;

    @FindBy(xpath = "//table[@id='btnSecretaryProtocolVotingRefresh']//button")
    private WebElement refreshVotingResultsProtocol;

    @FindBy(xpath = "//table[@id='protocolVotingDownloadBtn']//button")
    private WebElement downloadThisTextButton;

    @FindBy(xpath = "//table[@id='protocolVotingUploadBtn']//button")
    private WebElement uploadEditedTextButton;

    @FindBy(xpath = "//table[@id='protocolVotingEditBtn']//button")
    private WebElement editInWordButton;

    @FindBy(xpath = "//div[@id='viewerContainer']//div[@class='textLayer']")
    private WebElement textVotingResultsProtocol;


    public VotingResultsProtocolPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        actions = new Actions(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public CurrentMeettingPage clickCloseVotingResultsProtocol() {
        actions.moveToElement(closeVotingResultsProtocol).click().perform();
        return new CurrentMeettingPage(webDriver);
    }

    public VotingResultsProtocolPage clickRefreshVotingResultsProtocol() {
        actions.moveToElement(refreshVotingResultsProtocol).click().perform();
        return this;
    }

    public VotingResultsProtocolPage clickDownloadThisTextButton() {
        actions.moveToElement(downloadThisTextButton).click().perform();
        return this;
    }

    public WindowUploadFile clickUploadEditedTextButton() {
        actions.moveToElement(uploadEditedTextButton).click().perform();
        return new WindowUploadFile(webDriver);
    }

    public VotingResultsProtocolPage clickEditInWordButton() {
        actions.moveToElement(editInWordButton).click().perform();
        return this;
    }

    public WebElement getTextVotingResultsProtocol() {
        return textVotingResultsProtocol;
    }
}
