package pages.window;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WindowEditSittingRequisiteArchive {

    private WebDriver webDriver;
    private Actions actions;

    private By headerWindow = By.xpath("//div[@class=' x-window']/div/div/div/div/span");

    @FindBy(xpath = "//input[@name='dmsregn']")
    private WebElement numberProtocolField;

    @FindBy(xpath = "//input[@name='dmsregdate']")
    private WebElement dateProtocolField;

    @FindBy(xpath = "//div[@class=' x-window']//img")
    private WebElement calendarButton;

    @FindBy(xpath = "//div[@class='x-menu x-menu-floating x-layer x-date-menu x-menu-plain x-menu-nosep']")
    private WebElement calendar;

    @FindBy(xpath = "//td[@class='x-date-bottom']//button")
    private WebElement todayButton;

    @FindBy(xpath = "//table[@id='btnAttributesSave']//button")
    private WebElement saveButton;

    @FindBy(xpath = "(//div[@class=' x-window']//table[@class='x-btn x-btn-noicon']//button)[2]")
    private WebElement cancelButton;

    public WindowEditSittingRequisiteArchive(WebDriver webDriver){
        this.webDriver = webDriver;
        actions = new Actions(webDriver);
        PageFactory.initElements(webDriver,this);
    }








}
