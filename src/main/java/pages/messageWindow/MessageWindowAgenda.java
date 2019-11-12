package pages.messageWindow;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.AgendaPage;

import java.util.List;

public class MessageWindowAgenda {
    private WebDriver webDriver;

    private Actions actions;

    private By headerMessageByPreliminaryAcquaintanceWithAgenda = By.xpath("//div[@class=' x-window x-window-plain x-window-dlg']//span");

    @FindBy(xpath = "//div[@class='ext-mb-content']//span")
    private List<WebElement> textMessage;

    @FindBy(css = ".x-tool")
    private WebElement messageCloseButtonByX;

    @FindBy(xpath = "//table[@class='x-btn x-btn-noicon']//button")
    private WebElement messageOkButton;

    public MessageWindowAgenda(WebDriver webDriver){
        this.webDriver = webDriver;
        actions = new Actions(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public String getTextMessage(){ return textMessage.iterator().next().getText();}

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
}
