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

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class FirstTest extends BaseWebDriverTest {
    @Before
    public void setUp() {
    }

    @Test
    public void authorizationAcceptTest() {
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
        assertEquals("Неверное ФИО пользователя.", FIO_USER_ACCOUNT, windowUserAccount.getTextByUserFIOField());
        windowUserAccount.saveUserAccount();
        mainPage.clickButtonUserAccount();
        windowUserAccount.closeWindowUserAccountByButton();
        mainPage.clickButtonUserAccount();
        windowUserAccount.closeWindowUserAccountByX();
    }

    @Test
    @Ignore
    //проверка кнопки "Нераспределенные вопросы"
    public void checkUnllocatedQuestionsTest() {
        PlanningTabPage planningTabPage = assessorSite.getPlanningPage();
        UnllocatedQuestions unllocatedQuestions = planningTabPage.clickUnllocatedQuestionsButton();

        assertEquals("Не осуществлен переход на форме 'Нераспределенные вопросы'.Неверный текст в поле статус.", UNLLOCATED_QUESTIONS_STATUS_FIELD, unllocatedQuestions.getTextStatusField());
    }

    @Test
    //@Ignore
    public void createPlanning() {
        planningTabPage = assessorSite.getPlanningPage();
        planningTabPage.clickTab(MainPage.ETab.PLANNING);
        WindowMeetingScheduling windowMeetingScheduling = planningTabPage.clickPlanningEventButton();
        isElementPresent(windowMeetingScheduling.getHeaderWindowWettingScheduling());

        //--проверка номера заседания
        // assertNotSame(planningTabPage.getAllNumberSittingCommittee(), windowMeetingScheduling.getSittingNumberText());
        assertFalse("Заседание с таким номером уже существует.", planningTabPage.getAllNumberCommitteeButton().stream().anyMatch(item -> windowMeetingScheduling.getSittingNumberText().equals(item.getText())));

        assertEquals("Поле 'Место заседания' не может быть пустым, либо выбрано другое место заседания", "переговорная 1", windowMeetingScheduling.getSittingPlaceText());
        windowMeetingScheduling.clickAndOpenSelectDropDownPlanningPlace();
       /* List<String> select = Arrays.asList("", "Small meeting room", "Большой кабинет",
                "Зал для совещаний Главного корпуса", "Зал заседаний", "Кабинет", "Комната для заседаний",
                "Переговорная", "переговорная 1", "Переговорная комната");
        verifyAutocompleteOptions(windowMeetingScheduling.clickAndOpenSelectDropDownPlanningPlace(), select);*/
        assertEquals("Поле Город содержит текст", "",windowMeetingScheduling.getCityFieldText());
    }





       /* //--ошибочные данные
        windowMeetingScheduling.savePlanning();
        //isElementPresent(windowMeetingScheduling.)
        ErrorByMeetingScheduling errorByMeetingScheduling = windowMeetingScheduling.getErrorByMeetingScheduling();
        assertEquals(errorByMeetingScheduling.getErrorMassageText(), ErrorType.NUMBER_SITTING_EMPTY_AND_EXIST.getLabel());}*/



  /*  @Test
    @Ignore
    public void checkSelectPlanningPlace() {
        planningTabPage = assessorSite.getPlanningPage();
        planningTabPage.clickTab(MainPage.ETab.PLANNING);
        planningTabPage.clickPlanningEventButton();
}*/

}


