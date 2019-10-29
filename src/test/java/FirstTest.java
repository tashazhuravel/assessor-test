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
        //assesorService = new AssesorService(dataBaseConnection.stmt);
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
    @Ignore
    //проверка кнопки "Нераспределенные вопросы"
    public void checkUnllocatedQuestionsTest() {
        PlanningTabPage planningTabPage = assessorSite.getPlanningPage();
        UnllocatedQuestions unllocatedQuestions = planningTabPage.clickUnllocatedQuestionsButton();

        assertEquals("Не осуществлен переход на форме 'Нераспределенные вопросы'.Неверный текст в поле статус.", unllocatedQuestionsStatusField, unllocatedQuestions.getTextStatusField());
    }

    @Test
    //@Ignore
    public void createPlanning() {
        planningTabPage = assessorSite.getPlanningPage();
        planningTabPage.clickTab(MainPage.ETab.PLANNING);
        WindowMeetingScheduling windowMeetingScheduling = planningTabPage.clickPlanningEventButton();
        isElementPresent(windowMeetingScheduling.getHeaderWindowWettingScheduling());

        //--проверка номера заседания
        String numberSitting = windowMeetingScheduling.getSittingNumberText();
        assertFalse("Заседание с таким номером уже существует.", planningTabPage.getAllNumberCommitteeButton().stream().anyMatch(item -> numberSitting.equals(item.getText())));

        //--проверка поля "Место заседания"
        assertEquals("Поле 'Место заседания' не может быть пустым, либо выбрано другое место заседания", sittingPlace, windowMeetingScheduling.getSittingPlaceText());
        //windowMeetingScheduling.clickAndOpenSelectDropDownPlanningPlace();
        //  List<String> select = assesorService.getNamesRoom();//todo вставить пустую строку в начало листа
        //verifyAutocompleteOptions(windowMeetingScheduling.clickAndOpenSelectDropDownPlanningPlace(), select);
        //windowMeetingScheduling.setSelectPlanningPlace("Переговорная");
        System.out.println("Город" + windowMeetingScheduling.getCityFieldText());
        assertEquals("Поле Город содержит текст", StringUtils.EMPTY, windowMeetingScheduling.getCityFieldText());
        windowMeetingScheduling.typeCityField("Витебск, пр-т Строителей 11а");

        //--Дата заседания
        // windowMeetingScheduling.clearDateFieldText();
        assertNotEquals("Поле Дата не может быть пустым", StringUtils.EMPTY, windowMeetingScheduling.getDateFieldText());
        assertEquals("По умолчанию должна быть отображена текущая дата", windowMeetingScheduling.getDateAsString(), windowMeetingScheduling.getDateFieldText());
        windowMeetingScheduling.clickCalendarButton();
        assertFalse("Не открылся календарь, либо нет данных внутри календаря", windowMeetingScheduling.emptyCalendar());
        windowMeetingScheduling.clickDateInCalendar();
        System.out.println(windowMeetingScheduling.getDateFieldText());
        windowMeetingScheduling.clickCalendarButton();
        windowMeetingScheduling.clickTodayButton();
        System.out.println(windowMeetingScheduling.getDateFieldText());

        //--Время начала заседания
        System.out.println(windowMeetingScheduling.getTimeStartText());
        assertNotEquals("Поле время начала заседания не может быть пустым",StringUtils.EMPTY,windowMeetingScheduling.getTimeStartText());
        windowMeetingScheduling.clickAndOpenDropDownSelectSittingTimeStart();
        windowMeetingScheduling.clickTimeStartInDropDown();
        System.out.println(windowMeetingScheduling.getTimeStartText());

        //--Время окончания заседания
        System.out.println(windowMeetingScheduling.getTimeEndText());
        assertNotEquals("Поле время окончания заседания не может быть пустым",StringUtils.EMPTY, windowMeetingScheduling.getTimeEndText());
        windowMeetingScheduling.clickAndOpenSelectSittingTimeEnd();
        windowMeetingScheduling.clickTimeEndInDropDown();
        System.out.println(windowMeetingScheduling.getTimeEndText());

        //--Список участников
        windowMeetingScheduling.getParticipantList();

        //--Сохранение запланированного заседания
        CurrentMeetingPage currentMeetingPage = windowMeetingScheduling.clickSaveButtonPlanning();
        assertEquals("Заседание на созданно, либо не осуществлен переход на форму запланированного заседания",String.format("Тестовая комиссия. %s. №%s. Очно-заочное. \n",windowMeetingScheduling.getDateAsString(),numberSitting) +
                "Секретарь: Секретарева И.О.",currentMeetingPage.getTextStatusField());

    }

    @Test
    @Ignore
    public void checkCanselAndCloseButtonPlanning() {
        planningTabPage = assessorSite.getPlanningPage();
        planningTabPage.clickTab(MainPage.ETab.PLANNING);
        WindowMeetingScheduling windowMeetingScheduling = planningTabPage.clickPlanningEventButton();
        windowMeetingScheduling.clickCancelButtonPlanningSitting();
        planningTabPage.clickPlanningEventButton();
        windowMeetingScheduling.clickCloseButtonPlanningSitting();
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


