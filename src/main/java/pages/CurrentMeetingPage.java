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
    private WebElement createNewQuestionButton;

    @FindBy(xpath = "//button[@class=' x-btn-text btnQuestionMove']")
    private WebElement addUnallocatedQuestionButton;

    @FindBy(xpath = "//button[@class=' x-btn-text btnAgendaOpen']")
    private WebElement agendaButton;

    @FindBy(xpath = "//table[@id='planningControlManagmentButtonInfoOpen']//button")
    private WebElement openInformationTableButton;

    @FindBy(xpath = "//table[@id='planningControlManagmentButtonPicturesOpen']//button")
    private  WebElement openPicturesButton;

    @FindBy(xpath = "//table[@id='btnPhonesConcole']//button")
    private WebElement openPhonesConsoleButton;

    @FindBy(xpath = "//table[@id='btnSecretaryProtocolVotingOpen']//button")
    private WebElement openVotingResultProtocol;

    @FindBy(xpath = "//table[@id='btnSecretaryProtocolOpen']//button")
    private WebElement openProtocol;

    @FindBy(xpath = "//table[@id='btnSecretarySittingOpen']//button")
    private WebElement openSittingButton;

    @FindBy(xpath = "//table[@id='btnSecretarySittingClose']//button")
    private WebElement closeSittingButton;

    @FindBy(xpath = "//table[@id='btnSecretarySittingArchive']//button")
    private WebElement putMeetingMaterialsInArchiveButton;

    @FindBy(xpath = "//div[@id ='popupWindowCreateQuestion']")
    private WebElement windowKindOfQuestion;

    @FindBy(xpath = "//div[@class=' x-window']")
    private WebElement windowAddUnallocatedQuestion;

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

    public WebElement getInformationFieldAboutSitting() {
        return informationFieldAboutSitting;
    }

    public WebElement getWindowKindOfQuestion() {
        return windowKindOfQuestion;
    }

    public WebElement getWindowAddUnallocatedQuestion() {
        return windowAddUnallocatedQuestion;
    }
}

