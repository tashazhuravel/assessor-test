package pages.mainPageTab;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.MainPage;
import pages.UnllocatedQuestions;
import pages.window.WindowMeetingScheduling;

import java.util.List;

public class PlanningTabPage extends MainPage {

    public PlanningTabPage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(xpath = "//div[@id=64]")
    private WebElement nameCommittee;

    @FindBy(xpath = "//fieldset[last()]/div/div/table//span[@class='btnSittingNum']")
    private List<WebElement> allNumberCommitteeButton;

    @FindBy(xpath = "(//fieldset[last()]/div/div/table)[last()-2]")
    private WebElement committeeButton;

    @FindBy(xpath = "(//button[contains(text(),'Нераспределённые вопросы')])[last()]")
    private WebElement unllocatedQuestions;

    @FindBy(xpath = "(//button[contains(text(),'Планировать новое заседание')])[last()]")
    private WebElement planningEventButton;

    private By windowCreatePlanning = By.xpath("//span[text()='Планирование заседания']");


    public WindowMeetingScheduling clickPlanningEventButton() {
        planningEventButton.click();
        return new WindowMeetingScheduling(webDriver);
    }

    public UnllocatedQuestions clickUnllocatedQuestionsButton(){
        unllocatedQuestions.click();
        return new UnllocatedQuestions(webDriver);
    }

    public List<WebElement> getAllNumberSittingCommittee() {
        return allNumberCommitteeButton;
    }

}
