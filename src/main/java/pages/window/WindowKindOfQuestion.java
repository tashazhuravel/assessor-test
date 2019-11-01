package pages.window;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

public class WindowKindOfQuestion {

    private WebDriver webDriver;

    private Actions actions;

    public WindowKindOfQuestion(WebDriver webDriver) {
        this.webDriver = webDriver;
        actions = new Actions(webDriver);
        PageFactory.initElements(webDriver, this);
    }
}
