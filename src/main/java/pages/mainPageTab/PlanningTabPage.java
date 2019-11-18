package pages.mainPageTab;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.MainPage;
import pages.UnallocatedQuestions;
import pages.window.WindowSittingPlanning;

import java.util.List;

public class PlanningTabPage extends MainPage {

    public PlanningTabPage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(xpath = "//div[@id=64]")
    private WebElement nameCommittee;

    @FindBy(xpath = "//div[@id=64]/parent::span/parent::legend/parent::fieldset//span[@class='btnSittingNum']")
    private List<WebElement> allNumberCommitteeButton;

    @FindBy(xpath = "//div[@id=64]/parent::span/parent::legend/parent::fieldset//span[@class='btnSittingState']")
    private List<WebElement> allStateCommitteeButton;

    @FindBy(xpath = "(//div[@id=64]/parent::span/parent::legend/parent::fieldset//table)[last()-2]")
    private WebElement committeeButton;

    @FindBy(xpath = "//div[@id=64]/parent::span/parent::legend/parent::fieldset//button[contains(@class ,'btnSittingUndistributed')]")
    private WebElement unllocatedQuestions;

    @FindBy(xpath = "//div[@id=64]/parent::span/parent::legend/parent::fieldset//button[contains(@class ,'btnSittingNew')]")
    private WebElement planningEventButton;

    private By windowCreatePlanning = By.cssSelector(".x-window-header-text");


    public WindowSittingPlanning clickPlanningEventButton() {
        planningEventButton.click();
        return new WindowSittingPlanning(webDriver);
    }

    public UnallocatedQuestions clickUnallocatedQuestionsButton() {
        unllocatedQuestions.click();
        return new UnallocatedQuestions(webDriver);
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
