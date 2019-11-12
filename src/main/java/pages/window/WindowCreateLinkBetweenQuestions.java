package pages.window;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

public class WindowCreateLinkBetweenQuestions {

    private WebDriver webDriver;
    private Actions actions;

    public WindowCreateLinkBetweenQuestions(WebDriver webDriver) {
        this.webDriver = webDriver;
        actions = new Actions(webDriver);
        PageFactory.initElements(webDriver,this);
    }
}
