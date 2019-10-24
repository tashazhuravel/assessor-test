package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import javax.xml.xpath.XPath;
import java.util.List;

public class WindowMeetingScheduling {
    protected WebDriver webDriver;

    @FindBy(xpath = "//input[@id='number_sittings']")
    WebElement inputSittingNumber;

    @FindBy(xpath = "//div[@class = 'x-combo-list-inner']/div")
    List<WebElement> selectPlanningPlace;

    @FindBy(xpath = "//img[@src='extensions/is.assessor/externallib/extjs/resources/images/default/s.gif']")
    WebElement inputPlanningPlaceDropDown;

    @FindBy(xpath = "//input[@name='city']")
    WebElement cityField;

    @FindBy(xpath = "//input[@id='helper_planningControlAttributesDate']")
    WebElement dateField;

    @FindBy(xpath = "(//img[@src='extensions/is.assessor/externallib/extjs/resources/images/default/s.gif'])[2]")
    WebElement calendarButton;

    @FindBy(xpath = "//div[@class='x-date-picker x-unselectable']")
    WebElement calendarTable;

    @FindBy(xpath = "//button[text()='Сегодня']")
    WebElement todayButton;

    @FindBy(xpath = "//input[@id='sittingTimeStart']")
    List<WebElement> sittingTimeStartField;

    @FindBy(xpath = "(//img[@src='extensions/is.assessor/externallib/extjs/resources/images/default/s.gif'])[3]")
    WebElement selectSittingTimeStartDropDown;

    @FindBy(xpath = "//div[@class='x-layer x-combo-list ']/div")
    WebElement selectSittingTimeStart;

    @FindBy(xpath = "//input[@id='sittingTimeEnd']")
    WebElement sittingTimeEndField;

    @FindBy(xpath = "(//img[@src='extensions/is.assessor/externallib/extjs/resources/images/default/s.gif'])[4]")
    WebElement sittingTimeEndDropDown;

    @FindBy(xpath = "(//div[@class='x-layer x-combo-list '])[2]/div")
    List<WebElement> selectSittingTimeEnd;

    @FindBy(xpath = "//div[@class='x-grid3-body']")
    List<WebElement> participantsList;

    @FindBy(xpath = "//button[text()='Сохранить']")
    WebElement buttonSave;

    @FindBy (xpath = "//button[text()='Отмена']")
    WebElement cancelButton;

    @FindBy(xpath = "//div[@class='x-tool x-tool-close']")
    WebElement closeButton;

    @FindBy(xpath = "(//div[@class='x-window x-window-plain x-window-dlg'])[2]//span")
    List<WebElement> errorMessage;

    @FindBy(xpath = "(//table[@class='x-btn x-btn-noicon'])[3]//button")
    WebElement errorOkButton;

    @FindBy(xpath = "//div[@class=' x-window x-window-plain x-window-dlg']//div[@class='x-tool x-tool-close']")
    WebElement errorCloseButton;




    public WindowMeetingScheduling(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public List<WebElement> getSelectPlanningPlace() {
        Actions action = new Actions(webDriver);
        action.moveToElement(inputPlanningPlaceDropDown).click().perform();
        return selectPlanningPlace;
    }
    public void getSittingNumber(){

    }


    public void savePlanning() {
        buttonSave.click();
    }
}
