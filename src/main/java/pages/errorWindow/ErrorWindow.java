package pages.errorWindow;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.window.WindowSittingPlanning;

import java.util.List;

public class ErrorWindow {

    private WebDriver webDriver;

    private Actions actions;

    private By headerErrorByMeetingScheduling = By.xpath("//span[text()='Ошибка']");

    @FindBy(xpath = "(//div[@class='x-window x-window-plain x-window-dlg'])[2]//span")
    private List<WebElement> errorMessage;

    @FindBy(xpath = "(//table[@class='x-btn x-btn-noicon'])[3]//button")
    private WebElement errorOkButton;

    @FindBy(xpath = "//div[@class=' x-window x-window-plain x-window-dlg']//div[@class='x-tool x-tool-close']")
    private WebElement errorCloseButton;

    public ErrorWindow(WebDriver webDriver) {
        this.webDriver = webDriver;
        actions = new Actions(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public String getErrorMassageText() {
        return errorMessage.iterator().next().getText();
    }

    public WindowSittingPlanning clickOkErrorButton() {
        actions.moveToElement(errorOkButton).click().perform();
        return new WindowSittingPlanning(webDriver);
    }

    public WindowSittingPlanning clickCloseErrorButton() {
        actions.moveToElement(errorCloseButton).click().perform();
        return new WindowSittingPlanning(webDriver);
    }
}
