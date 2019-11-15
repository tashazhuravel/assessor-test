package pages.window;

import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.InformationTablePage;

public class WindowMaximizedInformationTable {

    private WebDriver webDriver;

    private Actions actions;

    @FindBy(xpath = "//div[@class=' x-window x-window-maximized']//div[@class='x-tool x-tool-restore']")
    private WebElement restoreWindow;

    @FindBy(xpath = "//div[@class=' x-window x-window-maximized']//div[@id='textcontent']")
    private WebElement maximizedTextContent;

    private By headerMaximizedWindow = By.xpath("//div[@class=' x-window x-window-maximized']//span/center");

    public WindowMaximizedInformationTable(WebDriver webDriver) {
        this.webDriver = webDriver;
        actions = new Actions(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public String getMaximizedTextContent() {
        return maximizedTextContent.getText();
    }

    public InformationTablePage clickRestoreWindow() {
        actions.moveToElement(restoreWindow).click().perform();
        return new InformationTablePage(webDriver);
    }

    public By getHeaderMaximizedWindow() {
        return headerMaximizedWindow;
    }
}
