import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import pages.MainPage;
import pages.UnllocatedQuestions;
import pages.errorWindow.ErrorByMeetingScheduling;
import pages.errorWindow.ErrorType;
import pages.mainPageTab.PlanningTabPage;
import pages.window.WindowAboutSystem;
import pages.window.WindowMeetingScheduling;
import pages.window.WindowUserAccount;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class FirstTest extends BaseWebDriverTest {
    @Before
    public void setUp() {
    }

    @Test
    public void authorizationAcceptTest() {
        assertEquals(authorizationPage.getElementsFromMainPage().size(), 1);
        takeScreenshot("authorization");
    }

    @Test
    @Ignore
    //проверка модального окна "О системе"
    public void checkWindowAboutSystemTest() {
        MainPage mainPage = assessorSite.getMainPage();
        WindowAboutSystem windowAboutSystem = mainPage.clickButtonAboutSystem();
        isElementPresent(windowAboutSystem.getHeaderWindowAboutSystem());
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

        isElementPresent(windowUserAccount.getHeaderWindowUserAccount());
        assertEquals(windowUserAccount.getUserFIOFieldText().size(), 1);
        assertEquals(FIO_USER_ACCOUNT, windowUserAccount.getTextByUserFIOField());
        windowUserAccount.saveUserAccount();
        mainPage.clickButtonUserAccount();
        windowUserAccount.closeWindowUserAccountByButton();
        mainPage.clickButtonUserAccount();
        windowUserAccount.closeWindowUserAccountByX();
    }

    @Test
    //проверка кнопки "Нераспределенные вопросы"
    public void checkUnllocatedQuestionsTest(){
        PlanningTabPage planningTabPage = assessorSite.getPlanningPage();
        UnllocatedQuestions unllocatedQuestions = planningTabPage.clickUnllocatedQuestionsButton();

        assertEquals(UNLLOCATED_QUESTIONS,unllocatedQuestions.getTextStatusField());
    }

    @Test
    @Ignore
    public void createPlanning() {
        planningTabPage = assessorSite.getPlanningPage();
        planningTabPage.clickTab(MainPage.ETab.PLANNING);
        WindowMeetingScheduling windowMeetingScheduling = planningTabPage.clickPlanningEventButton();
        isElementPresent(windowMeetingScheduling.getHeaderWindowWettingScheduling());
        //--ошибочные данные
        windowMeetingScheduling.savePlanning();
        //isElementPresent(windowMeetingScheduling.)
        ErrorByMeetingScheduling errorByMeetingScheduling = windowMeetingScheduling.getErrorByMeetingScheduling();
        assertEquals(errorByMeetingScheduling.getErrorMassageText(), ErrorType.NUMBER_SITTING_EMPTY_AND_EXIST.getLabel());

    }

  /*  @Test
    @Ignore
    public void checkSelectPlanningPlace() {
        planningTabPage = assessorSite.getPlanningPage();
        planningTabPage.clickTab(MainPage.ETab.PLANNING);
        planningTabPage.clickPlanningEventButton();

        List<String> select = Arrays.asList("", "Small meeting room", "Большой кабинет",
                "Зал для совещаний Главного корпуса", "Зал заседаний", "Кабинет", "Комната для заседаний",
                "Переговорная", "переговорная 1", "Переговорная комната");
       // verifyAutocompleteOptions(planningTabPage.getSelectPlanningPlace(), select);
       }*/
}



