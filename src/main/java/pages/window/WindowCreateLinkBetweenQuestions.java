package pages.window;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import sittingPage.CurrentMeetingPage;

import java.util.List;

public class WindowCreateLinkBetweenQuestions {

    private WebDriver webDriver;
    private Actions actions;

    private By headerWindowCreateLink = By.xpath("//div[@class=' x-window']//span");

    @FindBy(xpath = "//input[@id='questionSearchTextField']")
    private WebElement searchQuestionField;

    @FindBy(xpath = "//div[@id='historyTable']//button")
    private WebElement searchButton;

    @FindBy(xpath = "//div[@id='historyTable']//div[@class='x-grid3-body']/div")
    private List<WebElement> questionTable;

    @FindBy(xpath = "//div[@id='historyTable']//div[@ class = 'x-grid3-cell-inner x-grid3-col-expander']")
    private List<WebElement> plusButton;

    @FindBy(xpath = "//div[@id='historyTable']//div[@ class = 'x-grid3-cell-inner x-grid3-col-subject']")
    private List<WebElement> subjectQuestion;

    @FindBy(xpath = "//table[@id='secretaryQuestionAddHistorySearchBtn']//button")
    private WebElement addLinkButton;

    @FindBy(xpath = "(//table[@class='x-btn x-btn-noicon']//button)[3]")
    private WebElement closeButton;


    public WindowCreateLinkBetweenQuestions(WebDriver webDriver) {
        this.webDriver = webDriver;
        actions = new Actions(webDriver);
        PageFactory.initElements(webDriver,this);
    }

    public String getSearchQuestionText(){ return searchQuestionField.getText();}

    public void typeTextQuestion(String subject){
        searchQuestionField.sendKeys(subject);
    }

    public WindowCreateLinkBetweenQuestions clickSearchButton(){
        actions.moveToElement(searchButton).click().perform();
        return this;
    }

    public WindowCreateLinkBetweenQuestions clickSelectedQueston(){
        questionTable.iterator().next().click();
        return this;
    }

    public WindowCreateLinkBetweenQuestions clickPlusButton(){
        plusButton.iterator().next().click();
        return this;
    }

    public String getSubjectQuestionText(){
        return subjectQuestion.iterator().next().getText();
    }

    public CurrentMeetingPage clickAddLinkButton(){
        actions.moveToElement(addLinkButton).click().perform();
        return new CurrentMeetingPage(webDriver);
    }

    public CurrentMeetingPage clickCloseButton(){
        actions.moveToElement(closeButton).click().perform();
        return new CurrentMeetingPage(webDriver);
    }
}
