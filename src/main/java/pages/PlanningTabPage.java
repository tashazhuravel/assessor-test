package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PlanningTabPage extends MainPage {

    public PlanningTabPage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(xpath = "(//button[contains(text(),'Планировать новое заседание')])[1]")
    WebElement planningEventButton;

    public void clickPlanningEventButton() {
        planningEventButton.click();
    }
}
