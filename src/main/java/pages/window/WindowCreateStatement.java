package pages.window;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.ProtocolPage;
import pages.StatementPage;
import pages.errorWindow.ErrorWindow;

import java.util.List;

public class WindowCreateStatement {

    private WebDriver webDriver;

    private Actions actions;

    private By headerChooseQuestions = By.xpath("//div[@id='registerWindow']//form//div[text()]");

    @FindBy(xpath = "//div[@id='radiogroup']//input")
    private List<WebElement> checkboxSelectQuestion;

    @FindBy(xpath = "//div[@id='radiogroup']//label")
    private List<WebElement> questionText;

    @FindBy(xpath = "(//div[@id='registerWindow']//button)[1]")
    private WebElement createButton;

    @FindBy(xpath = "(//div[@id='registerWindow']//button']//button)[2]")
    private WebElement cancelButton;

    @FindBy(xpath = "//div[@id='registerWindow']//div[@class='x-tool x-tool-close']")
    private WebElement closeByXButton;

    public WindowCreateStatement(WebDriver webDriver){
        this.webDriver = webDriver;
        actions = new Actions(webDriver);
        PageFactory.initElements(webDriver,this);
    }

    public WindowCreateStatement clickCheckboxSelectQuestion(){
        checkboxSelectQuestion.iterator().next().click();
        return this;
    }

    public String getQuestionText(){
        return questionText.iterator().next().getText();
    }

    public StatementPage clickCreateButton(){
        actions.moveToElement(createButton).click().perform();
        return new StatementPage(webDriver);
    }

    public ProtocolPage clickCancelButton(){
        actions.moveToElement(cancelButton).click().perform();
        return new ProtocolPage(webDriver);
    }

    public ProtocolPage clickCloseByXButton(){
        actions.moveToElement(cancelButton).click().perform();
        return new ProtocolPage(webDriver);
    }

    public List<WebElement> getCheckboxSelectQuestion() {
        return checkboxSelectQuestion;
    }

    public By getHeaderChooseQuestions() {
        return headerChooseQuestions;
    }
}
