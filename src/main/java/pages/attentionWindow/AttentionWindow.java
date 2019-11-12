package pages.attentionWindow;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.AgendaPage;
import pages.messageWindow.MessageWindowAgenda;

public class AttentionWindow {
    private WebDriver webDriver;
    private Actions actions;

    private By headerAttentionWindow = By.xpath("(//div[@class=' x-window x-window-plain x-window-dlg']//span)[1]");

    @FindBy(xpath = "(//div[@class=' x-window x-window-plain x-window-dlg']//span)[2]")
    private WebElement textAttention;

    @FindBy(css = ".x-tool")
    private WebElement attentionCloseButtonByX;

    @FindBy(xpath = "(//table[@class='x-btn x-btn-noicon']//button)[1]")
    private WebElement yesAttentionButton;

    @FindBy(xpath = "(//table[@class='x-btn x-btn-noicon']//button)[2]")
    private WebElement noAttentionButton;



    public AttentionWindow(WebDriver webDriver){
        this.webDriver = webDriver;
        actions = new Actions(webDriver);
        PageFactory.initElements(webDriver,this);
    }

    public String getTextAttention(){return textAttention.getText();}

    public MessageWindowAgenda clickYesAttentionButton(){
        actions.moveToElement(yesAttentionButton).click().perform();
        return new MessageWindowAgenda(webDriver);
    }

    public AgendaPage clickNoAttentionButton(){
        actions.moveToElement(noAttentionButton).click().perform();
        return new AgendaPage(webDriver);
    }

    public AgendaPage clickAttentionCloseByXButton(){
        actions.moveToElement(attentionCloseButtonByX).click().perform();
        return new AgendaPage(webDriver);
    }


}
