package pages.window;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.sittingPage.CurrentMeettingPage;

import java.util.List;

public class WindowKindOfQuestion {

    private WebDriver webDriver;

    private Actions actions;

    @FindBy (xpath = "//div[@id='popupWindowCreateQuestion']//span")
    private WebElement header;

    @FindBy (xpath = "//div[@id='questionsTypesChooseListId']//div[@class='x-grid3-row x-grid3-row-first x-grid3-row-selected']")
    private WebElement selectedQuestion;

    @FindBy(xpath = "//div[@class='x-grid3-row x-grid3-row-alt']")
    private List<WebElement> allUnselectedQuestion;

    @FindBy(xpath = "//button[text()='Создать']")
    private WebElement createButton;

    @FindBy(xpath = "//button[text()='Отмена']")
    private WebElement cancelButton;
//форма Вид вопроса, которая открывается принажать добавить новый вопрос

    public WindowKindOfQuestion(WebDriver webDriver) {
        this.webDriver = webDriver;
        actions = new Actions(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public WindowKindOfQuestion clickSelectKindOfQuestion(){
        actions.moveToElement(selectedQuestion).click().perform();
        return new WindowKindOfQuestion(webDriver);
    }

    public String getTextSelectKindOfQuestion(){
        return selectedQuestion.getText();
    }

    public WindowKindOfQuestion clickUnSelectKindOfQuestion(){
        allUnselectedQuestion.iterator().next().click();
        return new WindowKindOfQuestion(webDriver);
    }

    public CurrentMeettingPage clickCreateButton(){
        actions.moveToElement(createButton).click().perform();
        return new CurrentMeettingPage(webDriver);
    }

    public CurrentMeettingPage clickCancelButton(){
        actions.moveToElement(cancelButton).click().perform();
        return new CurrentMeettingPage(webDriver);
    }

    public WebElement getHeader() {
        return header;
    }
}
