package pages.window;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class WindowEditMeetingRequisites {

    private WebDriver webDriver;
    private Actions actions;

    @FindBy(xpath = "//div[@id='winPlanningControlAttributesButton']//div[@class='x-tool x-tool-close']")
    private WebElement closeByXButton;

    @FindBy(xpath = "//input[@id='planningControlAttributesCommittee']")
    private WebElement nameCommitteeField;

    @FindBy(xpath = "//input[@id='planningControlAttributesNum']")
    private WebElement numberSittingField;

    @FindBy(xpath = "//input[@name='time']")
    private WebElement startTimeField;

    @FindBy(xpath = "(//div[@id='winPlanningControlAttributesButton']//img)[1]")
    private WebElement startTimeDropDownButton;

    @FindBy(xpath = "(//div[@class='x-combo-list-inner'])[1]/div")
    private List<WebElement> startTimeDropDown;

    @FindBy(xpath = "//input[@name='timeEnd']")
    private WebElement endTimeField;

    @FindBy(xpath = "(//div[@id='winPlanningControlAttributesButton']//img)[2]")
    private WebElement endTimeDropDownButton;

    @FindBy(xpath = "((//div[@class='x-combo-list-inner'])[2]/div")
    private List<WebElement> endTimeDropDown;

    @FindBy(xpath = "//input[@id='planningControlAttributesDate']")
    private WebElement sittingDateField;

    @FindBy(xpath = "(//div[@id='winPlanningControlAttributesButton']//img)[3]")
    private WebElement calendarButton;

    @FindBy(xpath = "//td[@class='x-date-bottom']//button")
    private WebElement calendarTodayButton;

    @FindBy(xpath = "//table[@id='helperButton_planningControlAttributesDate']//button")
    private WebElement watchPlanningSittingButton;

    @FindBy(xpath = "//input[@id='sitting_place']")
    private WebElement sittingPlaceField;

    @FindBy(xpath = "(//div[@id='winPlanningControlAttributesButton']//img)[4]")
    private WebElement sittingPlaceDropDownButton;

    @FindBy(xpath = "(//div[@class='x-combo-list-inner'])[3]/div")
    private WebElement sittingPlaceDropDown;

    @FindBy(xpath = "//input[@id='city_sittings']")
    private WebElement cityField;

    @FindBy(xpath = "//div[@id='checkboxgroup']//div[@class='x-panel-body']/div")
    private List<WebElement> participantList;

    @FindBy(xpath = "//input[@name='presiding']")
    private WebElement presidingField;

    @FindBy(xpath = "(//div[@id='winPlanningControlAttributesButton']//img)[5]")
    private WebElement presidingDropDownButton;

    @FindBy(xpath = "(//div[@class='x-combo-list-inner'])[4]/div")
    private List<WebElement> presidingDropDown;

    @FindBy(xpath = "//textarea[@name='invited']")
    private WebElement presentField;

    @FindBy(xpath = "//input[@name='public']")
    private WebElement publicCheckbox;

    @FindBy(xpath = "//input[@id='formVoteSitting']")
    private WebElement formVoteField;

    @FindBy(xpath = "(//div[@id='winPlanningControlAttributesButton']//img)[6]")
    private WebElement formVoteDropDownButton;

    @FindBy(xpath = "(//div[@class='x-combo-list-inner'])[5]/div")
    private List<WebElement> formVoteDropDown;

    @FindBy(xpath = "//table[@id='btnAttributesSave']//button")
    private WebElement saveButton;

    @FindBy(xpath = "(//div[@id='winPlanningControlAttributesButton']//button)[3]")
    private WebElement cancelButton;

    private By headerWindow = By.xpath("//div[@id='winPlanningControlAttributesButton']//span");



    public WindowEditMeetingRequisites(WebDriver webDriver){
        this.webDriver = webDriver;
        actions = new Actions(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public String getNameCommitteeText(){ return nameCommitteeField.getText();}

    public String getNumberSittingText(){return numberSittingField.getText();}

    public void inputNumberSitting(String number){ numberSittingField.sendKeys(number);}

    public String getStartTimeText(){return startTimeField.getText();}


}
