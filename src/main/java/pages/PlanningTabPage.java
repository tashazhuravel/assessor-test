package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class PlanningTabPage extends MainPage {

    public PlanningTabPage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(xpath = "(//button[contains(text(),'Планировать новое заседание')])[1]")
    WebElement planningEventButton;

    @FindBy(xpath = "//span[text()='Планирование заседания']")
    List<WebElement> windowCreatePlanning;

    @FindBy(xpath = "//button[text()='Сохранить']")
    WebElement buttonSave;

    @FindBy(xpath = "//div[@class = 'x-combo-list-inner']/div")
    List<WebElement> selectPlanningPlace;

    @FindBy(xpath = "//img[@src='extensions/is.assessor/externallib/extjs/resources/images/default/s.gif']")
    WebElement inputPlanningPlaceDropDown;

    @FindBy(xpath = "//input[@id='number_sittings']")
    WebElement inputSittingNumber;

  //  @FindBy (xpath = "")

    public void clickPlanningEventButton() {
        planningEventButton.click();
    }

    public void savePlanning() {
        buttonSave.click();
    }

    public boolean checkWindowPlanningCreate() {
        return windowCreatePlanning.isEmpty();
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
    public void getSittingNumber(int num){

    }
}
