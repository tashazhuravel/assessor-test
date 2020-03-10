import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebElement;
import pages.MainPage;
import pages.window.WindowNotification;
import pages.window.WindowUserAccount;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OnOffWindowNotificationTest extends BaseWebDriverTest{
    public OnOffWindowNotificationTest(String login, String password, String fioUserAccount, String unllocatedQuestionsStatusField, String sittingPlace) {
        this.login = login;
        this.password = password;
        this.fioUserAccount = fioUserAccount;
        this.unallocatedQuestionsStatusField = unllocatedQuestionsStatusField;
        this.sittingPlace = sittingPlace;

    }

    @Test
    public void  authorization() {
        authorizationPage = assessorSite.getAuthorizationPage();
        log.info("Authorization begin");
        authorizationPage.setLogin(login).setPassword(password).clickLoginButton();
        assertEquals("Неверный логин/пароль.", authorizationPage.getElementsFromMainPage().size(), 1);
        log.info("Authorization complete");
    }

    @Test
    // @Ignore
    public void checkWindowUserAccountWithWindowNotification() {
        log.info("Тест-кейс. Проверка диалогового окна 'Уведомлений'. Когда в Учетной запииси пользователя снят чек-бокс Показывать уведомления о новых сообщениях или Включить уведомления");
        MainPage mainPage = assessorSite.getMainPage();
        waitWhileElementPresent(mainPage.getUserFIOButton());
        WindowUserAccount windowUserAccount = mainPage.clickButtonUserAccount();

        assertTrue("Не открылось диалоговое окно или не найден заголовок 'Учетная запись пользователя'", isElementFind(windowUserAccount.getHeaderWindowUserAccount()));
        WebElement checkboxEnabledShowNewNotificationsMessages = windowUserAccount.getCheckboxEnabledShowNewNotificationsMessages();

        // Todo---провекрка нажат чекбокс Показывать уведомления о новых сообщениях
        if (isCheckboxSelected(checkboxEnabledShowNewNotificationsMessages)) {
            windowUserAccount.clickCheckboxEnabledShowNewNotificationsMessages();
           sleepAnyTime(1000L);
            windowUserAccount.saveUserAccount();
        } else {
            windowUserAccount.saveUserAccount();
        }
        //Не удалять! Этот sleep необходим для ожидания обновления страницы после выполнения действия saveUserAccount, иначе не успевает отрабатываеть первое условие if.
        sleepAnyTime(5000L);


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
            sleepAnyTime(2000L);
            windowUserAccount.getCheckboxEnabledShowNewNotificationsMessages().isEnabled();
            assertTrue("Активен чекбокс 'Включить уведомления'", windowUserAccount.getCheckboxEnabledNotifications().isEnabled());

            windowUserAccount.saveUserAccount();
        } else {
            windowUserAccount.saveUserAccount();
        }
        assertFalse("Активна кнопка 'Уведомления'", mainPage.isNotificationMessageButtonDisplay());
        assertTrue("Активна кнопка 'Есть новые Уведомления'", isElementVisible(mainPage.getNotificationButtonHaveMessage()));
    }

}
