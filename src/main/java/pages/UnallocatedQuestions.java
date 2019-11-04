package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UnallocatedQuestions {

    private WebDriver webDriver;

    @FindBy(xpath = "//textarea[@id='planningCommittee']")
    WebElement statusField;
    //todo дописать все элементы  и методы к ним

    public UnallocatedQuestions(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public String getTextStatusField() {
        return statusField.getAttribute("value");
    }
}
