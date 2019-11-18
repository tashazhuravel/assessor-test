package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

public class ProtocolPage {

    WebDriver webDriver;
    Actions actions;

    public ProtocolPage(WebDriver webDriver){
        this.webDriver = webDriver;
        actions = new Actions(webDriver);
        PageFactory.initElements(webDriver,this);
    }

}
