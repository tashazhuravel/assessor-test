package pages.window;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.errorWindow.ErrorWindow;
import pages.mainPageTab.PlanningTabPage;
import pages.sittingPage.CurrentMeettingPage;

import java.util.List;

public class WindowSittingPlanning {

    private WebDriver webDriver;

    private Actions actions;

    private By headerWindowWettingScheduling = By.xpath("//span[text()='Планирование заседания']");

    @FindBy(xpath = "//input[@id='number_sittings']")
    private WebElement inputSittingNumber;

    @FindBy(xpath = "//input[@name='place_id']")
    private WebElement inputSittingPlace;

    @FindBy(xpath = "//img[@src='extensions/is.assessor/externallib/extjs/resources/images/default/s.gif']")
    private WebElement inputPlanningPlaceDropDown;

    @FindBy(xpath = "(//div[@class = 'x-layer x-combo-list '])[1]/div[@class='x-combo-list-inner']/div[text()]")
    private List<WebElement> selectPlanningPlace;

    @FindBy(xpath = "//input[@name='city']")
    private WebElement cityField;

    @FindBy(xpath = "//input[@id='helper_planningControlAttributesDate']")
    private WebElement dateField;

    @FindBy(xpath = "(//img[@src='extensions/is.assessor/externallib/extjs/resources/images/default/s.gif'])[2]")
    private WebElement calendarButton;

    @FindBy(xpath = "//table[@class='x-date-inner']/tbody/tr/td/a")
    private List<WebElement> calendarTable;

    @FindBy(xpath = "//button[text()='Сегодня']")
    private WebElement todayButton;

    @FindBy(xpath = "//input[@id='sittingTimeStart']")
    private WebElement sittingTimeStartField;

    @FindBy(xpath = "(//img[@src='extensions/is.assessor/externallib/extjs/resources/images/default/s.gif'])[3]")
    private WebElement selectSittingTimeStartDropDown;

    @FindBy(xpath = "(//div[@class='x-layer x-combo-list '])[2]/div")
    private List<WebElement> selectSittingTimeStart;

    @FindBy(xpath = "//input[@id='sittingTimeEnd']")
    private WebElement sittingTimeEndField;

    @FindBy(xpath = "(//img[@src='extensions/is.assessor/externallib/extjs/resources/images/default/s.gif'])[4]")
    private WebElement sittingTimeEndDropDown;

    @FindBy(xpath = "(//div[@class='x-layer x-combo-list '])[3]/div")
    private List<WebElement> selectSittingTimeEnd;

    @FindBy(xpath = "//td[@class='x-grid3-col x-grid3-cell x-grid3-td-0 x-grid3-cell-first ']/div[text()]")
    private List<WebElement> participantsList;

    @FindBy(xpath = "//button[text()='Сохранить']")
    private WebElement buttonSave;

    @FindBy(xpath = "//button[text()='Отмена']")
    private WebElement cancelButton;

    @FindBy(xpath = "//div[@class='x-tool x-tool-close']")
    private WebElement closeButtonByX;

    public WindowSittingPlanning(WebDriver webDriver) {
        this.webDriver = webDriver;
        actions = new Actions(webDriver);
        PageFactory.initElements(webDriver, this);
    }



    //-----------------------проверки поля Номер

    public String getSittingNumberText() {
        return inputSittingNumber.getAttribute("value");
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
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return selectPlanningPlace;
    }

    public void setSelectPlanningPlace(String roomName) {
        actions.moveToElement(webDriver.findElement(By.xpath(String.format("//div[@class='x-combo-list-inner']//div[text()='%s']", roomName)))).click();
        try {
            Thread.sleep(500L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

//--------------------проверка поля Город

    public void typeCityField(String city) {
        cityField.sendKeys(city);
    }

    public String getCityFieldText() {
        return cityField.getText();
    }

//--------------------проверка  Дата и Время

    public void clearDateFieldText() {
        dateField.clear();
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getDateFieldText() {
        return dateField.getAttribute("value");
    }

    public void typeDate(String dateSitting) {
        dateField.sendKeys(dateSitting);
    }

    public WindowSittingPlanning clickCalendarButton() {
        actions.moveToElement(calendarButton).click().perform();
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }

    public boolean emptyCalendar() {
        return calendarTable.isEmpty();
    }

    public WindowSittingPlanning clickDateInCalendar() {
        calendarTable.iterator().next().click();
        return this;
    }

    public WindowSittingPlanning clickTodayButton() {
        actions.moveToElement(todayButton).click().perform();
        return this;
    }

//-------------------------Время Начала заседания

    public String getTimeStartText(){
        return sittingTimeStartField.getAttribute("value");
    }
    public void typeTimeStart(String time) {
        sittingTimeStartField.sendKeys(time);
    }

    public List<WebElement> clickAndOpenDropDownSelectSittingTimeStart() {
        actions.moveToElement(selectSittingTimeStartDropDown).click().perform();
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return selectSittingTimeStart;
    }

    public WindowSittingPlanning clickTimeStartInDropDown(){
        selectSittingTimeStart.iterator().next().click();
        return this;
    }

    //----------------------------Время Окончания заседания
    public String getTimeEndText(){
        return sittingTimeEndField.getAttribute("value");
    }

    public List<WebElement> clickAndOpenSelectSittingTimeEnd() {
        actions.moveToElement(sittingTimeEndDropDown).click().perform();
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return selectSittingTimeEnd;
    }
    public WindowSittingPlanning clickTimeEndInDropDown(){
        selectSittingTimeEnd.iterator().next().click();
        return this;
    }

    public void typeTimeEnd(String time) {
        sittingTimeEndField.sendKeys(time);
    }

    //----------------Список участников


    public List<WebElement> getParticipantsList() {
        return participantsList;
    }

    public CurrentMeettingPage clickSaveButtonPlanning() {
        buttonSave.click();
        return new CurrentMeettingPage(webDriver);
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

    public ErrorWindow getErrorByMeetingScheduling() {
        return new ErrorWindow(webDriver);
    }
}
