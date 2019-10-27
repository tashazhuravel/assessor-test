package pages.window;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WindowAboutSystem {

    private WebDriver webDriver;

    private Actions actions;

    private By headerWindowAboutSystem = By.xpath("//span[text()='О системе']");

    @FindBy(xpath = "//button[text()='Закрыть']")
    private WebElement closeButton;

    @FindBy(xpath = "//div[@class='x-tool x-tool-close']")
    private WebElement closeWindowButton;

    public WindowAboutSystem(WebDriver webDriver) {
        this.webDriver = webDriver;
        actions = new Actions(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public WindowAboutSystem closeWindowAboutSystemByButton() {
        actions.moveToElement(closeButton).click().perform();
        return this;
    }

    public void closeWindowAboutSystemByX() {
        actions.moveToElement(closeWindowButton).click().perform();
    }

    public By getHeaderWindowAboutSystem() {
        return headerWindowAboutSystem;
    }

    public WebElement getCloseButton() {
        return closeButton;
    }

    public WebElement getCloseWindowButton() {
        return closeWindowButton;
    }
}
