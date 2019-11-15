package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.window.WindowMaximizedInformationTable;
import sittingPage.CurrentMeetingPage;

public class InformationTablePage {

    private WebDriver webDriver;

    private Actions actions;

    @FindBy(xpath = "//table[@id='planningControlManagmentButtonInfoClose']//button")
    private WebElement backToQuestionListButton;

    @FindBy(xpath = "//table[@id='planningControlManagmentButtonInfoMaximize']//button")
    private WebElement fullScreenModeButton;

    @FindBy(xpath = "//table[@id='planningControlManagmentButtonInfoZoomIn']//button")
    private WebElement increaseFontSizeButton;

    @FindBy(xpath = "//table[@id='planningControlManagmentButtonInfoZoomOut']//button")
    private WebElement decreaseFontSizeButton;

    @FindBy(xpath = "//div[@id='textcontent']")
    private WebElement textContent;

    private WebElement maximizedTextContent;

    private By headerInformationTable = By.xpath("//div[@id='infoData']/div/span");


    public InformationTablePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        actions = new Actions(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public CurrentMeetingPage clickBackToQuestionListButton() {
        actions.moveToElement(backToQuestionListButton).click().perform();
        return new CurrentMeetingPage(webDriver);
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

    public By getHeaderInformationTable() {
        return headerInformationTable;
    }

    public String getTextContent() {
        return textContent.getText();
    }


}
