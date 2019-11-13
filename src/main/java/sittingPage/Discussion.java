package sittingPage;

import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Discussion {

   private WebDriver webDriver;
   private Actions actions;

    @FindBy(xpath = "//div[@class=' x-panel chatClass x-border-panel']//span[@class='titlePanel']")
    private WebElement headerDiscussion;

    @FindBy(xpath = "//div[@class='x-tool x-tool-toggle x-tool-collapse-east']")
    private WebElement hideDiscussionButton;

    @FindBy(xpath = "//div[@class='chat-msg__day-parser']/span")
    private List<WebElement> dayAndDate;

    @FindBy(xpath = "//div[@class='chat-msg msg-system']/p")
    private List<WebElement> systemMessage;

    @FindBy(xpath = "//div[@class='chat-msg msg-another-author']/p")
    private List<WebElement> anotherAuthorMessage;

    @FindBy(xpath = "//div[@class='chat-msg msg-i-am-author']/p")
    private List<WebElement> myMessage;

    @FindBy(xpath = "//textarea[@name='messageText']")
    private WebElement inputMessageField;

    @FindBy(xpath = "//div[@id='messageForm']//button")
    private WebElement sendMessageButton;

    public Discussion(WebDriver webDriver){
        this.webDriver = webDriver;
        actions = new Actions(webDriver);
        PageFactory.initElements(webDriver, this);
    }

}
