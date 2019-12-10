package pages.mainPageTab;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.MainPage;
import pages.unallocatedQuestionPage.UnallocatedQuestions;
import pages.window.WindowSittingPlanning;
import sittingPage.CurrentMeetingPage;

import java.time.Year;
import java.util.List;

public class PlanningTabPage extends MainPage {

    private By windowCreatePlanning = By.cssSelector(".x-window-header-text");

    @FindBy(xpath = "//div[@id=64]")
    private WebElement nameCommittee;

    @FindBy(xpath = "//div[@id=64]/parent::span/parent::legend/parent::fieldset//span[@class='btnSittingNum']")
    private List<WebElement> allNumberCommitteeButton;

    @FindBy(xpath = "//div[@id=64]/parent::span/parent::legend/parent::fieldset//span[@class='btnSittingState']")
    private List<WebElement> allStateCommitteeButton;

    @FindBy(xpath = "(//div[@id=64]/parent::span/parent::legend/parent::fieldset//table)[last()-2]")
    private WebElement committeeButton;

    @FindBy(xpath = "(//div[@id=64]/parent::span/parent::legend/parent::fieldset//table)[last()-2]//span[@class='btnSittingDate']")
    private WebElement day;

    @FindBy(xpath = "(//div[@id=64]/parent::span/parent::legend/parent::fieldset//table)[last()-2]//span[@class='btnSittingDateMonth']")
    private WebElement month;

    @FindBy(xpath = "(//div[@id=64]/parent::span/parent::legend/parent::fieldset//table)[last()-2]//span[@class='btnSittingDateYear']")
    private WebElement year;

    @FindBy(xpath = "(//div[@id=64]/parent::span/parent::legend/parent::fieldset//table)[last()-2]//span[@class='btnSittingNum']")
    private WebElement numberCommitteeLastButton;

    @FindBy(xpath = "//div[@id=64]/parent::span/parent::legend/parent::fieldset//button[contains(@class ,'btnSittingUndistributed')]")
    private WebElement unllocatedQuestions;

    @FindBy(xpath = "//div[@id=64]/parent::span/parent::legend/parent::fieldset//button[contains(@class ,'btnSittingNew')]")
    private WebElement planningEventButton;

    public PlanningTabPage(WebDriver webDriver) {
        super(webDriver);
    }

    public WindowSittingPlanning clickPlanningEventButton() {
        planningEventButton.click();
        return new WindowSittingPlanning(webDriver);
    }

    public UnallocatedQuestions clickUnallocatedQuestionsButton() {
        unllocatedQuestions.click();
        return new UnallocatedQuestions(webDriver);
    }

    public CurrentMeetingPage clickCommitteeButton() {
        actions.moveToElement(committeeButton).click().perform();
        return new CurrentMeetingPage(webDriver);
    }

    public WebElement getNameCommittee() {
        return nameCommittee;
    }

    public String getCommitteeButtonText() {
        return committeeButton.getText();
    }

    public String getNumberCommitteeLastButtonText() {
        return numberCommitteeLastButton.getText();
    }

    public String getDate() {
        return String.format("%s%s.%d", day.getText(), month.getText(), Year.now().getValue());
    }

    public String getAllNumberSittingCommittee() {
        return allNumberCommitteeButton.iterator().next().getText();
    }

    public List<WebElement> getAllNumberCommitteeButton() {
        return allNumberCommitteeButton;
    }

    public List<WebElement> getAllStateCommitteeButton() {
        return allStateCommitteeButton;
    }
}
