package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {

    protected WebDriver webDriver;

    @FindBy(xpath = "//ul/li[@id='layoutTabs__planning']/a[2]")
    WebElement planningTab;

    @FindBy(xpath = "//ul/li[@id='layoutTabs__archivesearch']/a[2]")
    WebElement archiveSearchTab;

    @FindBy(xpath = "//ul/li[@id='layoutTabs__manage']/a[2]")
    WebElement manageTab;

    public MainPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public MainPage clickTab(ETab eTab) {
        Actions actions = new Actions(webDriver);
        switch (eTab) {
            case PLANNING:
                actions.moveToElement(planningTab).click().perform();
                return new PlanningTabPage(webDriver);
            case ARCHIVE:
                actions.moveToElement(archiveSearchTab).click().perform();
                //Todo тоже сделать return
                return null;
            case MANAGER:
                actions.moveToElement(manageTab).click().perform();
                //Todo тоже сделать return
                return null;
        }
        return null;
    }

    public enum ETab {
        PLANNING, ARCHIVE, MANAGER
    }
}
