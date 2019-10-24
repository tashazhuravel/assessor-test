package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import javax.swing.*;
import java.util.List;

public class WindowUserAccount {

    protected WebDriver webDriver;

    @FindBy(xpath = "//div/span[text()='Учётная запись пользователя']")
    List<WebElement> windowUserAccount;

    @FindBy(xpath = "//div[contains(text(),'Секретарева')]")
    List<WebElement> userFIOFieldText;

    @FindBy(xpath = "//button[text()='Сохранить']")
    WebElement saveButton;

    @FindBy(xpath = "//button[text()='Закрыть']")
    WebElement closeButton;

    @FindBy(xpath = "(//div[@class='x-tool x-tool-close'])[2]")
    WebElement closeWindowButton;

    public WindowUserAccount(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    //Проверяем открыто окно Учетная запись пользователя или нет
    public boolean checkWindowUserAccount() {
        return windowUserAccount.isEmpty();
    }

    //Проверка поля ФИО
    public boolean emptyUserFIOFieldText() {
        return userFIOFieldText.isEmpty();
    }
    public String getUserFIOFieldText(){
        return userFIOFieldText.get(0).getText();
    }

    public void saveUserAccount(){
        Actions actions = new Actions(webDriver);
        actions.moveToElement(saveButton).click().perform();
    }

    public void closeUserAccount(){
        Actions actions = new Actions(webDriver);
        actions.moveToElement(closeButton).click().perform();
    }

    public void closingWindowUserAccount(){
        Actions actions = new Actions(webDriver);
        actions.moveToElement(closeWindowButton).click().perform();
    }

    }



