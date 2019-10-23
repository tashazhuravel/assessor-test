package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class WindowAboutSystem {
    protected WebDriver webDriver;

    @FindBy(xpath = "//span[text()='О системе']")
    List<WebElement>  windowAboutSystem;

    @FindBy(xpath = "//button[text()='Закрыть']")
    WebElement closeButton;

    @FindBy(xpath = "//div[@class='x-tool x-tool-close']")
    WebElement closeWindowButton;

    public WindowAboutSystem(WebDriver webDriver){
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver,this);
    }

}
