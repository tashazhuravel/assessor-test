package pages.errorWindow;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.window.WindowMeetingScheduling;

import java.util.List;

public class ErrorByMeetingScheduling {

    private WebDriver webDriver;

    private Actions actions;

    //TODO создать поле заголовок типа By для проверки открытия окна

    @FindBy(xpath = "(//div[@class='x-window x-window-plain x-window-dlg'])[2]//span")
    private List<WebElement> errorMessage;

    @FindBy(xpath = "(//table[@class='x-btn x-btn-noicon'])[3]//button")
    private WebElement errorOkButton;

    @FindBy(xpath = "//div[@class=' x-window x-window-plain x-window-dlg']//div[@class='x-tool x-tool-close']")
    private WebElement errorCloseButton;

    public ErrorByMeetingScheduling(WebDriver webDriver) {
        this.webDriver = webDriver;
        actions = new Actions(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public String getErrorMassageText() {
        return errorMessage.iterator().next().getText();
    }

    public WindowMeetingScheduling clickOkErrorButton() {
        actions.moveToElement(errorOkButton).click().perform();
        return new WindowMeetingScheduling(webDriver);
    }

    public WindowMeetingScheduling clickCloseErrorButton() {
        actions.moveToElement(errorCloseButton).click().perform();
        return new WindowMeetingScheduling(webDriver);
    }
}
