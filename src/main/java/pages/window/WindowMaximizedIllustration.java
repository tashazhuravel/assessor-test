package pages.window;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.IllustrationsPage;

public class WindowMaximizedIllustration extends IllustrationsPage {


    private By headerMaximizedIllustration = By.xpath("//div[@id='windowIllustration']//span/center");

    Actions actions;

    @FindBy(xpath = "//div[@id='windowIllustration']//div[@class='x-tool x-tool-close']")
    private WebElement closeByXButton;

    public WindowMaximizedIllustration(WebDriver webDriver) {
        super(webDriver);
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

    //TODO доделать метод
   /* public WindowMaximizedIllustration clickCloseByXButton(){
        actions.moveToElement(closeByXButton).click().perform();
        return new IllustrationsPage();

    }*/

    public String getImagePageNumber() {
        return imagePageNumber.getText();
    }

    public WebElement getImageContent() {
        return imageContent;
    }


}
