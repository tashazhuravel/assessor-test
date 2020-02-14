package pages.sittingPage;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.*;
import pages.mainPageTab.PlanningTabPage;
import pages.window.*;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CurrentMeettingPage {

    private WebDriver webDriver;

    private Actions actions;

    private By headerSelectedQuestion = By.xpath("//div[@id='questionContent']//span");

    @FindBy(xpath = "//textarea[@id='planningCommittee']")
    private WebElement informationFieldAboutSitting;

    @FindBy(xpath = "//div[@id='planningSittingState']/div/div")
    private WebElement statusField;

    @FindBy(xpath = "//button[@class=' x-btn-text btnSittingAttributesEdit']")
    private WebElement editRequisites;

    @FindBy(xpath = "//button[@class=' x-btn-text btnSittingDelete']")
    private WebElement cancelSittingButton;

    @FindBy(xpath = "//button[@class=' x-btn-text btnQuestionAdd']")
    private WebElement createNewQuestionButton;

    @FindBy(xpath = "//button[@class=' x-btn-text btnQuestionMove']")
    private WebElement addUnallocatedQuestionButton;

    @FindBy(xpath = "//button[@class=' x-btn-text btnAgendaOpen ru']")
    private WebElement agendaButton;

    @FindBy(xpath = "//table[@id='planningControlManagmentButtonInfoOpen']//button")
    private WebElement openInformationTableButton;

    @FindBy(xpath = "//table[@id='planningControlManagmentButtonPicturesOpen']//button")
    private WebElement openPicturesButton;

    @FindBy(xpath = "//table[@id='btnPhonesConcole']//button")
    private WebElement openPhonesConsoleButton;

    @FindBy(xpath = "//table[@id='btnSecretaryProtocolVotingOpen']//button")
    private WebElement openVotingResultProtocol;

    @FindBy(xpath = "//table[@id='btnSecretaryProtocolOpen']//button")
    private WebElement openProtocol;

    @FindBy(xpath = "//table[@id='planningQuestionButtonVoteBulk']//button")
    private WebElement openGroupVoteButton;

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

    @FindBy(xpath = "//div[@id='winPlanningControlAttributesButton']")
    private WebElement windowEditRequisitesSitting;

    @FindBy(xpath = "//table[@id='btnSecretaryBack']//button")
    private WebElement backOnListSitting;

    @FindBy(xpath = "//div[@id='planningQuestionTree']//span/span")
    private WebElement headerQuestionList;

    @FindBy(xpath = "//div[@class='x-tree-root-node']/li/div/a/span")
    private List<WebElement> questionList;

    public CurrentMeettingPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        actions = new Actions(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public String getTextInformationField() {
        return informationFieldAboutSitting.getAttribute("value");
    }

    public String getTextStatusField(){
        return statusField.getText();
    }

    public String getPartOfTextInformationField(String nameCommittee) {
        String result = StringUtils.EMPTY;
        Pattern regex = Pattern.compile("№\\d+");
        Matcher m = regex.matcher(nameCommittee);
        if (m.find()) {
            result = m.group();
        }
        return result;
    }

    public WindowEditMeetingRequisites clickEditRequisitesSitting() {
        actions.moveToElement(editRequisites).click().perform();
        return new WindowEditMeetingRequisites(webDriver);
    }

    public PlanningTabPage clickBackOnListSitting() {
        actions.moveToElement(backOnListSitting).click().perform();
        return new PlanningTabPage(webDriver);
    }

    public MainPage clickCancelSitting() {
        actions.moveToElement(cancelSittingButton).click().perform();
        return new MainPage(webDriver);
    }

    public WindowKindOfQuestion clickCreateNewQuestion() {
        actions.moveToElement(createNewQuestionButton).click().perform();
        return new WindowKindOfQuestion(webDriver);
    }

    public WindowAddUnllocatedQuestion clickAddUnllocatedQuestion() {
        actions.moveToElement(addUnallocatedQuestionButton).click().perform();
        return new WindowAddUnllocatedQuestion(webDriver);
    }

    public AgendaPage clickAgendaButton() {
        actions.moveToElement(agendaButton).click().perform();

        return new AgendaPage(webDriver);
    }

    public InformationTablePage clickOpenInformationTableButton() {
        actions.moveToElement(openInformationTableButton).click().perform();
        return new InformationTablePage(webDriver);
    }

    public IllustrationsPage clickOpenPicturesButton() {
        actions.moveToElement(openPicturesButton).click().perform();
        return new IllustrationsPage(webDriver);
    }

    public WindowPhoneManagementConsole clickOpenPhonesConsoleButton() {
        actions.moveToElement(openPhonesConsoleButton).click().perform();
        return new WindowPhoneManagementConsole(webDriver);
    }

    public CurrentMeettingPage clickOpenSittingButton() {
        actions.moveToElement(openSittingButton).click().perform();
        return this;
    }

    public WindowOpenGroupVote clickGroupVoteButton() {
        actions.moveToElement(openGroupVoteButton).click().perform();
        return new WindowOpenGroupVote(webDriver);
    }

    public VotingResultsProtocolPage clickOpenVotingResultProtocol() {
        actions.moveToElement(openVotingResultProtocol).click().perform();
        return new VotingResultsProtocolPage(webDriver);
    }

    public ProtocolPage clickOpenProtocol() {
        actions.moveToElement(openProtocol).click().perform();
        return new ProtocolPage(webDriver);
    }

    public WindowEditSittingRequisiteArchive clickPutMeetingMaterialsInArchive() {
        actions.moveToElement(putMeetingMaterialsInArchiveButton).click().perform();
        return new WindowEditSittingRequisiteArchive(webDriver);
    }

    public String getHeaderQuestionListText(){return headerQuestionList.getText();}

    public WebElement getInformationFieldAboutSitting() {
        return informationFieldAboutSitting;
    }

    public WebElement getWindowKindOfQuestion() {
        return windowKindOfQuestion;
    }

    public WebElement getWindowAddUnallocatedQuestion() {
        return windowAddUnallocatedQuestion;
    }

    public WebElement getWindowEditRequisitesSitting() {
        return windowEditRequisitesSitting;
    }

    public WebElement getStatusField() {
        return statusField;
    }

    public QuestionList QuestionList() {
        questionList.iterator().next().getText();
        return new QuestionList(webDriver);
    }

    public WebElement getOpenInformationTableButton() {
        return openInformationTableButton;
    }

    public WebElement getOpenSittingButton() {
        return openSittingButton;
    }
}

