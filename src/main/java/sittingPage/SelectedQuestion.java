package sittingPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.attentionWindow.AttentionWindow;
import pages.window.*;

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
    private WebElement copyQuestionButton;

    @FindBy(xpath = "//table[@id='planningQuestionButtonMove']//button")
    private WebElement moveQuestionButton;

    @FindBy(xpath = "//table[@id='planningQuestionSandBackRevisionButton']//button")
    private WebElement sendBackRevisionButton;

    @FindBy(xpath = "//table[@id='planningQuestionButtonDel']//button")
    private WebElement deleteQuestionButton;

    @FindBy(xpath = "//textarea[@id='planningQuestionSubject']")
    private WebElement subjectQuestionField;

    @FindBy(xpath = "(//div[@class='x-panel-tbar x-panel-tbar-noheader']//button[text()='Очистить'])[1]")
    private WebElement clearSubjectButton;

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

    @FindBy(xpath = "(//div[@class = 'x-layer x-combo-list ']/div[@class='x-combo-list-inner']/div//span)[1]")
    private List<WebElement> repporteurFIOList;

    @FindBy(xpath = "//table[@id='btnAddRepporteur']//button")
    private WebElement addRepporteurButton;

    @FindBy(xpath = "//div[@id='planningQuestionRepporteurGrid']//table//span")
    private List<WebElement> addedRepporteurList;

    @FindBy(xpath = "//table[@id='secretaryQuestionDelRepporteurBtn']//button")
    private WebElement deleteRepporteurButton;

    @FindBy(xpath = "//input[@id='planningQuestionCoRepporteur']")
    private WebElement coRepporteurFIOField;

    @FindBy(xpath = "(//div[@class = 'x-layer x-combo-list ']/div[@class='x-combo-list-inner']/div//span)[2]")
    private List<WebElement> coRepporteurFIOList;

    @FindBy(xpath = "//table[@id='btnAddCoRepporteur']//button")
    private WebElement addCoRepporteurButton;

    @FindBy(xpath = "//div[@id='planningQuestionCoRepporteurGrid']//table//span")
    private List<WebElement> addedCoRepporteurList;

    @FindBy(xpath = "//table[@id='secretaryQuestionDelCoRepporteurBtn']//button")
    private WebElement deleteCoRreporteurButton;

    @FindBy(xpath = "//input[@id='planningQuestionInvited']")
    private WebElement invitedFIOField;

    @FindBy(xpath = "(//div[@class = 'x-layer x-combo-list ']/div[@class='x-combo-list-inner']/div//span)[3]")
    private List<WebElement> invitedFIOList;

    @FindBy(xpath = "//table[@id='btnAddInvited']//button")
    private WebElement addInvitedButton;

    @FindBy(xpath = "//div[@id='planningQuestionInvitedGrid']//table//span")
    private List<WebElement> addedInvitedList;

    @FindBy(xpath = "//table[@id='secretaryQuestionDelInvitedBtn']//button")
    private WebElement deleteInvitedButton;

    @FindBy(xpath = "//input[@id='planningQuestionActed']")
    private WebElement actedFIOField;

    @FindBy(xpath = "(//div[@class = 'x-layer x-combo-list ']/div[@class='x-combo-list-inner']/div//span)[4]")
    private List<WebElement> actedFIOList;

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
    private List<WebElement> checkboxLinkQuestion;

    @FindBy(xpath = "//div[@id='historyListBox']//a")
    private List<WebElement> linkQuestions;

    @FindBy(xpath = "//div[@id='planningQuestionTree']//span/span")
    private WebElement headerQuestionList;

    public SelectedQuestion(WebDriver webDriver) {
        this.webDriver = webDriver;
        actions = new Actions(webDriver);
        PageFactory.initElements(webDriver, this);
    }
    //TODO дописать методы

    public SelectedQuestion clickSaveQuestionButton() {
        actions.moveToElement(saveQuestionButton).click().perform();
        return this;
    }

    public SelectedQuestion clickUnderConsiderationButton() {
        actions.moveToElement(underConsiderationButton).click().perform();
        return this;
    }

    public WindowOpenVote clickOpenVoteButton() {
        actions.moveToElement(openVoteButton).click().perform();
        return new WindowOpenVote(webDriver);
    }

    public WindowOpenResultsVote clickOpenResultsVoteButton() {
        actions.moveToElement(voteResultButton).click().perform();
        return new WindowOpenResultsVote(webDriver);
    }

    public WindowCopyQuestion clickCopyVoteButton() {
        actions.moveToElement(copyQuestionButton).click().perform();
        return new WindowCopyQuestion(webDriver);
    }

    public WindowDeferralQuestion clickMoveQuestionButton() {
        actions.moveToElement(moveQuestionButton).click().perform();
        return new WindowDeferralQuestion(webDriver);
    }

    public AttentionWindow clickDeleteQuestionButton() {
        actions.moveToElement(deleteQuestionButton).click().perform();
        return new AttentionWindow(webDriver);
    }

    public QuestionList clickHandVoteButton() {
        actions.moveToElement(handVoteButton).click().perform();
        return new QuestionList(webDriver);
    }

    public QuestionList clickWorkVoteButton() {
        actions.moveToElement(workVoteButton).click().perform();
        return new QuestionList(webDriver);
    }

    public QuestionList clickAnonymVoteButton() {
        actions.moveToElement(anonymVoteButton).click().perform();
        return new QuestionList(webDriver);
    }

    public QuestionList clickAnonymWorkVoteButton() {
        actions.moveToElement(anonymWorkVoteButton).click().perform();
        return new QuestionList(webDriver);
    }

    public String getSubjectQuestionText() {
        return subjectQuestionField.getText();
    }

    public void typeSubjectQuestionText(String subject) {
        subjectQuestionField.sendKeys(subject);
    }

    public SelectedQuestion clickClearSubjectButton() {
        actions.moveToElement(clearSubjectButton).click().perform();
        return this;
    }

    public void typeFioAttestedField(String name) {
        fioAttestedField.sendKeys(name);
    }

    public String getFioAttestedText() {
        return fioAttestedField.getText();
    }

    public WebElement clickFioAttestedList() {
        fioAttestedList.iterator().next().click();
        return fioAttestedField;
    }

    public SelectedQuestion clickFioAttestedClearButton() {
        actions.moveToElement(positionFioClearButton).click().perform();
        return this;
    }

    public void typePositionAttestedField(String position) {
        positionAttestedField.sendKeys(position);
    }

    public String getPositionAttestedText() {
        return positionAttestedField.getText();
    }

    public WebElement clickPositionAttestedClearButton() {
        actions.moveToElement(positionClearButton).click().perform();
        return positionAttestedField;
    }

    public void typeRepporteurFIOField(String name) {
        repporteurFIOField.sendKeys(name);
    }

    public String getRepporteurFIOText() {
        return repporteurFIOField.getText();
    }

    public WebElement clickRepporteurFIO() {
        repporteurFIOList.iterator().next().click();
        return repporteurFIOField;
    }

    public List<WebElement> clickAddRepporteur() {
        actions.moveToElement(addRepporteurButton).click().perform();
        return addedRepporteurList;
    }

    public SelectedQuestion clickAddedRepporteurList() {
        addedRepporteurList.iterator().next().click();
        return this;
    }

    public String getAddedRepporteurList() {
        return addedRepporteurList.iterator().next().getText();
    }

    public SelectedQuestion clickDeleteRepporteur() {
        actions.moveToElement(deleteRepporteurButton).click().perform();
        return this;
    }

    public String getCoRepporteurText() {
        return coRepporteurFIOField.getText();
    }

    public void typeCoRepporteurFio(String name) {
        coRepporteurFIOField.sendKeys(name);
    }

    public WebElement clickCoReppoteurFioList() {
        coRepporteurFIOList.iterator().next().click();
        return coRepporteurFIOField;
    }

    public List<WebElement> clickCoRepporteurAddButton() {
        actions.moveToElement(addCoRepporteurButton).click().perform();
        return addedCoRepporteurList;
    }

    public SelectedQuestion clickAddedCoRepporteur() {
        addedCoRepporteurList.iterator().next().click();
        return this;
    }

    public String getAddedCoRepporteurText() {
        return addedCoRepporteurList.iterator().next().getText();
    }

    public SelectedQuestion clickDeleteAddedCoRepporteur() {
        actions.moveToElement(deleteCoRreporteurButton).click().perform();
        return this;
    }

    public String getInvitedFIOFieldText() {
        return invitedFIOField.getText();
    }

    public void typeInvitedFIOField(String name) {
        invitedFIOField.sendKeys(name);
    }

    public WebElement clickIinvitedFIOList() {
        invitedFIOList.iterator().next().click();
        return invitedFIOField;
    }

    public List<WebElement> clickInvitedAddButton() {
        actions.moveToElement(addInvitedButton).click().perform();
        return addedInvitedList;
    }

    public SelectedQuestion clickAddedIvited() {
        addedInvitedList.iterator().next().click();
        return this;
    }

    public String getAddedInvitedText() {
        return addedInvitedList.iterator().next().getText();
    }

    public SelectedQuestion clickDeleteAddedInvited() {
        actions.moveToElement(deleteInvitedButton).click().perform();
        return this;
    }

    public String getActedFIOFieldText() {
        return actedFIOField.getText();
    }

    public void typeActedFIOField(String name) {
        actedFIOField.sendKeys(name);
    }

    public WebElement clickActedFIOList() {
        actedFIOList.iterator().next().click();
        return invitedFIOField;
    }

    public List<WebElement> clickActedAddButton() {
        actions.moveToElement(addActedButton).click().perform();
        return addedActedList;
    }

    public SelectedQuestion clickAddedActed() {
        addedActedList.iterator().next().click();
        return this;
    }

    public String getAddedActedText() {
        return addedActedList.iterator().next().getText();
    }

    public SelectedQuestion clickDeleteAddedActed() {
        actions.moveToElement(deleteActedButton).click().perform();
        return this;
    }

    public void clickChoseFileMaterials(String file) {
        addFileMaterials.sendKeys(file);
    }

    public String getTextPathChosenMaterials() {
        return fileMaterialsField.getText();
    }

    public void typeNameFileMaterials(String name) {
        nameFileMaterials.sendKeys(name);
    }

    public String getNameFileMaterials() {
        return nameFileMaterials.getText();
    }

    public List<WebElement> clickAddDocumentButton() {
        actions.moveToElement(addDocumentsButton).click().perform();
        return addedMaterialsList;
    }

    public SelectedQuestion clickAddedMaterialsList() {
        addedMaterialsList.iterator().next().click();
        return this;
    }

    public SelectedQuestion clickDeleteMaterialsButton() {
        actions.moveToElement(deleteDocumentButton).click().perform();
        return this;
    }

    public void typeListenText(String text) {
        listenTextField.sendKeys(text);
    }

    public String getListenText() {
        return listenTextField.getText();
    }

    public void typeDecideText(String text) {
        decideTextField.sendKeys(text);
    }

    public String getDecideText() {
        return decideTextField.getText();
    }

    public String getHistoryQuestionText() {
        return historyField.getText();
    }

    public void typeHistoryQuestionText(String text) {
        historyField.sendKeys(text);
    }

    public SelectedQuestion clickClearHistoryButton() {
        actions.moveToElement(clearHistoryButton).click().perform();
        return this;
    }

    public WindowCreateLinkBetweenQuestions clickAddLinkButton() {
        actions.moveToElement(addLinkButton).click().perform();
        return new WindowCreateLinkBetweenQuestions(webDriver);
    }

    public SelectedQuestion clickCheckboxLink() {
        checkboxLinkQuestion.iterator().next().click();
        return this;
    }

    public String getSelectQuestionText() {
        return linkQuestions.iterator().next().getText();
    }

    public WindowViewQuestionLink clickQuestionLink() {
        linkQuestions.iterator().next().click();
        return new WindowViewQuestionLink(webDriver);
    }

    public SelectedQuestion clickDeleteLinkButton() {
        actions.moveToElement(deleteLinkButton).click().perform();
        return this;
    }

    public String getHeaderQuestionListText() {
        return headerQuestionList.getText();
    }


}
