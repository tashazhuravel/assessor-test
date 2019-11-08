import dataBase.AssesorService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import pages.CurrentMeetingPage;
import pages.MainPage;
import pages.UnallocatedQuestions;
import pages.mainPageTab.PlanningTabPage;
import pages.window.WindowAboutSystem;
import pages.window.WindowNotification;
import pages.window.WindowUserAccount;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class FirstTest extends BaseWebDriverTest {

    public FirstTest(String login, String password, String fioUserAccount, String unllocatedQuestionsStatusField, String sittingPlace) {
        this.login = login;
        this.password = password;
        this.fioUserAccount = fioUserAccount;
        this.unallocatedQuestionsStatusField = unllocatedQuestionsStatusField;
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
        try {
            Thread.sleep(1000);
        } catch (
                InterruptedException e) {
            e.printStackTrace();
        }
        WindowUserAccount windowUserAccount = mainPage.clickButtonUserAccount();

        assertTrue("Не открылось диалоговое окно или не найден заголовок окна.", isElementFind(windowUserAccount.getHeaderWindowUserAccount()));
        assertThat("Заголовок Диалогового окна не 'Учётная запись пользователя'.", windowUserAccount.getHeaderWindowUserAccountText(), containsString("Учётная запись пользователя"));
        assertThat("Неверное ФИО пользователя", windowUserAccount.getUserFIOFieldText().getText(), containsString(fioUserAccount));
        windowUserAccount.saveUserAccount();

        mainPage.clickButtonUserAccount();
        windowUserAccount.closeWindowUserAccountByButton();

        mainPage.clickButtonUserAccount();
        windowUserAccount.closeWindowUserAccountByX();
    }

    @Test
    // @Ignore
    //Todo добавить проверку чекбоксов Уведомлений.  у чекбокса "Показывать уведомления о новых сообщениях:" в свойстве checked появляется disabled, если снята галка "Включить уведомления"
    public void checkWindowUserAccountWithWindowNotification() {
        MainPage mainPage = assessorSite.getMainPage();
        waitWhileElementPresent(mainPage.getUserFIOButton());
        WindowUserAccount windowUserAccount = mainPage.clickButtonUserAccount();

        assertTrue("Не открылось диалоговое окно", isElementFind(windowUserAccount.getHeaderWindowUserAccount()));
        WebElement checkboxEnabledShowNewNotificationsMessages = windowUserAccount.getCheckboxEnabledShowNewNotificationsMessages();

        // Todo---провекрка нажат чекбокс Показывать уведомления о новых сообщениях
        if (isCheckboxSelected(checkboxEnabledShowNewNotificationsMessages)) {
            windowUserAccount.clickCheckboxEnabledShowNewNotificationsMessages();
            try {
                Thread.sleep(1000);
            } catch (
                    InterruptedException e) {
                e.printStackTrace();
            }
            windowUserAccount.saveUserAccount();
        } else {
            windowUserAccount.saveUserAccount();
        }


        // Todo--провекрка отображения Системных сообщений, когда снят чекбокс Показывать уведомления о новых сообщениях
        WindowNotification windowNotification;
        if (isElementFind(mainPage.getNotificationMessageButton())) {
            windowNotification = mainPage.clickButtonNotification();
            try {
                Thread.sleep(5000);
            } catch (
                    InterruptedException e) {
                e.printStackTrace();
            }
            assertFalse("Окно содержит новые уведомления или непрочитанные системные уведомления", isElementPresent(windowNotification.getHaveNewAnyNotificationMessage()));
            assertFalse("Окно содержит прочитанные системные уведомления", isElementPresent(windowNotification.getHaveOldSystemNotificationMessage()));
        } else {
            assertTrue("Нет новых сообщений", isElementFind(mainPage.getNotificationButtonHaveMessage()));
            windowNotification = mainPage.clickNotificationButtonHaveNewMessage();

            assertTrue("Окно содержит нетолько новые системные уведомления, либо нет уведомлений", isElementPresent(windowNotification.getHaveNewSystemNotificationMessage()));
            assertFalse("Окно содержит нетолько новые системные уведомления", isElementPresent(windowNotification.getHaveNewNotificationMessage()));
        }

        windowNotification.clickCloseButton();

        mainPage.clickButtonUserAccount();
        WebElement checkboxEnabledNotificationsMessages = windowUserAccount.getCheckboxEnabledNotifications();

        // Todo---провекрка нажат чекбокс Включить уведомления
        if (isCheckboxSelected(checkboxEnabledNotificationsMessages)) {
            windowUserAccount.clickCheckboxEnabledNotifications();
            try {
                Thread.sleep(500);
            } catch (
                    InterruptedException e) {
                e.printStackTrace();
            }
            assertFalse("Активен чекбокс 'Показывать уведомления о новых сообщениях:'", windowUserAccount.getCheckboxEnabledShowNewNotificationsMessages().isEnabled());

            windowUserAccount.saveUserAccount();
        } else {
            windowUserAccount.saveUserAccount();
        }
        assertTrue("Активна кнопка уведомления", mainPage.isNotificationMessageButtonDisplay());
        assertFalse("Активна кнопка Есть новые уведомления", isElementFind(mainPage.getNotificationButtonHaveMessage()));
    }

    @Test
    @Ignore
    public void checkWindowNotification() {
        MainPage mainPage = assessorSite.getMainPage();
        WindowNotification windowNotification = mainPage.clickButtonNotification();

        assertTrue(".", isElementVisible(mainPage.getNotificationWindow()));
        windowNotification.closeWindowNotificationByX();

        assertTrue("Нет новых сообщений", isElementFind(mainPage.getNotificationButtonHaveMessage()));
        mainPage.clickNotificationButtonHaveNewMessage();

        assertTrue(isElementFind(windowNotification.getHaveNewAnyNotificationMessage()));
        assertTrue("Не удалось открыть Уведомления, либо нет новых уведомлений", isElementPresent(windowNotification.getHaveNewAnyNotificationMessage()));
        windowNotification.clickAnyNewNotificationMessage();

        assertTrue(isElementFind(windowNotification.getHaveOldAnyNotificationMessage()));
        assertTrue("Не удалось открыть Уведомления, либо нет новых уведомлений", isElementPresent(windowNotification.getHaveOldAnyNotificationMessage()));
        String textOldNotificationMessage = windowNotification.getTextOldNotificationMessage();
        System.out.println(textOldNotificationMessage);
        CurrentMeetingPage currentMeetingPage = windowNotification.clickLinkSittingNotificationMessage();
        //  assertEquals("Заседание на созданно, либо не осуществлен переход на форму запланированного заседания", textOldNotificationMessage, currentMeetingPage.getPartOfTextStatusField(textOldNotificationMessage));
        assertThat("Заседание на созданно, либо не осуществлен переход на форму запланированного заседания", windowNotification.getTextOldNotificationMessage(), containsString(currentMeetingPage.getTextStatusField()));
        currentMeetingPage.clickBackOnListSitting();

        mainPage.clickNotificationButtonHaveNewMessage();
        windowNotification.clickClearButton();
        assertFalse("Не удалось открыть Уведомления, либо список содержит уведомления", isElementPresent(windowNotification.getHaveOldAnyNotificationMessage()));
        windowNotification.clickCloseButton();
        assertEquals("Не закрыто окно уведомления.", "hidden", mainPage.getNotificationWindow().getCssValue("visibility"));
    }


    @Test
    @Ignore
    //проверка кнопки "Нераспределенные вопросы"
    public void checkUnallocatedQuestionsTest() {
        PlanningTabPage planningTabPage = assessorSite.getPlanningPage();
        UnallocatedQuestions unallocatedQuestions = planningTabPage.clickUnallocatedQuestionsButton();

        assertEquals("Не осуществлен переход на форме 'Нераспределенные вопросы'.Неверный текст в поле статус.", unallocatedQuestionsStatusField, unallocatedQuestions.getTextStatusField());
    }


}












