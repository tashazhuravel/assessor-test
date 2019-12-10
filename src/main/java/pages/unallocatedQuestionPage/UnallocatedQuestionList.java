package pages.unallocatedQuestionPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

public class UnallocatedQuestionList {

    private WebDriver webDriver;

    private Actions actions;

    public UnallocatedQuestionList(WebDriver webDriver) {
        this.webDriver = webDriver;
        actions = new Actions(webDriver);
        PageFactory.initElements(webDriver, this);
    }
    //TODO доделать
}
