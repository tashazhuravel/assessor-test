package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProtocolPage {

    WebDriver webDriver;
    Actions actions;

    @FindBy(xpath = "//table[@id='btnSecretaryProtocolClose']//button")
    private WebElement closeProtocol;

    @FindBy(xpath = "//table[@id='btnSecretaryProtocolRefresh']//button")
    private WebElement refreshProtocol;

    public ProtocolPage(WebDriver webDriver){
        this.webDriver = webDriver;
        actions = new Actions(webDriver);
        PageFactory.initElements(webDriver,this);
    }

}
