import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import pages.MainPage;
import pages.mainPageTab.PlanningTabPage;
import pages.unallocatedQuestionPage.UnallocatedQuestions;
import pages.window.WindowAboutSystem;
import pages.window.WindowNotification;
import pages.window.WindowUserAccount;
import pages.sittingPage.CurrentMeettingPage;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MainPageTest extends BaseWebDriverTest {

    public MainPageTest(String login, String password, String fioUserAccount, String unallocatedQuestionsStatusField, String sittingPlace) {
        this.login = login;
        this.password = password;
        this.fioUserAccount = fioUserAccount;
        this.unallocatedQuestionsStatusField = unallocatedQuestionsStatusField;
        this.sittingPlace = sittingPlace;

    }

    @Test
    public void authorization() {
        authorizationPage = assessorSite.getAuthorizationPage();
        log.info("Authorization begin");
        MainPage mainPage = authorizationPage.setLogin(login).setPassword(password).clickLoginButton();
        assertTrue("Неверный логин/пароль.", isElementFind(mainPage.getDivMainPage()));
        log.info("Authorization complete");
    }

    @Test
    //@Ignore
    public void checkWindowAboutSystemTest() {
        log.info("Проверка модального окна 'О системе'");
        MainPage mainPage = assessorSite.getMainPage();
        waitWhileElementPresent(mainPage.getAboutSystemButton());
        sleepAnyTime(1000L);
        WindowAboutSystem windowAboutSystem = mainPage.clickButtonAboutSystem();
        assertTrue("Не открылось диалоговое окно 'О системе'.", isElementFind(windowAboutSystem.getHeaderWindowAboutSystem()));
        windowAboutSystem.closeWindowAboutSystemByX();
        mainPage.clickButtonAboutSystem();
        windowAboutSystem.closeWindowAboutSystemByButton();

    }

    @Test
    //@Ignore
    public void checkWindowUserAccountTest() {
        log.info("Проверка модального окна 'Учетная запись пользователя'");
        MainPage mainPage = assessorSite.getMainPage();
        waitWhileElementPresent(mainPage.getUserFIOButton());
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
    //@Ignore
    public void checkWindowNotification() {
        log.info("Проверка модального окна 'Оповещения'");
        MainPage mainPage = assessorSite.getMainPage();
        WindowNotification windowNotification;
        assertFalse("Кнопка сообщение не отображена", mainPage.isNotificationMessageButtonDisplay());
        sleepAnyTime(1000L);

        if (isElementFind(mainPage.getNotificationMessageButton())) {
            windowNotification = mainPage.clickButtonNotification();
            assertTrue(".", isElementVisible(mainPage.getNotificationWindow()));
            windowNotification.closeWindowNotificationByX();
        } else if (isElementVisible(mainPage.getNotificationButtonHaveMessage())) {
            assertTrue("Нет новых сообщений", isElementVisible(mainPage.getNotificationButtonHaveMessage()));
            windowNotification = mainPage.clickNotificationButtonHaveNewMessage();

            assertTrue(isElementFind(windowNotification.getHaveNewAnyNotificationMessage()));
            assertTrue("Не удалось открыть Уведомления, либо нет новых уведомлений", isElementPresent(windowNotification.getHaveNewAnyNotificationMessage()));
            windowNotification.clickAnyNewNotificationMessage();

            assertTrue(isElementFind(windowNotification.getHaveOldAnyNotificationMessage()));
            assertTrue("Не удалось открыть Уведомления, либо нет новых уведомлений", isElementPresent(windowNotification.getHaveOldAnyNotificationMessage()));
            String textOldNotificationMessage = windowNotification.getNumberSittingFromNottificationMessage();
            System.out.println(textOldNotificationMessage);
            CurrentMeettingPage currentMeettingPage = windowNotification.clickLinkSittingNotificationMessage();
            // assertEquals("Заседание на созданно, либо не осуществлен переход на форму запланированного заседания", textOldNotificationMessage, currentMeetingPage.getPartOfTextStatusField(textOldNotificationMessage));
            assertThat("Заседание на созданно, либо не осуществлен переход на форму запланированного заседания", currentMeettingPage.getTextInformationField(), containsString(textOldNotificationMessage));
            currentMeettingPage.clickBackOnListSitting();

            mainPage.clickNotificationButtonHaveNewMessage();
            windowNotification.clickClearButton();
            assertFalse("Не удалось открыть Уведомления, либо список содержит уведомления", isElementPresent(windowNotification.getHaveOldAnyNotificationMessage()));
            windowNotification.clickCloseButton();
            assertEquals("Не закрыто окно уведомления.", "hidden", mainPage.getNotificationWindow().getCssValue("visibility"));
        }


    }

    @Test
    //@Ignore
    public void checkUnallocatedQuestionsTest() {
        log.info("Проверка кнопки 'Нераспределенные вопросы'");
        PlanningTabPage planningTabPage = assessorSite.getPlanningPage();
        UnallocatedQuestions unallocatedQuestions = planningTabPage.clickUnallocatedQuestionsButton();

        assertEquals("Не осуществлен переход на форме 'Нераспределенные вопросы'.Неверный текст в поле статус.", unallocatedQuestionsStatusField, unallocatedQuestions.getTextStatusField());
        unallocatedQuestions.clickButtonBack();
    }


}












