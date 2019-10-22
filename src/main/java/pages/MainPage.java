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

    @FindBy(xpath = "//button[text()='Завершение сеанса']")
    WebElement logOutButton;
    @FindBy(xpath = "//button [@id=\"ext-gen21\"]")
    WebElement userFIOButton;
    @FindBy(xpath = "//button[text()='О системе']")
    WebElement aboutSystemButton;

    public MainPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public void logOut() {
        Actions actions = new Actions(webDriver);
        actions.moveToElement(logOutButton).click().perform();
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
    public void userAccount (){
        Actions actions = new Actions(webDriver);
        actions.moveToElement(userFIOButton).click().perform();
    }

    public void aboutSystem (){
        Actions actions = new Actions(webDriver);
        actions.moveToElement(aboutSystemButton).click().perform();
    }
}
