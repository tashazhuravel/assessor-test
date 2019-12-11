package sittingPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class QuestionList {

    private WebDriver webDriver;

    private Actions actions;

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


    public QuestionList(WebDriver webDriver){
        this.webDriver = webDriver;
        actions = new Actions(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public QuestionList clickHideQuestionListButton(){
        actions.moveToElement(hideQuestionListButton).click().perform();
        return this;
    }

    public String getQuestionListText(){return questionListText.iterator().next().getText();}

    public QuestionList selectQuestionList(){
        questionList.iterator().next().click();
        return this;
    }

    public String getStatusExamine(){
        return questionStatusExamine.getCssValue("title");
    }

    public WebElement getHeaderQuestionList() {
        return headerQuestionList;
    }

    public List<WebElement> getQuestionStatusVotingWork() {
        return questionStatusVotingWork;
    }

    public List<WebElement> getQuestionStatusVotingInternal() {
        return questionStatusVotingInternal;
    }

    public List<WebElement> getQuestionStatusVotingAnonym() {
        return questionStatusVotingAnonym;
    }

    public List<WebElement> getQuestionStatusVotingAnonymWork() {
        return questionStatusVotingAnonymWork;
    }

    public List<WebElement> getQuestionStatusVoted() {
        return questionStatusVoted;
    }

    public List<WebElement> getQuestionStatusAllVoted() {
        return questionStatusAllVoted;
    }

    public WebElement getQuestionStatusExamine() {
        return questionStatusExamine;
    }
}
