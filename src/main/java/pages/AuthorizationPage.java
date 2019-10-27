package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AuthorizationPage {

    private WebDriver webDriver;

    @FindBy(id = "wpName")
    WebElement inputLogin;

    @FindBy(name = "wpPassword")
    WebElement inputPassword;

    @FindBy(id = "wpLoginattempt")
    WebElement buttonLogining;

    @FindBy(xpath = "//div[@id='layoutTabs']")
    List<WebElement> elementsFromMainPage;

    public AuthorizationPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public void setLogin(String login) {
        inputLogin.clear();
        inputLogin.sendKeys(login);
    }

    public void setPassword(String password) {
        inputPassword.clear();
        inputPassword.sendKeys(password);
    }

    public void clickLoginButton() {
        buttonLogining.submit();
    }

    public WebElement getInputLogin() {
        return inputLogin;
    }

    public WebElement getInputPassword() {
        return inputPassword;
    }

    public WebElement getButtonLogining() {
        return buttonLogining;
    }

    public List<WebElement> getElementsFromMainPage() {
        return elementsFromMainPage;
    }
}
