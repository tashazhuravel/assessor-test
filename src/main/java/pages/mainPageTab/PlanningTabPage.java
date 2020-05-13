package pages.mainPageTab;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.MainPage;
import pages.unallocatedQuestionPage.UnallocatedQuestions;
import pages.window.WindowSittingPlanning;
import pages.sittingPage.CurrentMeettingPage;

import java.time.Year;
import java.util.List;

public class PlanningTabPage extends MainPage {

    private By windowCreatePlanning = By.cssSelector(".x-window-header-text");

    @FindBy(xpath = "//fieldset[@id='fieldset-committees-64']//div[@id=64]/parent::span")
    private WebElement nameCommittee;

    @FindBy(xpath = "//fieldset[@id='fieldset-committees-64']//span[@class='btnSittingNum']")
    private List<WebElement> allNumberCommitteeButton;

    @FindBy(xpath = "//fieldset[@id='fieldset-committees-64']//span[@class='btnSittingState']")
    private List<WebElement> allStateCommitteeButton;

    @FindBy(xpath = "(//fieldset[@id='fieldset-committees-64']//table)[last()-2]")
    private WebElement committeeButton;

    @FindBy(xpath = "(//fieldset[@id='fieldset-committees-64']//span[@class='btnSittingDate'])[last()]")
    private WebElement day;

    @FindBy(xpath = "(//fieldset[@id='fieldset-committees-64']//span[@class='btnSittingDateMonth'])[last()]")
    private WebElement month;

    @FindBy(xpath = "(//fieldset[@id='fieldset-committees-64']//span[@class='btnSittingDateYear'])[last()]")
    private WebElement year;

    @FindBy(xpath = "(//fieldset[@id='fieldset-committees-64']//span[@class='btnSittingState'])[last()]")
    private WebElement state;

    @FindBy(xpath = "(//fieldset[@id='fieldset-committees-64']//span[@class='btnSittingNum'])[last()]")
    private WebElement numberCommitteeLastButton;

    @FindBy(xpath = "//fieldset[@id='fieldset-committees-64']//button[@class=' x-btn-text btnSittingInList btnSittingAgendaApproved btnSittingInListSecretary ']")
    private List<WebElement> committeePreparedButton;

    @FindBy(xpath = "//fieldset[@id='fieldset-committees-64']//button[@class=' x-btn-text btnSittingInList btnSittingAgendaForming btnSittingInListSecretary ']")
    private List<WebElement> committeePreparingButton;

    @FindBy(xpath = "//fieldset[@id='fieldset-committees-64']//button[@class=' x-btn-text btnSittingInList btnSittingOpen btnSittingInListSecretary ']")
    private List<WebElement> committeeOpenButton;

    @FindBy(xpath = "//fieldset[@id='fieldset-committees-64']//button[@class=' x-btn-text btnSittingInList btnSittingProtocolForming btnSittingInListSecretary ']")
    private List<WebElement> committeeCloseButton;

    @FindBy(xpath = "//fieldset[@id='fieldset-committees-64']//button[contains(@class ,'btnSittingUndistributed')]")
    private WebElement unllocatedQuestions;

    @FindBy(xpath = "//fieldset[@id='fieldset-committees-64']//button[contains(@class ,'btnSittingNew')]")
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

    public CurrentMeettingPage clickCommitteeButton() {
        actions.moveToElement(committeeButton).click().perform();
        return new CurrentMeettingPage(webDriver);
    }

    public CurrentMeettingPage clickPreparingCommitteeButton(){
        committeePreparingButton.iterator().next().click();
        return new CurrentMeettingPage(webDriver);
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

    public WebElement getState() {
        return state;
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
