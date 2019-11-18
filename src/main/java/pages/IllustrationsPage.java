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
    protected List<WebElement> usersFIOTab;

    @FindBy(xpath = "//div[@id='pictureView']//ul/li//span/span")
    protected List<WebElement> usersFIOTabText;

    @FindBy(xpath = "//span[@id='currentQuestionName']")
    protected WebElement subjectSelectedQuestion;

    @FindBy(xpath = "//table[@id='sharePictureButton']//button")
    //TODO attribute pressed change text button Начать показ/Прекратить показ
    protected WebElement slideshowButton;

    @FindBy(xpath = "//table[@id='deletePictureButton']//button")
    protected WebElement deleteImageButton;

    @FindBy(xpath = "//div[@id='pageSelector']//div/img")
    protected WebElement imagePageButton;

    @FindBy(xpath = "//div[@id='pageSelector']//div/span")
    protected WebElement imagePageNumber;

    @FindBy(xpath = "//div[@id='pictureViewPanel']//div/img")
    protected WebElement imageContent;

    private By headerIllustrations = By.xpath("(//div[@id='sittingContent']//div/span)[1]");

    public IllustrationsPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        actions = new Actions(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public CurrentMeetingPage clickBackToQuestionListButton() {
        actions.moveToElement(backToQuestionListButton).click().perform();
        return new CurrentMeetingPage(webDriver);
    }

    public WindowUploadFile clickAddIllustrationButton() {
        actions.moveToElement(addIllustrationButton).click().perform();
        return new WindowUploadFile(webDriver);
    }

    public WindowMaximizedIllustration clickFullScreenModeButton() {
        actions.moveToElement(fullScreenModeButton).click().perform();
        return new WindowMaximizedIllustration(webDriver);
    }

    public String getFIOParticipantTab() {
        return usersFIOTabText.iterator().next().getAttribute("value");
    }

    public IllustrationsPage clickTabFIOParticipant() {
        usersFIOTab.iterator().next().click();
        return this;
    }

    public String getSubjectSelectedQuestionText() {
        return subjectSelectedQuestion.getAttribute("value");
    }

    public IllustrationsPage clickSlideshowButton() {
        actions.moveToElement(slideshowButton).click().perform();
        return this;
    }

    public IllustrationsPage clickDeleteImageButton() {
        actions.moveToElement(deleteImageButton).click().perform();
        return this;
    }

    public IllustrationsPage clickImagePageButton() {
        actions.moveToElement(imagePageButton).click().perform();
        return this;
    }

    public String getImagePageNumber() {
        return imagePageNumber.getText();
    }

    public WebElement getImageContent() {
        return imageContent;
    }

    public By getHeaderIllustrations() {
        return headerIllustrations;
    }
}
