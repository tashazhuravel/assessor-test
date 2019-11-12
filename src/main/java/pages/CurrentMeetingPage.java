package pages;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.mainPageTab.PlanningTabPage;

import java.util.List;
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
    private WebElement openPicturesButton;

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

    @FindBy(xpath = "//table[@id='btnSecretaryBack']//button")
    private WebElement backOnListSitting;

    @FindBy(xpath = "//div[@id='planningQuestionTree']//span[@class='titlePanel']")
    private WebElement headerQuestionList;

    @FindBy(xpath = "//div[@class='x-tool x-tool-toggle x-tool-collapse-west']")
    private WebElement hideQuestionListButton;

    @FindBy(xpath = "//div[@class='x-tree-root-node']/li/div/a/span")
    private List<WebElement> questionList;

    @FindBy(xpath = "//span[@class='questionList']")
    private List<WebElement> questionListText;

    @FindBy(xpath = "//span[@class='questionStatus']/div[@class='questionStatusVotingWork']")
    private List<WebElement> questionStatusVotingWork; //есть атрибут title

    @FindBy(xpath = "//span[@class='questionStatus']/div[@class='questionStatusVotingInternal']")
    private List<WebElement> questionStatusVotingInternal;

    @FindBy(xpath = "//span[@class='questionStatus']/div[@class='questionStatusVotingAnonym']")
    private List<WebElement> questionStatusVotingAnonym;

    @FindBy(xpath = "//span[@class='questionStatus']/div[@class='questionStatusVotingAnonymWork']")
    private List<WebElement> questionStatusVotingAnonymWork;

    @FindBy(xpath = "//span[@class='questionStatus']/div[@class='questionStatusVoted']")
    private List<WebElement> questionStatusVoted;

    @FindBy(xpath = "//span[@class='questionStatus']/div[@class='questionStatusAllVoted']")
    private List<WebElement> questionStatusAllVoted;

    @FindBy(xpath = "//span[@class='questionStatus']/div[@class='questionStatusExamine']")
    private WebElement questionStatusExamine;

    //TODO добавить 2 статуса, они появляют при работе со сторонней системой

    @FindBy(xpath = "//div[@class='x-tree-node-el x-unselectable x-tree-node-collapsed x-tree-selected']")
    private WebElement selectedQuestionInList;

    @FindBy(xpath = "//div[@id='planningQuestionButtonSave']//button")
    private WebElement saveQuestionButton;

    @FindBy(xpath = "//table[@id='planningQuestionSelect']//button")
    private WebElement underConsiderationButton;

    @FindBy(xpath = "//table[@id='planningQuestionButtonVote']//button")
    private WebElement openVoteButton;

    @FindBy(xpath = "//table[@id='planningQuestionButtonVoteResult']//button")
    private WebElement voteResultButton;

    @FindBy(xpath = "//table[@id='planningQuestionButtonHand']//button")
    private WebElement handVoteButton;

    @FindBy(xpath = "//table[@id='planningQuestionButtonElectro']//button")
    private WebElement workVoteButton;

    @FindBy(xpath = "//table[@id='planningQuestionButtonAnonym']//button")
    private WebElement anonymVoteButton;

    @FindBy(xpath = "//table[@id='planningQuestionButtonAnonymWork']//button")
    private WebElement anonymWorkVoteButton;

    @FindBy(xpath = "//table[@id='planningQuestionButtonCopy']//button")
    private WebElement copeQuestionButton;

    @FindBy(xpath = "//table[@id='planningQuestionButtonMove']//button")
    private WebElement moveQuestionButton;

    @FindBy(xpath = "//table[@id='planningQuestionSandBackRevisionButton']//button")
    private WebElement sendBackRevisionButton;

    @FindBy(xpath = "//table[@id='planningQuestionButtonDel']//button")
    private WebElement deleteQuestionButton;




    By headerSelectedQuestion = By.xpath("//div[@id='questionContent']//span");


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
        Pattern regex = Pattern.compile("№\\d+");
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

