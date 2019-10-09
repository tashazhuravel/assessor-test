package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {

    private WebDriver webDriver;

    @FindBy(xpath = "//ul/li[@id='layoutTabs__planning']/a")
    WebElement planningTab;

    @FindBy(xpath = "//ul/li[@id='layoutTabs__archivesearch']/a")
    WebElement archiveSearchTab;

    @FindBy(xpath = "//ul/li[@id='layoutTabs__manage']/a")
    WebElement manageTab;

    public MainPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public void clickTab(ETab eTab) {
        switch (eTab) {
            case PLANNING:
                planningTab.click();
                break;
            case ARCHIVE:
                archiveSearchTab.click();
                break;
            case MANAGER:
                manageTab.click();
                break;
        }
    }

    public static enum ETab {
        PLANNING, ARCHIVE, MANAGER
    }
}
