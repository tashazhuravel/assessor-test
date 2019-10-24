package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UnllocatedQuestions {
    protected WebDriver webDriver;

    @FindBy(xpath = "//textarea[@id='planningCommittee']")
    WebElement statusField;


            public UnllocatedQuestions(WebDriver webDriver) {
        this.webDriver = webDriver;
    }


}
