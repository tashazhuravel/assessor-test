import dataBase.AssesorService;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import pages.CurrentMeetingPage;
import pages.MainPage;
import pages.window.WindowMeetingScheduling;
import utils.DateUtil;

import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;
import static org.junit.Assert.assertNotEquals;

public class PlaningSittingTest extends BaseWebDriverTest {

    public PlaningSittingTest(String login, String password, String fioUserAccount, String unallocatedQuestionsStatusField, String sittingPlace) {
        this.login = login;
        this.password = password;
        this.fioUserAccount = fioUserAccount;
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
        takeScreenshot("initWebDriver");
    }

    @Test
    @Ignore
    public void createPlanning() {
        planningTabPage = assessorSite.getPlanningPage();
        planningTabPage.clickTab(MainPage.ETab.PLANNING);
        WindowMeetingScheduling windowMeetingScheduling = planningTabPage.clickPlanningEventButton();
        assertTrue("Окошко не отрылося", isElementPresent(windowMeetingScheduling.getHeaderWindowWettingScheduling()));

        //--проверка номера заседания
        String numberSitting = windowMeetingScheduling.getSittingNumberText();
        assertFalse("Заседание с таким номером уже существует.", planningTabPage.getAllNumberCommitteeButton().stream().anyMatch(item -> numberSitting.equals(item.getText())));

        //--проверка поля "Место заседания"
        assertEquals("Поле 'Место заседания' не может быть пустым, либо выбрано другое место заседания", sittingPlace, windowMeetingScheduling.getSittingPlaceText());
        // windowMeetingScheduling.clickAndOpenSelectDropDownPlanningPlace();
        List<String> select = assesorService.getNamesRoom();
        verifyAutocompleteOptions(windowMeetingScheduling.clickAndOpenSelectDropDownPlanningPlace(), select);
        windowMeetingScheduling.setSelectPlanningPlace("Переговорная");
        assertEquals("Поле Город содержит текст", StringUtils.EMPTY, windowMeetingScheduling.getCityFieldText());
        windowMeetingScheduling.typeCityField("Витебск, пр-т Строителей 11а");
        System.out.println("Город" + windowMeetingScheduling.getCityFieldText());

        //--Дата заседания
        // windowMeetingScheduling.clearDateFieldText();
        assertNotEquals("Поле Дата не может быть пустым", StringUtils.EMPTY, windowMeetingScheduling.getDateFieldText());
        assertEquals("По умолчанию должна быть отображена текущая дата", DateUtil.getCurrentDateAsString(), windowMeetingScheduling.getDateFieldText());
        windowMeetingScheduling.clickCalendarButton();
        assertFalse("Не открылся календарь, либо нет данных внутри календаря", windowMeetingScheduling.emptyCalendar());
        windowMeetingScheduling.clickDateInCalendar();
        System.out.println(windowMeetingScheduling.getDateFieldText());
        windowMeetingScheduling.clickCalendarButton();
        windowMeetingScheduling.clickTodayButton();
        System.out.println(windowMeetingScheduling.getDateFieldText());

        //--Время начала заседания
        System.out.println(windowMeetingScheduling.getTimeStartText());
        assertNotEquals("Поле время начала заседания не может быть пустым", StringUtils.EMPTY, windowMeetingScheduling.getTimeStartText());
        windowMeetingScheduling.clickAndOpenDropDownSelectSittingTimeStart();
        windowMeetingScheduling.clickTimeStartInDropDown();
        System.out.println(windowMeetingScheduling.getTimeStartText());

        //--Время окончания заседания
        System.out.println(windowMeetingScheduling.getTimeEndText());
        assertNotEquals("Поле время окончания заседания не может быть пустым", StringUtils.EMPTY, windowMeetingScheduling.getTimeEndText());
        windowMeetingScheduling.clickAndOpenSelectSittingTimeEnd();
        windowMeetingScheduling.clickTimeEndInDropDown();
        System.out.println(windowMeetingScheduling.getTimeEndText());

        //--Список участников
        List<String> selectParticipant = assesorService.getFIOParticipantSitting();
        System.out.println(selectParticipant);
        verifyAutocompleteOptionsText(changeWordPressSymbol(windowMeetingScheduling.getParticipantsList()), selectParticipant);


        //--Сохранение запланированного заседания
        CurrentMeetingPage currentMeetingPage = windowMeetingScheduling.clickSaveButtonPlanning();
        String selectSecretary = assesorService.getFIOSecretaryOfCommittee().get(0);
        assertThat(".", String.format("Тестовая комиссия. %s. №%s. Очно-заочное. \nСекретарь: %s", DateUtil.getCurrentDateAsString(), numberSitting, selectSecretary), containsString(currentMeetingPage.getTextStatusField()));
    }

    @Test
    @Ignore
    public void checkCancelAndCloseButtonPlanning() {
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