package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.window.WindowMaximizedIllustration;
import pages.window.WindowUploadFile;
import sittingPage.CurrentMeetingPage;

import java.util.List;

public class IllustrationsPage {

    private WebDriver webDriver;
    private Actions actions;

    @FindBy(xpath = "//table[@id='planningControlManagmentButtonInfoClose']//button")
    private WebElement backToQuestionListButton;

    @FindBy(xpath = "//table[@id='planningControlManagmentButtonAddPicture']//button")
    private WebElement addIllustrationButton;

    @FindBy(xpath = "//table[@id='planningControlManagmentButtonInfoMaximize']//button")
    private WebElement fullScreenModeButton;

    @FindBy(xpath = "//div[@id='pictureViewPanel']//div[@class='no-pictures-text']")
    private WebElement noImageText;

    @FindBy(xpath = "//div[@id='pictureView']//ul/li")
    private List<WebElement> usersFIOTab;

    @FindBy(xpath = "//div[@id='pictureView']//ul/li//span/span")
    private List<WebElement> usersFIOTabText;

    @FindBy(xpath = "//span[@id='currentQuestionName']")
    private WebElement subjectSelectedQuestion;

    @FindBy(xpath = "//table[@id='sharePictureButton']//button")  //TODO attribute pressed change text button Начать показ/Прекратить показ
    private WebElement slideshowButton;

    @FindBy(xpath = "//table[@id='deletePictureButton']//button")
    private WebElement deleteImageButton;

    @FindBy(xpath = "//div[@id='pageSelector']//div/img")
    private WebElement imagePageButton;

    @FindBy(xpath = "//div[@id='pageSelector']//div/span")
    private WebElement imagePageNumber;

    @FindBy(xpath = "//div[@id='pictureViewPanel']//div/img")
    private WebElement imageContent;

    private By headerIllustrations = By.xpath("(//div[@id='sittingContent']//div/span)[1]");

    public IllustrationsPage(WebDriver webDriver){
        this.webDriver = webDriver;
        actions = new Actions(webDriver);
        PageFactory.initElements(webDriver,this);
    }

    public CurrentMeetingPage clickBackToQuestionListButton(){
        actions.moveToElement(backToQuestionListButton).click().perform();
        return new CurrentMeetingPage(webDriver);
    }

    public WindowUploadFile clickAddIllustrationButton(){
        actions.moveToElement(addIllustrationButton).click().perform();
        return new WindowUploadFile(webDriver);
    }

    public WindowMaximizedIllustration clickFullScreenModeButton(){
        actions.moveToElement(fullScreenModeButton).click().perform();
        return new WindowMaximizedIllustration(webDriver);
    }
}
