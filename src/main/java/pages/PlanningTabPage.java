package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

    public void clickPlanningEventButton() {
        planningEventButton.click();
    }

    public void savePlanning() {
        buttonSave.click();
    }

    public boolean checkWindowPlanningCreate() {
        return windowCreatePlanning.isEmpty();
    }
}
