package pages.window;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

public class WindowMailingNotificationInvitations {

    private WebDriver webDriver;
    private Actions actions;

    By headerNotificationInvitations =By.xpath("//div[@class='x-window-header x-unselectable x-window-draggable']//span");

    public WindowMailingNotificationInvitations(WebDriver webDriver){
        this.webDriver = webDriver;
        actions = new Actions(webDriver);
        PageFactory.initElements(webDriver,this);
    }


}
