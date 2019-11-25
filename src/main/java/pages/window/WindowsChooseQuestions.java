package pages.window;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.ProtocolPage;
import pages.StatementPage;

import java.util.List;

public class WindowsChooseQuestions {

    private WebDriver webDriver;
    private Actions actions;

    private By headerChooseQuestions = By.xpath("//div[@id='registerWindow']//span");

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

    public WindowsChooseQuestions(WebDriver webDriver){
        this.webDriver = webDriver;
        actions = new Actions(webDriver);
        PageFactory.initElements(webDriver,this);
    }

    public WindowsChooseQuestions clickCheckboxSelectQuestion(){
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

    public By getHeaderChooseQuestions() {
        return headerChooseQuestions;
    }
}
