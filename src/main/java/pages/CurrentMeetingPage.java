package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CurrentMeetingPage {

    private @FindBy(xpath = "//textarea[@id='planningCommittee']")
    WebElement statusField;

    private WebDriver webDriver;

    public CurrentMeetingPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }
    public String getTextStatusField() {
        return statusField.getAttribute("value");
    }
}
