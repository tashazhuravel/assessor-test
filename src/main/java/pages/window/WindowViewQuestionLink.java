package pages.window;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import sittingPage.CurrentMeetingPage;


public class WindowViewQuestionLink {

    private WebDriver webDriver;

    private Actions actions;

    private By headerViewLink = By.xpath("//div[@class=' x-window']//span/center");

    @FindBy(xpath = "(//div[@class=' x-window']//button)[1]")
    private WebElement goStittingButton;

    @FindBy(xpath = "//div[@id='textcontent']/p/b")
    private WebElement textSitting;

    @FindBy(xpath = "//div[@id='textcontent']/table")
    private WebElement textQuestion;

    @FindBy(xpath = "//div[@class=' x-window']//div[@class='x-tool x-tool-maximize']")
    private  WebElement maximizeButton;

    @FindBy(xpath = "//div[@class=' x-window']//div[@class='x-tool x-tool-close']")
    private WebElement closeByXButton;

    @FindBy(xpath = "(//div[@class=' x-window']//button)[2]")
    private WebElement closeButton;

    public WindowViewQuestionLink (WebDriver webDriver){
        this.webDriver = webDriver;
        actions = new Actions(webDriver);
        PageFactory.initElements(webDriver,this);
    }

    public CurrentMeetingPage clickgoSittingButton(){
        actions.moveToElement(goStittingButton).click().perform();
        return new CurrentMeetingPage(webDriver);
    }

    public String getTextSitting(){
        return textSitting.getText();
    }

    public String getQuestionText(){
        return textSitting.getText();
    }

    public WindowMaximizedViewLinkQuestion clickMaximizeButton(){
        actions.moveToElement(maximizeButton).click().perform();
        return new WindowMaximizedViewLinkQuestion(webDriver);
    }

    public CurrentMeetingPage clickCloseButton(){
        actions.moveToElement(closeButton).click().perform();
        return new CurrentMeetingPage(webDriver);
    }

    public CurrentMeetingPage clickCloseByXButton(){
        actions.moveToElement(closeByXButton).click().perform();
        return new CurrentMeetingPage(webDriver);
    }

    public By getHeaderViewLink() {
        return headerViewLink;
    }
}
