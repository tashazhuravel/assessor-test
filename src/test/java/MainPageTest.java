import org.junit.FixMethodOrder;
import org.junit.Ignore;
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
        assertTrue("authorization.wrong.login.or.login", isElementFind(mainPage.getDivMainPage()));
        log.info("Authorization complete");
    }

    @Test
    //@Ignore
    public void checkWindowAboutSystemTest() {
        log.info("test.case.checking.the.about.system.modal.window");
        MainPage mainPage = assessorSite.getMainPage();
        waitWhileElementPresent(mainPage.getAboutSystemButton());
        sleepAnyTime(1000L);
        WindowAboutSystem windowAboutSystem = mainPage.clickButtonAboutSystem();
        assertTrue("the.title.was.not.found.or.the.about.system.dialog.box.did.not.open", isElementFind(windowAboutSystem.getHeaderWindowAboutSystem()));
        windowAboutSystem.closeWindowAboutSystemByX();
        mainPage.clickButtonAboutSystem();
        windowAboutSystem.closeWindowAboutSystemByButton();

    }

    @Test
    //@Ignore
    public void checkWindowUserAccountTest() {
        log.info("test.case.checking.the.user.account.modal.window");
        MainPage mainPage = assessorSite.getMainPage();
        waitWhileElementPresent(mainPage.getUserFIOButton());
        WindowUserAccount windowUserAccount = mainPage.clickButtonUserAccount();

        assertTrue("Не открылось диалоговое окно или не найден заголовок окна.", isElementFind(windowUserAccount.getHeaderWindowUserAccount()));
        assertThat("Заголовок Диалогового окна не 'Учётная запись пользователя'.", windowUserAccount.getHeaderWindowUserAccountText(), containsString("Учётна запись пользователя"));
        assertThat("invalid.user.name", windowUserAccount.getUserFIOFieldText().getText(), containsString(fioUserAccount));
        windowUserAccount.saveUserAccount();

        mainPage.clickButtonUserAccount();
        windowUserAccount.closeWindowUserAccountByButton();

        mainPage.clickButtonUserAccount();
        windowUserAccount.closeWindowUserAccountByX();
    }

    @Test
    //@Ignore
    public void checkWindowNotification() {
        log.info("test.case.checking.the.alerts.modal.window");
        MainPage mainPage = assessorSite.getMainPage();
        WindowNotification windowNotification;
        assertFalse("notification.button.not.found", mainPage.isNotificationMessageButtonDisplay());
        sleepAnyTime(1000L);

        if (isElementFind(mainPage.getNotificationMessageButton())) {
            windowNotification = mainPage.clickButtonNotification();
            assertTrue("the.alerts.dialog.box.did.not.open.or.this.functionality.is.not.enabled.in.main.php.settings", isElementVisible(mainPage.getNotificationWindow()));
            windowNotification.closeWindowNotificationByX();
        } else if (isElementVisible(mainPage.getNotificationButtonHaveMessage())) {
            assertTrue("no.new.posts", isElementVisible(mainPage.getNotificationButtonHaveMessage()));
            windowNotification = mainPage.clickNotificationButtonHaveNewMessage();

            assertTrue(isElementFind(windowNotification.getHaveNewAnyNotificationMessage()));
            assertTrue("failed.to.open.the.notifications.dialog.box,or.no.new.notifications", isElementPresent(windowNotification.getHaveNewAnyNotificationMessage()));
            windowNotification.clickAnyNewNotificationMessage();

            assertTrue(isElementFind(windowNotification.getHaveOldAnyNotificationMessage()));
            assertTrue("failed.to.open.the.notifications.dialog.box,or.no.new.notifications", isElementPresent(windowNotification.getHaveOldAnyNotificationMessage()));
            String textOldNotificationMessage = windowNotification.getNumberSittingFromNottificationMessage();
            System.out.println(textOldNotificationMessage);
            CurrentMeettingPage currentMeettingPage = windowNotification.clickLinkSittingNotificationMessage();
            // assertEquals("Заседание на созданно, либо не осуществлен переход на форму запланированного заседания", textOldNotificationMessage, currentMeetingPage.getPartOfTextStatusField(textOldNotificationMessage));
            assertThat("the.meeting.was.created.or.the.transition.to.the.form.of.the.planned.meeting.is.not.carried.out", currentMeettingPage.getTextInformationField(), containsString(textOldNotificationMessage));
            currentMeettingPage.clickBackOnListSitting();

            mainPage.clickNotificationButtonHaveNewMessage();
            windowNotification.clickClearButton();
            assertFalse("failed.to.open.notifications.or.the.list.contains.notifications", isElementPresent(windowNotification.getHaveOldAnyNotificationMessage()));
            windowNotification.clickCloseButton();
            assertEquals("notification.window.not.closed", "hidden", mainPage.getNotificationWindow().getCssValue("visibility"));
        }


    }

    @Test
    //@Ignore
    public void checkUnallocatedQuestionsTest() {
        log.info("test.case.check.button.unassigned.questions");
        PlanningTabPage planningTabPage = assessorSite.getPlanningPage();
        UnallocatedQuestions unallocatedQuestions = planningTabPage.clickUnallocatedQuestionsButton();

        assertEquals("the.transition.to.the.unassigned.questions.form.has.not.been.completed.invalid.text.in.the.info.board", unallocatedQuestionsStatusField, unallocatedQuestions.getTextStatusField());
        unallocatedQuestions.clickBackOnListSitting();
    }


}












