package pages.window;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.CurrentMeetingPage;

import java.util.List;

public class WindowAddUnllocatedQuestion {

    private WebDriver webDriver;

    private Actions actions;

    private By headerWindowAddUnllocatedQuestion = By.xpath("//span[text()='Добавление вопросов из нераспределённых']");

    private By addButtonDisabled = By.xpath("//table[@class='x-btn x-btn-noicon x-item-disabled']//button");

    @FindBy(xpath = "//div[@class='x-grid3-hd-inner x-grid3-hd-checker']/a")
    private WebElement checkboxSelectAllUnllocatedQuestion;

    @FindBy(xpath = "//div[@class='x-grid3-cell-inner x-grid3-col-checker']")
    private List<WebElement> checkboxSelectUnllocatedQuestion;

    @FindBy(xpath = "//div[@class='x-grid3-cell-inner x-grid3-col-subject']")
    private List<WebElement> listSubjectQuestion;

    @FindBy(xpath = "//table[@class='x-btn x-btn-noicon']//button")
    private WebElement addButton;

    @FindBy(xpath = "//button[text()='Закрыть']")
    private WebElement closeButton;


    public WindowAddUnllocatedQuestion(WebDriver webDriver) {
        this.webDriver = webDriver;
        actions = new Actions(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public WindowAddUnllocatedQuestion clikcCheckBoxSelectAllUnllocatedQuestion() {
        actions.moveToElement(checkboxSelectAllUnllocatedQuestion).click().perform();
        return new WindowAddUnllocatedQuestion(webDriver);
    }

    public WindowAddUnllocatedQuestion clickSelectUnllocatedQuestion(){
        checkboxSelectUnllocatedQuestion.iterator().next().click();
        return new WindowAddUnllocatedQuestion(webDriver);
    }

    public String getSubjectQuestionText(){
       return listSubjectQuestion.iterator().next().getText();
    }

    public CurrentMeetingPage clickAddButton(){
        actions.moveToElement(addButton).click().perform();
        return new CurrentMeetingPage(webDriver);
    }

    public CurrentMeetingPage clickCloseButton(){
        actions.moveToElement(closeButton).click().perform();
        return new CurrentMeetingPage(webDriver);
    }



    public By getHeaderWindowAddUnllocatedQuestion() {
        return headerWindowAddUnllocatedQuestion;
    }

    public By getAddButtonDisabled() {
        return addButtonDisabled;
    }
}
