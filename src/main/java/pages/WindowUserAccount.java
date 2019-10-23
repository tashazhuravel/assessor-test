package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WindowUserAccount {

    protected WebDriver webDriver;

    @FindBy(xpath = "//div/span[text()='Учётная запись пользователя']")
    WebElement userAccount;

    @FindBy(xpath = "//fieldset[@class=' x-fieldset x-form-label-left']/div/div/div/div/div/div/div")
    WebElement userFIOFieldText;

    @FindBy(xpath = '')

    public WindowUserAccount(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }
}
