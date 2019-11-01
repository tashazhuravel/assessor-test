import dataBase.AssesorService;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import pages.CurrentMeetingPage;
import pages.MainPage;
import pages.UnllocatedQuestions;
import pages.mainPageTab.PlanningTabPage;
import pages.window.WindowAboutSystem;
import pages.window.WindowMeetingScheduling;
import pages.window.WindowNotification;
import pages.window.WindowUserAccount;

import java.util.Arrays;
import java.util.List;

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
        System.out.println("Step 1: Authorization");
        authorizationPage.setLogin(login);
        authorizationPage.setPassword(password);
        authorizationPage.clickLoginButton();
        assertEquals("Неверный логин/пароль.", authorizationPage.getElementsFromMainPage().size(), 1);
        takeScreenshot("authorization");
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
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(".", "visible", mainPage.getNotificationWindow().getCssValue("visibility"));
        windowNotification.closeWindowNotificationByX();
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue("Нет новых сообщений", isElementPresent(mainPage.getNotificationButtonHaveMessage()));
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
        String textOldNotificationMessage = windowNotification.getTextOldNotificationMessage();
        System.out.println(textOldNotificationMessage);
        CurrentMeetingPage currentMeetingPage = windowNotification.clickLinkSittingNotificationMessage();

        //Todo проверка части строк Тестовая комиссия № из уведомления и из строки статус. можно просто по номеру.
        //  System.out.println(currentMeetingPage.getPartOfTextStatusField(currentMeetingPage.getTextStatusField()));
        //assertEquals("Заседание на созданно, либо не осуществлен переход на форму запланированного заседания",textOldNotificationMessage, currentMeetingPage.getPartOfTextStatusField());
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












