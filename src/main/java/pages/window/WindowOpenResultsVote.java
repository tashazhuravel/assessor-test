package pages.window;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

public class WindowOpenResultsVote {

    private WebDriver webDriver;
    private Actions actions;

    private By headerResultsVote = By.xpath("//div[@class=' x-window']//span");
    //TODO дописать формы результатов голосования
    public WindowOpenResultsVote(WebDriver webDriver){
        this.webDriver = webDriver;
        actions = new Actions(webDriver);
        PageFactory.initElements(webDriver,this);
    }

    public By getHeaderResultsVote() {
        return headerResultsVote;
    }
}
