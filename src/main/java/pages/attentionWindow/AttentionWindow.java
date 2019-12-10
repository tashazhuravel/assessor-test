package pages.attentionWindow;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.AgendaPage;
import pages.messageWindow.MessageWindow;
import sittingPage.CurrentMeetingPage;

public class AttentionWindow {
    private WebDriver webDriver;
    private Actions actions;

    private By headerAttentionWindow = By.xpath("(//div[@class=' x-window x-window-plain x-window-dlg']//span)[1]");

    @FindBy(xpath = "(//div[@class=' x-window x-window-plain x-window-dlg']//span)[2]")
    private WebElement textAttention;

    @FindBy(xpath = "//div[@class='x-tool x-tool-close']")
    private WebElement attentionCloseButtonByX;

    @FindBy(xpath = "(//div[@class=' x-window x-window-plain x-window-dlg']//td[@class='x-toolbar-cell']//button)[1]")
    private WebElement yesAttentionButton;

    @FindBy(xpath = "(//div[@class=' x-window x-window-plain x-window-dlg']//td[@class='x-toolbar-cell']//button)[2]")
    private WebElement noAttentionButton;



    public AttentionWindow(WebDriver webDriver){
        this.webDriver = webDriver;
        actions = new Actions(webDriver);
        PageFactory.initElements(webDriver,this);
    }

    public String getTextAttention(){return textAttention.getText();}

    public MessageWindow clickYesAttentionButton(){
        actions.moveToElement(yesAttentionButton).click().perform();
        return new MessageWindow(webDriver);
    }

    public AgendaPage clickYesButton(){
        actions.moveToElement(yesAttentionButton).click().perform();
        return new AgendaPage(webDriver);
    }

    public AgendaPage clickNoAttentionButton(){
        actions.moveToElement(noAttentionButton).click().perform();
        return new AgendaPage(webDriver);
    }

    public AgendaPage clickAttentionCloseByXButton(){
        actions.moveToElement(attentionCloseButtonByX).click().perform();
        return new AgendaPage(webDriver);
    }

    public CurrentMeetingPage clickYesDeleteButton(){
        actions.moveToElement(yesAttentionButton).click().perform();
        return new CurrentMeetingPage(webDriver);
    }
    public CurrentMeetingPage clickNoDeleteButton(){
        actions.moveToElement(noAttentionButton).click().perform();
        return new CurrentMeetingPage(webDriver);
    }




}
