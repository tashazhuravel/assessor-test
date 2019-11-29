package pages.window;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class WindowOpenVote {

    private WebDriver webDriver;
    private Actions actions;

    private By headerOpenVote = By.xpath("//div[@id='vote-window']/div/div/div/div/span");

    @FindBy(xpath = "//span[@class='userStatusOnline'] ")
    private  List<WebElement> userOnline;

    @FindBy(xpath = "//div[@id='planningVoteTableGrid']//table[@class='x-grid3-row-table']//div[@class='x-grid3-cell-inner x-grid3-col-1']")
    private List<WebElement> participantFIO;

    @FindBy(xpath = "//div[@id='planningVoteTableGrid']//table[@class='x-grid3-row-table']//div[@class='x-grid3-cell-inner x-grid3-col-2']/input") //name=pv[5] -id из БД
    private List<WebElement> firstVarVote;

    @FindBy(xpath = "//div[@id='planningVoteTableGrid']//table[@class='x-grid3-row-table']//div[@class='x-grid3-cell-inner x-grid3-col-3']/input")
    private List<WebElement> secondVarVote;

    @FindBy(xpath = "//div[@id='planningVoteTableGrid']//table[@class='x-grid3-row-table']//div[@class='x-grid3-cell-inner x-grid3-col-4']/input")
    private List<WebElement> thirdVarVote;

    @FindBy(xpath = "//div[@id='planningVoteTableGrid']//table[@class='x-grid3-row-table']//div[@class='x-grid3-cell-inner x-grid3-col-5']/input")
    private List<WebElement> nonVote;


    public WindowOpenVote(WebDriver webDriver){
        this.webDriver = webDriver;
        actions = new Actions(webDriver);
        PageFactory.initElements(webDriver,this);
    }
//TODO дописать все окна с голосованием

    public By getHeaderOpenVote() {
        return headerOpenVote;
    }
}
