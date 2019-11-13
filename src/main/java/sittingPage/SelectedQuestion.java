package sittingPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SelectedQuestion {

    private WebDriver webDriver;
    private Actions actions;

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

    @FindBy(xpath = "//textarea[@id='planningQuestionSubject']")
    private WebElement subjectQuestionField;

    @FindBy(xpath = "(//div[@class='x-panel-tbar x-panel-tbar-noheader']//button[text()='Очистить'])[1]")
    private List<WebElement> clearSubjectButton;

    @FindBy(xpath = "//input[@id='fioAttestedFieldId']")
    private WebElement fioAttestedField;

    @FindBy(xpath = "//div[@class = 'x-layer x-combo-list ']/div[@class='x-combo-list-inner']/div[text()]")
    private List<WebElement> fioAttestedList;

    @FindBy(xpath = "//input[@id='positionAttestedFieldId']")
    private WebElement positionAttestedField;

    @FindBy(xpath = "//table[@id='positionFioClearButton']//button")
    private WebElement positionFioClearButton;

    @FindBy(xpath = "//table[@id='positionClearButton']//button")
    private WebElement positionClearButton;

    @FindBy(xpath = "//input[@id='planningQuestionRepporteur']")
    private WebElement repporteurFIOField;

    @FindBy(xpath = "//div[@class = 'x-layer x-combo-list ']/div[@class='x-combo-list-inner']/div//span")
    private List<WebElement> repporteurFIOList;

    @FindBy(xpath = "//table[@id='btnAddRepporteur']//button")
    private WebElement addRepporteurButton;

    @FindBy(xpath = "//div[@id='planningQuestionRepporteurGrid']//table//span")
    private List<WebElement> addedRepporteurList;

    @FindBy(xpath = "//table[@id='secretaryQuestionDelRepporteurBtn']//button")
    private WebElement deleteRepporteurButton;

    @FindBy(xpath = "//input[@id='planningQuestionCoRepporteur']")
    private WebElement coRepporteurFIOField;

    @FindBy(xpath = "//table[@id='btnAddCoRepporteur']//button")
    private WebElement addCoRepporteurButton;

    @FindBy(xpath = "//div[@id='planningQuestionCoRepporteurGrid']//table//span")
    private List<WebElement> addedCoRepporteurList;

    @FindBy(xpath = "//table[@id='secretaryQuestionDelCoRepporteurBtn']//button")
    private WebElement deleteCoRreporteurButton;

    @FindBy(xpath = "//input[@id='planningQuestionInvited']")
    private WebElement invitedFIOField;

    @FindBy(xpath = "//table[@id='btnAddInvited']//button")
    private WebElement addInvitedButton;

    @FindBy(xpath = "//div[@id='planningQuestionInvitedGrid']//table//span")
    private List<WebElement> addedInvitedList;

    @FindBy(xpath = "//table[@id='secretaryQuestionDelInvitedBtn']//button")
    private WebElement deleteInvitedButton;

    @FindBy(xpath = "//input[@id='planningQuestionActed']")
    private WebElement actedFIOField;

    @FindBy(xpath = "//table[@id='btnAddActed']//button")
    private WebElement addActedButton;

    @FindBy(xpath = "//div[@id='planningQuestionActedGrid']//div[@class='x-grid3-cell-inner x-grid3-col-1']")
    private List<WebElement> addedActedList;

    @FindBy(xpath = "//table[@id='secretaryQuestionDelActedBtn']//button")
    private WebElement deleteActedButton;

    @FindBy(xpath = "//div[@id='addDocumentsForm']//input[@id='form-file']")
    private WebElement fileMaterialsField;

    @FindBy(xpath = "//div[@id='addDocumentsForm']//input[@id='form-file-file']")
    private WebElement addFileMaterials;

    @FindBy(xpath = "//div[@id='addDocumentsForm']//button")
    private WebElement chooseFileMaterialsButton;

    @FindBy(xpath = "//div[@id='addDocumentsForm']//input[@id='form-name']")
    private WebElement nameFileMaterials;

    @FindBy(xpath = "//table[@id='planningQuestionButtonDocument']//button")
    private WebElement addDocumentsButton;

    @FindBy(xpath = "//table[@id='documentDelButton']//button")
    private WebElement deleteDocumentButton;

    @FindBy(xpath = "//div[@id='planningQuestionMaterialsGrid']//div[@class='x-grid3-cell-inner x-grid3-col-2']")
    private List<WebElement> addedMaterialsList;

    @FindBy(xpath = "//div[@id='planningQuestionListenForm']//button")
    private WebElement editLisenInMSWordButton;

    @FindBy(xpath = "//textarea[@id='planningQuestionListen']")
    private WebElement listenTextField;

    @FindBy(xpath = "//div[@id='planningQuestionListenDoc']")
    private WebElement listenWordDocField;

    @FindBy(xpath = "//div[@id='planningQuestionDecideForm']//button")
    private WebElement editDecideInMSWordButton;

    @FindBy(xpath = "//textarea[@id='planningQuestionDecide']")
    private WebElement decideTextField;

    @FindBy(xpath = "//div[@id='planningQuestionDecideDoc']")
    private WebElement decideWordDocField;

    @FindBy(xpath = "//textarea[@id='planningQuestionHistory']")
    private WebElement historyField;

    @FindBy(xpath = "(//div[@class='x-panel-tbar x-panel-tbar-noheader']//button[text()='Очистить'])[2]")
    private WebElement clearHistoryButton;

    @FindBy(xpath = "//table[@id='historyAddButton']//button")
    private WebElement addLinkButton;

    @FindBy(xpath = "//table[@id='historyDelButton']//button")
    private WebElement deleteLinkButton;

    @FindBy(xpath = "//div[@id='historyListBox']//input")
    private WebElement checkboxLinkQuestion;

    @FindBy(xpath = "//div[@id='historyListBox']//a")
    private List<WebElement> linkQuestions;

    public SelectedQuestion(WebDriver webDriver){
        this.webDriver = webDriver;
        actions = new Actions(webDriver);
        PageFactory.initElements(webDriver,this);
    }

}
