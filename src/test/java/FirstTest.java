import dataBase.AssesorService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.CurrentMeetingPage;
import pages.MainPage;
import pages.UnllocatedQuestions;
import pages.mainPageTab.PlanningTabPage;
import pages.window.WindowAboutSystem;
import pages.window.WindowNotification;
import pages.window.WindowUserAccount;

import static org.junit.Assert.*;

public class FirstTest extends BaseWebDriverTest {

    public FirstTest(String login, String password, String fioUserAccount, String unllocatedQuestionsStatusField, String sittingPlace) {
        this.login = login;
        this.password = password;
        this.fioUserAccount = fioUserAccount;
        this.unllocatedQuestionsStatusField = unllocatedQuestionsStatusField;
        this.sittingPlace = sittingPlace;

    }

    @Before
    public void setUp() {
        assesorService = new AssesorService(dataBaseConnection.stmt);
        authorizationPage = assessorSite.getAuthorizationPage();
        log.debug("Authorization");
        System.out.println("Step 1: Authorization");
        authorizationPage.setLogin(login);
        authorizationPage.setPassword(password);
        authorizationPage.clickLoginButton();
        assertEquals("Неверный логин/пароль.", authorizationPage.getElementsFromMainPage().size(), 1);
        takeScreenshot("initWebDriver");
    }

    @Test
    @Ignore
    //проверка модального окна "О системе"
    public void checkWindowAboutSystemTest() {
        MainPage mainPage = assessorSite.getMainPage();
        WindowAboutSystem windowAboutSystem = mainPage.clickButtonAboutSystem();
        assertTrue("Не открылось диалоговое окно 'О системе'.", isElementPresent(windowAboutSystem.getHeaderWindowAboutSystem()));
        windowAboutSystem.closeWindowAboutSystemByX();
        mainPage.clickButtonAboutSystem();
        windowAboutSystem.closeWindowAboutSystemByButton();
    }

    @Test
    @Ignore
    //проверка модального окна "Учетная запись пользователя"
    public void checkWindowUserAccountTest() {
        MainPage mainPage = assessorSite.getMainPage();
        WindowUserAccount windowUserAccount = mainPage.clickButtonUserAccount();
        assertTrue("Не открылось диалоговое окно 'Учетная запись пользователя'.", isElementPresent(windowUserAccount.getHeaderWindowUserAccount()));
        assertEquals("Пустое поле ФИО", windowUserAccount.getUserFIOFieldText().size(), 1);
        assertEquals("Неверное ФИО пользователя.", fioUserAccount, windowUserAccount.getTextByUserFIOField());
        windowUserAccount.saveUserAccount();
        mainPage.clickButtonUserAccount();
        windowUserAccount.closeWindowUserAccountByButton();
        mainPage.clickButtonUserAccount();
        windowUserAccount.closeWindowUserAccountByX();
    }

    @Test
    //@Ignore
    public void checkWindowNotification() {
        MainPage mainPage = assessorSite.getMainPage();
        WindowNotification windowNotification = mainPage.clickButtonNotification();

        assertTrue(".", isElementVisible(mainPage.getNotificationWindow()));
        windowNotification.closeWindowNotificationByX();

        assertTrue("Нет новых сообщений", isFind(mainPage.getNotificationButtonHaveMessage()));
        mainPage.clickNotificationButtonHaveNewMessage();
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue("Не удалось открыть Уведомления, либо нет новых уведомлений", isElementPresent(windowNotification.getHaveNewNotificationMessage()));
        windowNotification.clickNewNotificationMessage();
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue("Не удалось открыть Уведомления, либо нет новых уведомлений", isElementPresent(windowNotification.getHaveOldNotificationMessage()));
        String textOldNotificationMessage = windowNotification.getNumberSittingFromNottificationMessage();
        System.out.println(textOldNotificationMessage);
        CurrentMeetingPage currentMeetingPage = windowNotification.clickLinkSittingNotificationMessage();
        assertEquals("Заседание на созданно, либо не осуществлен переход на форму запланированного заседания", textOldNotificationMessage, currentMeetingPage.getPartOfTextStatusField(textOldNotificationMessage));
        currentMeetingPage.clickBackOnListSitting();
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mainPage.clickNotificationButtonHaveNewMessage();
        windowNotification.clickClearButton();
        assertFalse("Не удалось открыть Уведомления, либо список содержит уведомления", isElementPresent(windowNotification.getHaveOldNotificationMessage()));
        windowNotification.clickCloseButton();
        assertEquals("Не закрыто окно уведомления.", "hidden", mainPage.getNotificationWindow().getCssValue("visibility"));
    }

    @Test
    @Ignore
    //проверка кнопки "Нераспределенные вопросы"
    public void checkUnllocatedQuestionsTest() {
        PlanningTabPage planningTabPage = assessorSite.getPlanningPage();
        UnllocatedQuestions unllocatedQuestions = planningTabPage.clickUnllocatedQuestionsButton();

        assertEquals("Не осуществлен переход на форме 'Нераспределенные вопросы'.Неверный текст в поле статус.", unllocatedQuestionsStatusField, unllocatedQuestions.getTextStatusField());
    }


}












