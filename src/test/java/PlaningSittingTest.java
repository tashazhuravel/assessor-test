import dataBase.AssessorService;
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

public class PlaningSittingTest extends BaseWebDriverTest {

    public PlaningSittingTest(String login, String password, String fioUserAccount, String unallocatedQuestionsStatusField, String sittingPlace) {
        this.login = login;
        this.password = password;
        this.fioUserAccount = fioUserAccount;
        this.sittingPlace = sittingPlace;

    }

    @Before
    public void setUp() {
        assessorService = new AssessorService(dataBaseConnection.stmt);
        authorizationPage = assessorSite.getAuthorizationPage();
        log.info("Authorization begin");
        authorizationPage.setLogin(login).setPassword(password).clickLoginButton();
        assertEquals("Неверный логин/пароль.", authorizationPage.getElementsFromMainPage().size(), 1);
        log.info("Authorization complete");
    }

    @Test
    @Ignore
    public void createPlanning() {
        log.info("Проверка модального окна 'Планирование заседания'");
        planningTabPage = assessorSite.getPlanningPage();
        planningTabPage.clickTab(MainPage.ETab.PLANNING);
        WindowMeetingScheduling windowMeetingScheduling = planningTabPage.clickPlanningEventButton();
        assertTrue("Окно Планирование заседания не отрылося", isElementPresent(windowMeetingScheduling.getHeaderWindowWettingScheduling()));

        log.info("Проверка поля Номер");
        String numberSitting = windowMeetingScheduling.getSittingNumberText();
        assertFalse("Заседание с таким номером уже существует.", planningTabPage.getAllNumberCommitteeButton().stream().anyMatch(item -> numberSitting.equals(item.getText())));

        log.info("Проверка поля 'Место заседания'");
        assertEquals("Поле 'Место заседания' не может быть пустым, либо выбрано другое место заседания", sittingPlace, windowMeetingScheduling.getSittingPlaceText());
        // windowMeetingScheduling.clickAndOpenSelectDropDownPlanningPlace();
        List<String> select = assessorService.getNamesRoom();
        verifyAutocompleteOptions(windowMeetingScheduling.clickAndOpenSelectDropDownPlanningPlace(), select);
        windowMeetingScheduling.setSelectPlanningPlace("Переговорная");
        assertEquals("Поле Город содержит текст", StringUtils.EMPTY, windowMeetingScheduling.getCityFieldText());
        windowMeetingScheduling.typeCityField("Витебск, пр-т Строителей 11а");
        System.out.println("Город" + windowMeetingScheduling.getCityFieldText());

        log.info("Проверка поля 'Дата'");
        assertNotEquals("Поле Дата не может быть пустым", StringUtils.EMPTY, windowMeetingScheduling.getDateFieldText());
        assertEquals("По умолчанию должна быть отображена текущая дата", DateUtil.getCurrentDateAsString(), windowMeetingScheduling.getDateFieldText());
        windowMeetingScheduling.clickCalendarButton();
        assertFalse("Не открылся календарь, либо нет данных внутри календаря", windowMeetingScheduling.emptyCalendar());
        windowMeetingScheduling.clickDateInCalendar();
        System.out.println(windowMeetingScheduling.getDateFieldText());
        windowMeetingScheduling.clickCalendarButton().clickTodayButton();
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
        List<String> selectParticipant = assessorService.getFIOParticipantSitting();
        System.out.println(selectParticipant);
        verifyAutocompleteOptionsText(changeWordPressSymbol(windowMeetingScheduling.getParticipantsList()), selectParticipant);


        //--Сохранение запланированного заседания
        CurrentMeetingPage currentMeetingPage = windowMeetingScheduling.clickSaveButtonPlanning();
        String selectSecretary = assessorService.getFIOSecretaryOfCommittee().get(0);
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