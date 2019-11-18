package pages.window;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class WindowMailingNotificationInvitations {

    private WebDriver webDriver;
    private Actions actions;

    private By headerNotificationInvitations =By.xpath("//div[@class='x-window-header x-unselectable x-window-draggable']//span");

    @FindBy(xpath = "//div[@id='checkboxgroup']//input")
    private List<WebElement> checkboxFIOParticipant;

    @FindBy(xpath = "//div[@id='checkboxgroup']//label")
    private List<WebElement> listFIOParticipant;

    //todo "Рассылка приглашений-уведомления с текстом заседаний" доделать



    public WindowMailingNotificationInvitations(WebDriver webDriver){
        this.webDriver = webDriver;
        actions = new Actions(webDriver);
        PageFactory.initElements(webDriver,this);
    }


}
