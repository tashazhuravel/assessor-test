package pages;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.mainPageTab.PlanningTabPage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CurrentMeetingPage {

    private WebDriver webDriver;
    private Actions actions;

    @FindBy(xpath = "//textarea[@id='planningCommittee']")
    private WebElement informationFieldAboutSitting;

    @FindBy(xpath = "//div[@id='planningSittingState']//div[text()]")
    private WebElement statusField;

    @FindBy(xpath = "//button[@class=' x-btn-text btnSittingAttributesEdit']")
    private WebElement editRequisites;

    @FindBy(xpath = "//button[@class=' x-btn-text btnSittingDelete']")
    private WebElement cancelSittingButton;

    @FindBy(xpath = "//button[@class=' x-btn-text btnQuestionAdd']")
    private WebElement createNewQuestion;

    @FindBy(xpath = "//button[@class=' x-btn-text btnQuestionMove']")
    private WebElement addUnllocatedQuestion;

    @FindBy(xpath = "//button[@class=' x-btn-text btnAgendaOpen']")
    private WebElement agendaButton;

    @FindBy(xpath = "//div[@id ='popupWindowCreateQuestion']")
    private WebElement windowKindOfQuestion;

    @FindBy(xpath = "//div[@class=' x-window']")
    private WebElement windowAddUnllocatedQuestion;

    @FindBy(xpath = "//button[@class=' x-btn-text btnSittingBack']")
    private WebElement backOnListSitting;

    public CurrentMeetingPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        actions = new Actions(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public String getTextStatusField() {
        return informationFieldAboutSitting.getAttribute("value");
    }

    public String getPartOfTextStatusField(String nameCommittee) {
        String result = StringUtils.EMPTY;
        Pattern regex = Pattern.compile("№/d+");
        Matcher m = regex.matcher(nameCommittee);
        if (m.find()) {
            result = m.group();
        }
        return result;
    }

    public PlanningTabPage clickBackOnListSitting() {
        actions.moveToElement(backOnListSitting).click().perform();
        return new PlanningTabPage(webDriver);
    }

    public WebElement getWindowKindOfQuestion() {
        return windowKindOfQuestion;
    }

    public WebElement getWindowAddUnllocatedQuestion() {
        return windowAddUnllocatedQuestion;
    }
}

