package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.window.WindowUploadFile;

public class StatementPage {

    private WebDriver webDriver;

    private Actions actions;

    private By headerStatement = By.xpath("(//div[@id='viewPanel']//span)[1]");

    @FindBy(xpath = "//table[@id='excerptDownloadBtn']//button")
    private WebElement downloadThisText;

    @FindBy(xpath = "//table[@id='excerptUploadBtn']//button")
    private WebElement uploadEditedTextButton;

    @FindBy(xpath = "//table[@id='agendaEditBtn']//button")
    private WebElement editInWordButton;

    @FindBy(xpath = "//table[@id='refreshReportBtn']//button")
    private WebElement refreshStatement;

    @FindBy(xpath = "//div[@class='textLayer']/div")
    private WebElement textFromStatement;

    @FindBy(xpath = "//table[@id='excerptSendBtn']//button")
    private WebElement sendStatement;

    public StatementPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        actions = new Actions(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public StatementPage clickDownloadThisTextButton(){
        actions.moveToElement(downloadThisText).click().perform();
        return this;
    }

    public WindowUploadFile clickUploadEditedTextButton(){
        actions.moveToElement(uploadEditedTextButton).click().perform();
        return new WindowUploadFile(webDriver);
    }

    public StatementPage clickEditInWordButton(){
        actions.moveToElement(editInWordButton).click().perform();
        return this;
    }

    public StatementPage clickRefreshStatement(){
        actions.moveToElement(refreshStatement).click().perform();
        return this;
    }

    public By getHeaderStatement() {
        return headerStatement;
    }

    public WebElement getTextFromStatement() {
        return textFromStatement;
    }
}
