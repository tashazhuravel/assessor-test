package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.window.WindowMaximizedInformationTable;
import pages.sittingPage.CurrentMeettingPage;

import java.util.List;

public class InformationTablePage {

    private WebDriver webDriver;

    private Actions actions;

    @FindBy(xpath = "//div[@id='infoData']/div/span")
    private WebElement headerInformationTable;

    @FindBy(xpath = "//table[@id='planningControlManagmentButtonInfoClose']//button")
    private WebElement backToQuestionListButton;

    @FindBy(xpath = "//table[@id='planningControlManagmentButtonInfoMaximize']//button")
    private WebElement fullScreenModeButton;

    @FindBy(xpath = "//table[@id='planningControlManagmentButtonInfoZoomIn']//button")
    private WebElement increaseFontSizeButton;

    @FindBy(xpath = "//table[@id='planningControlManagmentButtonInfoZoomOut']//button")
    private WebElement decreaseFontSizeButton;

    @FindBy(xpath = "(//div[@id='textcontent']//p)[3]")
    private List<WebElement> textContent;

    @FindBy(xpath = "//div[@id='textcontent']")
    private WebElement textContentTransformSize;

    @FindBy(xpath = "(//div[@id='textcontent']//td)[2]")
    private WebElement textSubjectQuestion;


    public InformationTablePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        actions = new Actions(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public CurrentMeettingPage clickBackToQuestionListButton() {
        actions.moveToElement(backToQuestionListButton).click().perform();
        return new CurrentMeettingPage(webDriver);
    }

    public WindowMaximizedInformationTable clickFullScreenModeButton() {
        actions.moveToElement(fullScreenModeButton).click().perform();
        return new WindowMaximizedInformationTable(webDriver);
    }

    public InformationTablePage clickIncreaseFontSizeButton() {
        actions.moveToElement(increaseFontSizeButton).click().perform();
        return this;
    }
    //TODO после клика +/- появляется атрибут style = "transform: scale(1.4, 1.4);" scale изменяется

    public InformationTablePage clickDecreaseFontSizeButton() {
        actions.moveToElement(decreaseFontSizeButton).click().perform();
        return this;
    }

    public WebElement getHeaderInformationTable() {
        return headerInformationTable;
    }

    public String getHeaderText() {
        return headerInformationTable.getText();
    }

    public String textFromTextContent() {
        return textContent.iterator().next().getText();
    }

    public List<WebElement> getTextContent() {
        return textContent;
    }

    public String getSubjectQuestion() {
        return textSubjectQuestion.getText();
    }

    public String getTransformTextSize() {
        return textContentTransformSize.getCssValue("transform");
    }


}
