package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import javax.xml.xpath.XPath;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class WindowMeetingScheduling {
    protected WebDriver webDriver;

    @FindBy(xpath = "//input[@id='number_sittings']")
    List<WebElement> inputSittingNumber;

    @FindBy(xpath = "//input[@id='helper_sitting_place']")
    List<WebElement> inputSittingPlace;

    @FindBy(xpath = "//div[@class = 'x-combo-list-inner']/div")
    List<WebElement> selectPlanningPlace;

    @FindBy(xpath = "//img[@src='extensions/is.assessor/externallib/extjs/resources/images/default/s.gif']")
    WebElement inputPlanningPlaceDropDown;

    @FindBy(xpath = "//input[@name='city']")
    WebElement cityField;

    @FindBy(xpath = "//input[@id='helper_planningControlAttributesDate']")
    List<WebElement> dateField;

    @FindBy(xpath = "(//img[@src='extensions/is.assessor/externallib/extjs/resources/images/default/s.gif'])[2]")
    WebElement calendarButton;

    @FindBy(xpath = "//table[@class='x-date-inner']/tbody/tr/td")
    List<WebElement> calendarTable;

    @FindBy(xpath = "//button[text()='Сегодня']")
    WebElement todayButton;

    @FindBy(xpath = "//input[@id='sittingTimeStart']")
    List<WebElement> sittingTimeStartField;

    @FindBy(xpath = "(//img[@src='extensions/is.assessor/externallib/extjs/resources/images/default/s.gif'])[3]")
    WebElement selectSittingTimeStartDropDown;

    @FindBy(xpath = "//div[@class='x-layer x-combo-list ']/div")
    List<WebElement> selectSittingTimeStart;

    @FindBy(xpath = "//input[@id='sittingTimeEnd']")
    List<WebElement> sittingTimeEndField;

    @FindBy(xpath = "(//img[@src='extensions/is.assessor/externallib/extjs/resources/images/default/s.gif'])[4]")
    WebElement sittingTimeEndDropDown;

    @FindBy(xpath = "(//div[@class='x-layer x-combo-list '])[2]/div")
    List<WebElement> selectSittingTimeEnd;

    @FindBy(xpath = "//div[@class='x-grid3-body']")
    List<WebElement> participantsList;

    @FindBy(xpath = "//button[text()='Сохранить']")
    WebElement buttonSave;

    @FindBy(xpath = "//button[text()='Отмена']")
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

    public void getDateAsString() {
        Date date = new Date();
        String formattedDate = new SimpleDateFormat("dd.MM.yyyy").format(date);
    }
   /* public void getTimeAsString(){
        Time time = new Time();
     //todo решить как задать формат времени

    }*/

    //проверки поля Номер
    public boolean emptySittingNumber() {
        return inputSittingNumber.isEmpty();
    }

    public String getSittingNumberText() {
        return inputSittingNumber.get(0).getText();
    }

    public void setSittingNumber(String number) {
        inputSittingNumber.get(0).sendKeys(number);

    }

  /*  public boolean checkUnique(List<WebElement> webElementList) {
        //TOdo должена быть проверка на уникальность
    }*/

    public void typeSittingNumber(String number) {
        inputSittingNumber.get(0).sendKeys(number);
    }

    // проверки поля Место заседания
    public boolean emptySittingPlace() {
        return inputSittingPlace.isEmpty();
    }

    public void typeSittingPlace(String place) {
        inputSittingPlace.get(0).sendKeys(place);
    }

    public List<WebElement> getSelectPlanningPlace() {
        Actions action = new Actions(webDriver);
        action.moveToElement(inputPlanningPlaceDropDown).click().perform();
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return selectPlanningPlace;
    }

//проверка поля Город

    public void typeCityField(String city){
        cityField.sendKeys(city);
    }

    public String getCityFieldText(){
        return cityField.getText();
    }

//проверка  Дата и Время

    public boolean emptyDateField(){
        return dateField.isEmpty();
    }

   public void typeDate(String dateSitting){
        dateField.get(0).sendKeys(dateSitting);
    }

    public WindowMeetingScheduling clickCalendarButton (){
        Actions actions = new Actions(webDriver);
        actions.moveToElement(calendarButton).click().perform();
        return this;
    }

    public boolean emptyCalendar(){
        return calendarTable.isEmpty();
    }

    public WindowMeetingScheduling clickTodayButton(){
        Actions actions = new Actions(webDriver);
        actions.moveToElement(todayButton).click().perform();
        return this;
    }


//Время Начала заседания
    public boolean emptySittingTimeStart(){
        return sittingTimeStartField.isEmpty();
    }

    public List<WebElement> getSelectSittingTimeStart() {
        Actions action = new Actions(webDriver);
        action.moveToElement(selectSittingTimeStartDropDown).click().perform();
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return selectSittingTimeStart;
    }

    public void typeTimeEnd(String time){
        sittingTimeEndField.get(0).sendKeys(time);
    }
    //Время Окончания заседания
    public boolean emptySittingTimeEnd(){
        return sittingTimeEndField.isEmpty();
    }

    public List<WebElement> getSelectSittingTimeEnd() {
        Actions action = new Actions(webDriver);
        action.moveToElement(selectSittingTimeEnd).click().perform();
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return selectSittingTimeEnd;
    }

    public void typeTimeStart(String time){
        sittingTimeStartField.get(0).sendKeys(time);
    }



    //Работа с сообщениями об ошибках

    public String getErrorMassageText(){
        return errorMessage.get(0).getText();
    }











    public void savePlanning() {
        buttonSave.click();
    }
}
