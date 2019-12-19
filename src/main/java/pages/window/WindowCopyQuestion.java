package pages.window;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.sittingPage.CurrentMeetingPage;

public class WindowCopyQuestion {
    private WebDriver webDriver;
    private Actions actions;

    private By headerCopyQuestion = By.xpath("(//div[@class=' x-window x-resizable-pinned']//span)[1]");

    @FindBy(xpath = "//div[@class='x-window-footer x-panel-btns']//button")
    private WebElement closeCopyButton;

    //TODO дописать методы и переменные

    public WindowCopyQuestion(WebDriver webDriver){
        this.webDriver = webDriver;
        actions = new Actions(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public CurrentMeetingPage clickCloseCopyButton(){
        actions.moveToElement(closeCopyButton).click().perform();
        return new CurrentMeetingPage(webDriver);
    }

    public By getHeaderCopyQuestion() {
        return headerCopyQuestion;
    }
}
