import dataBase.AssessorService;
import org.apache.commons.lang3.StringUtils;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebElement;
import pages.MainPage;
import pages.window.WindowSittingPlanning;
import pages.sittingPage.CurrentMeettingPage;
import utils.DateUtil;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PlaningSittingTest extends BaseWebDriverTest {

    public PlaningSittingTest(String login, String password, String fioUserAccount, String unallocatedQuestionsStatusField, String sittingPlace) {
        this.login = login;
        this.password = password;
        this.fioUserAccount = fioUserAccount;
        this.sittingPlace = sittingPlace;

    }

    @Test
    public void authorization() {
        authorizationPage = assessorSite.getAuthorizationPage();
        log.info("Authorization begin");
        authorizationPage.setLogin(login).setPassword(password).clickLoginButton();
        assertEquals("Неверный логин/пароль.", authorizationPage.getElementsFromMainPage().size(), 1);
        log.info("Authorization complete");
    }

    @Test
    //@Ignore
    public void createPlanning() {
        log.info("Тест-кейс. Проверка модального окна 'Планирование заседания'");
        assessorService = new AssessorService(dataBaseConnection.stmt);
        planningTabPage = assessorSite.getPlanningPage();
        planningTabPage.clickTab(MainPage.ETab.PLANNING);
        WindowSittingPlanning windowSittingPlanning = planningTabPage.clickPlanningEventButton();
        assertTrue("Окно Планирование заседания не отрылося", isElementPresent(windowSittingPlanning.getHeaderWindowWettingScheduling()));

        log.info("Проверка поля Номер");
        String numberSitting = windowSittingPlanning.getSittingNumberText();
        assertFalse("Заседание с таким номером уже существует.", planningTabPage.getAllNumberCommitteeButton().stream().anyMatch(item -> numberSitting.equals(item.getText())));

        log.info("Проверка поля 'Место заседания'");
        assertEquals("Поле 'Место заседания' не может быть пустым, либо выбрано другое место заседания", sittingPlace, windowSittingPlanning.getSittingPlaceText());
        // windowMeetingScheduling.clickAndOpenSelectDropDownPlanningPlace();
        List<String> select = assessorService.getNamesRoom();
        verifyAutocompleteOptions(windowSittingPlanning.clickAndOpenSelectDropDownPlanningPlace(), select);
        windowSittingPlanning.setSelectPlanningPlace("Переговорная");
        assertEquals("Поле Город содержит текст", StringUtils.EMPTY, windowSittingPlanning.getCityFieldText());
        windowSittingPlanning.typeCityField("Витебск, пр-т Строителей 11а");
        log.info("Город" + windowSittingPlanning.getCityFieldText());

        log.info("Проверка поля 'Дата'");
        assertNotEquals("Поле Дата не может быть пустым", StringUtils.EMPTY, windowSittingPlanning.getDateFieldText());
        assertEquals("По умолчанию должна быть отображена текущая дата", DateUtil.getCurrentDateAsString(), windowSittingPlanning.getDateFieldText());
        windowSittingPlanning.clickCalendarButton();
        assertFalse("Не открылся календарь, либо нет данных внутри календаря", windowSittingPlanning.emptyCalendar());
        windowSittingPlanning.clickDateInCalendar();
        log.info(windowSittingPlanning.getDateFieldText());
        windowSittingPlanning.clickCalendarButton().clickTodayButton();
        log.info(windowSittingPlanning.getDateFieldText());

        //--Время начала заседания
        log.info(windowSittingPlanning.getTimeStartText());
        assertNotEquals("Поле время начала заседания не может быть пустым", StringUtils.EMPTY, windowSittingPlanning.getTimeStartText());
        windowSittingPlanning.clickAndOpenDropDownSelectSittingTimeStart();
        windowSittingPlanning.clickTimeStartInDropDown();
        log.info(windowSittingPlanning.getTimeStartText());

        //--Время окончания заседания
        System.out.println(windowSittingPlanning.getTimeEndText());
        assertNotEquals("Поле время окончания заседания не может быть пустым", StringUtils.EMPTY, windowSittingPlanning.getTimeEndText());
        windowSittingPlanning.clickAndOpenSelectSittingTimeEnd();
        windowSittingPlanning.clickTimeEndInDropDown();
        log.info(windowSittingPlanning.getTimeEndText());

        //--Список участников
        List<String> selectParticipant = assessorService.getFIOParticipantSitting();
        log.info(selectParticipant);
        Collections.swap(selectParticipant, 2,4);
        Collections.swap(selectParticipant, 1,5);
        Collections.swap(selectParticipant, 4,5);
        log.info(selectParticipant);
        verifyAutocompleteOptionsText(changeWordPressSymbol(windowSittingPlanning.getParticipantsList()), selectParticipant);


        //--Сохранение запланированного заседания
        CurrentMeettingPage currentMeettingPage = windowSittingPlanning.clickSaveButtonPlanning();
        String selectSecretary = assessorService.getFIOSecretaryOfCommittee().get(0);
        assertThat(".", String.format("Тестовая комиссия autoTests. %s. №%s. Очно-заочное. \nСекретарь: %s", DateUtil.getCurrentDateAsString(), numberSitting, selectSecretary), containsString(currentMeettingPage.getTextInformationField()));
    }

    @Test
    @Ignore
    public void checkCancelAndCloseButtonPlanning() {
        log.info("Тест-кейс.Проверка кнопок открыть, закрыть, отмена 'Планирование заседания'");
        planningTabPage = assessorSite.getPlanningPage();
        planningTabPage.clickTab(MainPage.ETab.PLANNING);
        WindowSittingPlanning windowSittingPlanning = planningTabPage.clickPlanningEventButton();
        windowSittingPlanning.clickCancelButtonPlanningSitting();
        planningTabPage.clickPlanningEventButton();
        windowSittingPlanning.clickCloseButtonPlanningSitting();
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