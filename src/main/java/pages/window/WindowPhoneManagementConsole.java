package pages.window;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.sittingPage.CurrentMeettingPage;

public class WindowPhoneManagementConsole {

    private WebDriver webDriver;

    private Actions actions;

    @FindBy(xpath = "//div[@class='x-window x-resizable-pinned']//div[@class='x-tool x-tool-close']")
    private WebElement closeByXButton;

    @FindBy(xpath = "//span[@class='call_button']")
    private WebElement callSelectedParticipantButton;

    @FindBy(xpath = "//div[@class='x-window-footer x-panel-btns']//button")
    private WebElement closeButton;

    //TODO дописать оставшиееся кнопки и чекбоксы и методы к ним

    private By headerTelephoneConsole = By.xpath("//div[@class='x-window x-resizable-pinned']//span[@class='x-window-header-text']");

    public WindowPhoneManagementConsole(WebDriver webDriver) {
        this.webDriver = webDriver;
        actions = new Actions(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public CurrentMeettingPage clickСloseByXButton() {
        actions.moveToElement(closeByXButton).click().perform();
        return new CurrentMeettingPage(webDriver);
    }

    public CurrentMeettingPage clickCloseButton() {
        actions.moveToElement(closeButton).click().perform();
        return new CurrentMeettingPage(webDriver);
    }

    public By getHeaderTelephoneConsole() {
        return headerTelephoneConsole;
    }
}
