package pages.window;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.AgendaPage;

public class WindowUploadFile {
    private WebDriver webDriver;
    private Actions actions;

    By headerWindowUploadFile = By.xpath("//div[@class=' x-window']//span");

    @FindBy(xpath = "//div[@class=' x-window']//input[@name='file']")
    WebElement inputFile;

    @FindBy(xpath = "//div[@class=' x-window']//button[1]")
    WebElement chooseFileButton;

    @FindBy(xpath = "(//div[@class=' x-window']//button)[2]")
    WebElement uploadFileButton;

    @FindBy(xpath = "(//div[@class=' x-window']//button)[3]")
    WebElement  cancelUploadFileButton;

    public WindowUploadFile(WebDriver webDriver){
        this.webDriver = webDriver;
        actions = new Actions(webDriver);
        PageFactory.initElements(webDriver,this);
    }

    public void setInputFile(String path){inputFile.sendKeys();}

    public AgendaPage clickUploadFileButton(){
        actions.moveToElement(uploadFileButton).click().perform();
        return new AgendaPage(webDriver);
    }

    public AgendaPage clickCancelUploadButton(){
        actions.moveToElement(cancelUploadFileButton).click().perform();
        return new AgendaPage(webDriver);
    }

    public By getHeaderWindowUploadFile() {
        return headerWindowUploadFile;
    }

}
