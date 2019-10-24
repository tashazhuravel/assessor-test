package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class PlanningTabPage extends MainPage {

    public PlanningTabPage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(xpath = "//div[@id=64]")
    WebElement nameCommittee;

    @FindBy(xpath = "(//fieldset[last()]/div/div/table)[last()-2]")
    List<WebElement> committeeButton;

    @FindBy(xpath = "(//button[contains(text(),'Нераспределённые вопросы')])[last()]")
    WebElement unllocatedQuestions;

    @FindBy(xpath = "(//button[contains(text(),'Планировать новое заседание')])[last()]")
    WebElement planningEventButton;

    @FindBy(xpath = "//span[text()='Планирование заседания']")
    List<WebElement> windowCreatePlanning;


    public WindowMeetingScheduling clickPlanningEventButton() {
        planningEventButton.click();
        return new WindowMeetingScheduling(webDriver);
    }

    public boolean checkWindowPlanningCreate() {
        return windowCreatePlanning.isEmpty();
    }



}
