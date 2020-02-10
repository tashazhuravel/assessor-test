package pages.window;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.sittingPage.CurrentMeettingPage;

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
    private List<WebElement> sittingPlaceDropDown;

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


    public WindowEditMeetingRequisites(WebDriver webDriver) {
        this.webDriver = webDriver;
        actions = new Actions(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public String getNameCommitteeText() {
        return nameCommitteeField.getText();
    }

    public String getNumberSittingText() {
        return numberSittingField.getText();
    }

    public void typeNumberSitting(String number) {
        numberSittingField.sendKeys(number);
    }

    public String getStartTimeText() {
        return startTimeField.getText();
    }

    public void typeStartTime(String start) {
        startTimeField.sendKeys(start);
    }

    public List<WebElement> clickStartTimeDropDownButton() {
        actions.moveToElement(startTimeDropDownButton).click().perform();
        try {
            Thread.sleep(500L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return startTimeDropDown;
    }

    public WindowEditMeetingRequisites clickStartTimeDropDown() {
        startTimeDropDown.iterator().next().click();
        return this;
    }

    public String getEndTimeText() {
        return endTimeField.getText();
    }

    public void typeEndTime(String end) {
        endTimeField.sendKeys(end);
    }

    public List<WebElement> clickEndTimDropDownButton() {
        actions.moveToElement(endTimeDropDownButton).click().perform();

        try {
            Thread.sleep(500L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return endTimeDropDown;
    }

    public WindowEditMeetingRequisites clickEndTimeDropDown() {
        endTimeDropDown.iterator().next().click();
        return this;
    }

    public String getSittingDateText() {
        return sittingDateField.getText();
    }

    public void typeDateSitting(String date) {
        sittingDateField.sendKeys(date);
    }

    public WindowEditMeetingRequisites clickCalendarButton() {
        actions.moveToElement(calendarButton).click().perform();
        try {
            Thread.sleep(500L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }

    public String getSittingPlaceText() {
        return sittingPlaceField.getText();
    }

    public void typeSittingPlace(String place) {
        sittingPlaceField.sendKeys(place);
    }

    public List<WebElement> clickSittingPlaceDropDownButton() {
        actions.moveToElement(sittingPlaceDropDownButton).click().perform();
        try {
            Thread.sleep(500L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return sittingPlaceDropDown;
    }

    public WindowEditMeetingRequisites clickSittingPlaceDropDown() {
        sittingPlaceDropDown.iterator().next().click();
        return this;
    }

    public String getCityField() {
        return cityField.getText();
    }

    public void typeCityField(String city) {
        cityField.sendKeys(city);
    }

    public WindowEditMeetingRequisites clickParticipantCheckbox() {
        participantList.iterator().next().click();
        return this;
    }

    public String getParticipantFIO() {
        return participantList.iterator().next().getText();
    }

    public String getPresidingFIOText() {
        return presidingField.getText();
    }

    public List<WebElement> clickPresidingDropDownButton() {
        actions.moveToElement(presidingDropDownButton).click().perform();
        try {
            Thread.sleep(500L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return presidingDropDown;
    }

    public WindowEditMeetingRequisites clickPresidingDropDown() {
        presidingDropDown.iterator().next().click();
        return this;
    }

    public String getPresentFIO() {
        return presentField.getText();
    }

    public void typePresentFIO(String name) {
        presentField.sendKeys(name);
    }

    public WindowEditMeetingRequisites clickPublicCheckbox() {
        actions.moveToElement(publicCheckbox).click().perform();
        return this;
    }

    public String getFormVoteText() {
        return formVoteField.getText();
    }

    public void typeFormVote(String form) {
        formVoteField.sendKeys(form);
    }

    public List<WebElement> clickFormVoteDropDownButton() {
        actions.moveToElement(formVoteDropDownButton).click().perform();
        try {
            Thread.sleep(500L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return formVoteDropDown;
    }

    public WindowEditMeetingRequisites clickFormVoteDropDown() {
        formVoteDropDown.iterator().next().click();
        return this;
    }

    public CurrentMeettingPage clickSaveButton() {
        actions.moveToElement(saveButton).click().perform();
        return new CurrentMeettingPage(webDriver);
    }

    public CurrentMeettingPage clickCancelButton() {
        actions.moveToElement(cancelButton).click().perform();
        return new CurrentMeettingPage(webDriver);
    }

    public By getHeaderWindow() {
        return headerWindow;
    }
}
