package pages.unallocatedQuestionPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.mainPageTab.PlanningTabPage;

public class UnallocatedQuestions {

    private WebDriver webDriver;

    private Actions actions;

    @FindBy(xpath = "//textarea[@id='planningCommittee']")
    WebElement statusField;

    @FindBy(css = ".btnSittingBack")
    WebElement buttonBack;

    //todo дописать все элементы  и методы к ним

    public UnallocatedQuestions(WebDriver webDriver) {
        this.webDriver = webDriver;
        actions = new Actions(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public PlanningTabPage clickButtonBack() {
        buttonBack.click();
        return new PlanningTabPage(webDriver);
    }

    public String getTextStatusField() {
        return statusField.getAttribute("value");
    }
}
