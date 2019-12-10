package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AuthorizationPage {

    private WebDriver webDriver;

    @FindBy(id = "wpName")
    private WebElement inputLogin;

    @FindBy(name = "wpPassword")
    private WebElement inputPassword;

    @FindBy(id = "wpLoginattempt")
    private WebElement buttonLogining;

    @FindBy(xpath = "//div[@id='layoutTabs']")
    private List<WebElement> elementsFromMainPage;

    public AuthorizationPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public AuthorizationPage setLogin(String login) {
        inputLogin.clear();
        inputLogin.sendKeys(login);
        return this;
    }

    public AuthorizationPage setPassword(String password) {
        inputPassword.clear();
        inputPassword.sendKeys(password);
        return this;
    }

    public MainPage clickLoginButton() {
        buttonLogining.submit();
        return new MainPage(webDriver);
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
