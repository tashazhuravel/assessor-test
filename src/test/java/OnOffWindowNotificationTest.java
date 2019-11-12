import dataBase.AssessorService;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import pages.MainPage;
import pages.window.WindowNotification;
import pages.window.WindowUserAccount;

import static org.junit.Assert.*;

public class OnOffWindowNotificationTest extends BaseWebDriverTest{
    public OnOffWindowNotificationTest(String login, String password, String fioUserAccount, String unllocatedQuestionsStatusField, String sittingPlace) {
        this.login = login;
        this.password = password;
        this.fioUserAccount = fioUserAccount;
        this.unallocatedQuestionsStatusField = unllocatedQuestionsStatusField;
        this.sittingPlace = sittingPlace;

    }

    @Before
    public void setUp() {
        assesorService = new AssessorService(dataBaseConnection.stmt);
        authorizationPage = assessorSite.getAuthorizationPage();
        log.info("Authorization begin");
        authorizationPage.setLogin(login).setPassword(password).clickLoginButton();
        assertEquals("Неверный логин/пароль.", authorizationPage.getElementsFromMainPage().size(), 1);
        log.info("Authorization complete");
    }

    @Test
    // @Ignore
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
        //Не удалять! Этот sleep необходим для ожидания обновления страницы после выполнения действия saveUserAccount, иначе не успевает отрабатываеть первое условие if.
        try {
            Thread.sleep(5000);
        } catch (
                InterruptedException e) {
            e.printStackTrace();
        }


        WindowNotification windowNotification;
        if (isElementFind(mainPage.getNotificationMessageButton())) {
            windowNotification = mainPage.clickButtonNotification();
            assertFalse("Окно содержит новые уведомления или непрочитанные системные уведомления", isElementPresent(windowNotification.getHaveNewAnyNotificationMessage()));
            assertFalse("Окно содержит прочитанные системные уведомления", isElementPresent(windowNotification.getHaveOldSystemNotificationMessage()));
        } else {
            assertTrue("Нет новых сообщений", isElementVisible(mainPage.getNotificationButtonHaveMessage()));
            windowNotification = mainPage.clickNotificationButtonHaveNewMessage();

            assertTrue("Окно содержит нетолько новые системные уведомления, либо нет уведомлений", isElementPresent(windowNotification.getHaveNewSystemNotificationMessage()));
            assertFalse("Окно содержит нетолько новые системные уведомления", isElementPresent(windowNotification.getHaveNewNotificationMessage()));
        }

        windowNotification.clickCloseButton();

        mainPage.clickButtonUserAccount();
        WebElement checkboxEnabledNotificationsMessages = windowUserAccount.getCheckboxEnabledNotifications();


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
        assertFalse("Активна кнопка уведомления", mainPage.isNotificationMessageButtonDisplay());
        assertFalse("Активна кнопка Есть новые уведомления", isElementVisible(mainPage.getNotificationButtonHaveMessage()));
    }

}
