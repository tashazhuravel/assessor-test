import dataBase.AssesorService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
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
        WindowUserAccount windowUserAccount = mainPage.clickButtonUserAccount();
        assertTrue("Не открылось диалоговое окно", isElementPresent(windowUserAccount.getHeaderWindowUserAccount()));
        assertThat("Заголовок Диалогового окна не 'Учётная запись пользователя'.", windowUserAccount.getHeaderWindowUserAccountText(), containsString("Учётная запись пользователя"));
        assertThat("Неверное ФИО пользователя", windowUserAccount.getUserFIOFieldText().getText(), containsString(fioUserAccount));

        mainPage.clickButtonUserAccount();
        windowUserAccount.clickCheckboxEnabledShowNewNotificationsMessages();
        windowUserAccount.saveUserAccount();
        if (isElementPresent(mainPage.getNotificationMessageButton())){
            WindowNotification windowNotification = mainPage.clickButtonNotification();
            assertFalse("Окно содержит новые уведомления или непрочитанные системные уведомления",isElementPresent(windowNotification.getHaveNewNotificationMessage()));
            assertFalse("Окно содержит прочитанные системные уведомления",isElementPresent(windowNotification.getHaveOldSystemNotificationMessage()));
            windowNotification.clickCloseButton();
        }
        else{
            WindowNotification windowNotification = mainPage.clickButtonNotification();
            assertTrue("Нет новых сообщений", isElementFind(mainPage.getNotificationButtonHaveMessage()));
            mainPage.clickNotificationButtonHaveNewMessage();
            assertTrue("Окно содержит нетолько новые системные уведомления, либо нет уведомлений", isElementPresent(windowNotification.getHaveNewSystemNotificationMessage()));
            assertFalse("Окно содержит нетолько новые системные уведомления",isElementPresent(windowNotification.getHaveNewNotificationMessage()));
            windowNotification.clickCloseButton();
        }

        mainPage.clickButtonUserAccount();
        windowUserAccount.closeWindowUserAccountByButton();

        mainPage.clickButtonUserAccount();
        windowUserAccount.closeWindowUserAccountByX();


        //Todo добавить проверку чекбоксов Уведомлений.  у чекбокса "Показывать уведомления о новых сообщениях:" в свойстве checked появляется disabled, если снята галка "Включить уведомления"
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

        assertTrue(isElementFind(windowNotification.getHaveNewNotificationMessage()));
        assertTrue("Не удалось открыть Уведомления, либо нет новых уведомлений", isElementPresent(windowNotification.getHaveNewNotificationMessage()));
        windowNotification.clickNewNotificationMessage();

        assertTrue(isElementFind(windowNotification.getHaveOldNotificationMessage()));
        assertTrue("Не удалось открыть Уведомления, либо нет новых уведомлений", isElementPresent(windowNotification.getHaveOldNotificationMessage()));
        String textOldNotificationMessage = windowNotification.getNumberSittingFromNottificationMessage();
        System.out.println(textOldNotificationMessage);
        CurrentMeetingPage currentMeetingPage = windowNotification.clickLinkSittingNotificationMessage();
      //  assertEquals("Заседание на созданно, либо не осуществлен переход на форму запланированного заседания", textOldNotificationMessage, currentMeetingPage.getPartOfTextStatusField(textOldNotificationMessage));
        assertThat("Заседание на созданно, либо не осуществлен переход на форму запланированного заседания", windowNotification.getTextOldNotificationMessage(), containsString(textOldNotificationMessage));
        currentMeetingPage.clickBackOnListSitting();

        mainPage.clickNotificationButtonHaveNewMessage();
        windowNotification.clickClearButton();
        assertFalse("Не удалось открыть Уведомления, либо список содержит уведомления", isElementPresent(windowNotification.getHaveOldNotificationMessage()));
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












