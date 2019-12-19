package pages.window;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.sittingPage.CurrentMeetingPage;

import java.util.List;

public class WindowOpenGroupVote {

    private WebDriver webDriver;
    private Actions actions;

    private By headerVoteGroup = By.xpath("//div[@id='vote-window-bulk']//span");

    @FindBy(xpath = "//div[@id='formBulkQuestions']//div[@class='x-form-item x-hide-label x-grid3-header']//input")
    private WebElement checkboxSelectAll;

    @FindBy(xpath = "//div[@id='myGroup']//input")
    private List<WebElement> checkboxSelectQuestion;

    @FindBy(xpath = "//div[@id='myGroup']/div/div/div/label")
    private List<WebElement> questionText;

    @FindBy(xpath = "(//div[@id='planningVoteButtons']//button)[1]")
    private WebElement forVoteButton;

    @FindBy(xpath = "(//div[@id='planningVoteButtons']//button)[2]")
    private WebElement againstVoteButton;

    @FindBy(xpath = "(//div[@id='planningVoteButtons']//button)[3]")
    private WebElement abstainedVoteButton;

    @FindBy(xpath = "//div[@id='planningVoteButtons']//button")
    private List<WebElement> kindOfVote;

    @FindBy(xpath = "//textarea[@id='planningVoteComment']")
    private WebElement commentField;

    @FindBy(xpath = "(//div[@id='vote-window-bulk']//div[@class='x-window-bl']//button)[1]")
    private WebElement okButton;

    @FindBy(xpath = "(//div[@id='vote-window-bulk']//div[@class='x-window-bl']//button)[2]")
    private WebElement closeButton;

    public WindowOpenGroupVote(WebDriver webDriver) {
        this.webDriver = webDriver;
        actions = new Actions(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public WindowOpenGroupVote clickCheckboxSelectAll() {
        actions.moveToElement(checkboxSelectAll).click().perform();
        return this;
    }

    public WindowOpenGroupVote clickCheckboxSelectQuestion() {
        checkboxSelectQuestion.iterator().next().click();
        return this;
    }

    public String getQuestionText() {
        return questionText.iterator().next().getText();
    }

    public WindowOpenGroupVote clickForVoteButton() {
        actions.moveToElement(forVoteButton).click().perform(); //todo attribute pressed
        return this;
    }

    public WindowOpenGroupVote clickAgainstVoteButton() {
        actions.moveToElement(againstVoteButton).click().perform();
        return this;
    }

    public WindowOpenGroupVote clickAbstainedVoteButton() {
        actions.moveToElement(abstainedVoteButton).click().perform();
        return this;
    }

    public void setTextCommentField(String comment) {
        commentField.sendKeys(comment);
    }

    public String getTextCommentField() {
        return commentField.getText();
    }

    public CurrentMeetingPage clickOkButton() {
        actions.moveToElement(okButton).click().perform();
        return new CurrentMeetingPage(webDriver);
    }

    public CurrentMeetingPage clickCloseButton() {
        actions.moveToElement(closeButton).click().perform();
        return new CurrentMeetingPage(webDriver);
    }

    public By getHeaderVoteGroup() {
        return headerVoteGroup;
    }

    public List<WebElement> getKindOfVote() {
        return kindOfVote;
    }


}
