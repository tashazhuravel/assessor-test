package pages.messageWindow;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.AgendaPage;

public class MessageWindow {

    private WebDriver webDriver;

    private Actions actions;

    private By headerMessageByPreliminaryAcquaintanceWithAgenda = By.xpath("//div[@class=' x-window x-window-plain x-window-dlg']//span");

    @FindBy(xpath = "//div[@class='ext-mb-content']//span")
    private WebElement textMessage;

    @FindBy(css = ".x-tool")
    private WebElement messageCloseButtonByX;

    @FindBy(xpath = "//div[@class=' x-window x-window-plain x-window-dlg']//td[@class='x-toolbar-cell']//button")
    private WebElement messageOkButton;

    public MessageWindow(WebDriver webDriver){
        this.webDriver = webDriver;
        actions = new Actions(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public String getMessage(){ return textMessage.getText();}

    public AgendaPage clickMessageOkButton(){
        actions.moveToElement(messageOkButton).click().perform();
        return new AgendaPage(webDriver);
    }

    public AgendaPage clickMessageCloseButtonByX(){
        actions.moveToElement(messageCloseButtonByX).click().perform();
        return new AgendaPage(webDriver);
    }

    public By getHeaderMessageByPreliminaryAcquaintanceWithAgenda() {
        return headerMessageByPreliminaryAcquaintanceWithAgenda;
    }

    public WebElement getTextMessage() {
        return textMessage;
    }
}
