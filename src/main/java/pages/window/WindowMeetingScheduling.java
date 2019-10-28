package pages.window;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.CurrentMeetingPage;
import pages.errorWindow.ErrorByMeetingScheduling;
import pages.mainPageTab.PlanningTabPage;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class WindowMeetingScheduling {

    private WebDriver webDriver;

    private Actions actions;

    private By headerWindowWettingScheduling = By.xpath("//span[text()='Планирование заседания']");

    @FindBy(xpath = "//input[@id='number_sittings']")
    private WebElement inputSittingNumber;

    @FindBy(xpath = "//input[@name='place_id']")
    private WebElement inputSittingPlace;

    @FindBy(xpath = "//img[@src='extensions/is.assessor/externallib/extjs/resources/images/default/s.gif']")
    private WebElement inputPlanningPlaceDropDown;

    @FindBy(xpath = "//div[@class = 'x-combo-list-inner']/div")
    private List<WebElement> selectPlanningPlace;

    @FindBy(xpath = "//input[@name='city']")
    private WebElement cityField;

    @FindBy(xpath = "//input[@id='helper_planningControlAttributesDate']")
    private WebElement dateField;

    @FindBy(xpath = "(//img[@src='extensions/is.assessor/externallib/extjs/resources/images/default/s.gif'])[2]")
    private WebElement calendarButton;

    @FindBy(xpath = "//table[@class='x-date-inner']/tbody/tr/td")
    private List<WebElement> calendarTable;

    @FindBy(xpath = "//button[text()='Сегодня']")
    private WebElement todayButton;

    @FindBy(xpath = "//input[@id='sittingTimeStart']")
    private WebElement sittingTimeStartField;

    @FindBy(xpath = "(//img[@src='extensions/is.assessor/externallib/extjs/resources/images/default/s.gif'])[3]")
    private WebElement selectSittingTimeStartDropDown;

    @FindBy(xpath = "//div[@class='x-layer x-combo-list ']/div")
    private List<WebElement> selectSittingTimeStart;

    @FindBy(xpath = "//input[@id='sittingTimeEnd']")
    private WebElement sittingTimeEndField;

    @FindBy(xpath = "(//img[@src='extensions/is.assessor/externallib/extjs/resources/images/default/s.gif'])[4]")
    private WebElement sittingTimeEndDropDown;

    @FindBy(xpath = "(//div[@class='x-layer x-combo-list '])[2]/div")
    private List<WebElement> selectSittingTimeEnd;

    @FindBy(xpath = "//div[@class='x-grid3-body']")
    private List<WebElement> participantsList;

    @FindBy(xpath = "//button[text()='Сохранить']")
    private WebElement buttonSave;

    @FindBy(xpath = "//button[text()='Отмена']")
    private WebElement cancelButton;

    @FindBy(xpath = "//div[@class='x-tool x-tool-close']")
    private WebElement closeButtonByX;

    public WindowMeetingScheduling(WebDriver webDriver) {
        this.webDriver = webDriver;
        actions = new Actions(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public String getDateAsString() {
        Date date = new Date();
        return new SimpleDateFormat("dd.MM.yyyy").format(date);
    }

    //-----------------------проверки поля Номер

    public String getSittingNumberText() {
        return inputSittingNumber.getText();
    }

    public void setSittingNumber(String number) {
        inputSittingNumber.sendKeys(number);

    }

    public void typeSittingNumber(String number) {
        inputSittingNumber.sendKeys(number);
    }

    //-----------------------проверки поля Место заседания

    public String getSittingPlaceText() {
        return inputSittingPlace.getAttribute("value");
    }

    public void typeSittingPlace(String place) {
        inputSittingPlace.sendKeys(place);
    }

    public List<WebElement> clickAndOpenSelectDropDownPlanningPlace() {
        actions.moveToElement(inputPlanningPlaceDropDown).click().perform();
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return selectPlanningPlace;
    }

    public void setSelectPlanningPlace(String id){
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript(String.format("document.getElementByName('place_id').setAttribute('value', %s)", id));
    }

//--------------------проверка поля Город

    public void typeCityField(String city) {
        cityField.sendKeys(city);
    }

    public String getCityFieldText() {
        return cityField.getText();
    }

//--------------------проверка  Дата и Время

    public void typeDate(String dateSitting) {
        dateField.sendKeys(dateSitting);
    }

    public WindowMeetingScheduling clickCalendarButton() {
        actions.moveToElement(calendarButton).click().perform();
        return this;
    }

    public boolean emptyCalendar() {
        return calendarTable.isEmpty();
    }

    public WindowMeetingScheduling clickTodayButton() {
        actions.moveToElement(todayButton).click().perform();
        return this;
    }

//-------------------------Время Начала заседания

    public void typeTimeStart(String time) {
        sittingTimeStartField.sendKeys(time);
    }

    public List<WebElement> openSelectSittingTimeStart() {
        actions.moveToElement(selectSittingTimeStartDropDown).click().perform();
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return selectSittingTimeStart;
    }

    //----------------------------Время Окончания заседания

    public List<WebElement> openSelectSittingTimeEnd() {
        actions.moveToElement(sittingTimeEndDropDown).click().perform();
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return selectSittingTimeEnd;
    }

    public void typeTimeEnd(String time) {
        sittingTimeEndField.sendKeys(time);
    }

    //----------------Список участников

    public void getParticipantList() {
        for (WebElement participant : participantsList) {
            participant.getText();
        }
    }

    public CurrentMeetingPage savePlanning() {
        buttonSave.click();
        return new CurrentMeetingPage(webDriver);
    }

    public PlanningTabPage clickCancelButtonPlanningSitting() {
        actions.moveToElement(cancelButton).click().perform();
        return new PlanningTabPage(webDriver);
    }

    public PlanningTabPage clickCloseButtonPlanningSitting() {
        actions.moveToElement(closeButtonByX).click().perform();
        return new PlanningTabPage(webDriver);
    }

    public By getHeaderWindowWettingScheduling() {
        return headerWindowWettingScheduling;
    }

    public ErrorByMeetingScheduling getErrorByMeetingScheduling() {
        return new ErrorByMeetingScheduling(webDriver);
    }
}
