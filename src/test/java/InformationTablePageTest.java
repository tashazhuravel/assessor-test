import dataBase.AssessorService;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import pages.AgendaPage;
import pages.InformationTablePage;
import pages.MainPage;
import pages.window.WindowMaximizedIllustration;
import pages.window.WindowMaximizedInformationTable;
import sittingPage.CurrentMeetingPage;
import sittingPage.QuestionList;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class InformationTablePageTest extends BaseWebDriverTest{

    protected static final String STATUS = "Повестка дня утверждена";
    protected static final String STATUS_OPEN = "Заседание открыто";

    public InformationTablePageTest(String login, String password, String fioUserAccount, String unallocatedQuestionsStatusField, String sittingPlace){
        this.login = login;
        this.password = password;
        this.fioUserAccount = fioUserAccount;
        this.sittingPlace = sittingPlace;
    }

    @Test
    public void authorization(){
        authorizationPage = assessorSite.getAuthorizationPage();
        log.info("Authorization begin");
        authorizationPage.setLogin(login).setPassword(password).clickLoginButton();
        assertEquals("Неверный логин/пароль.", authorizationPage.getElementsFromMainPage().size(), 1);
        log.info("Authorization complete");
    }

    @Test
    @Ignore
    public void openAndCloseInformationTable(){
        log.info("Переход на форму Информационное табло и вовзрат к форме со списком вопросов");
        assessorService = new AssessorService(dataBaseConnection.stmt);
        planningTabPage = assessorSite.getPlanningPage();
        planningTabPage.clickTab(MainPage.ETab.PLANNING);
        String numberCommitteeButton = planningTabPage.getNumberCommitteeLastButtonText();
        String dateCommitteeButton = planningTabPage.getDate();
        log.info(numberCommitteeButton + " " + dateCommitteeButton);
        CurrentMeetingPage currentMeettingPage = planningTabPage.clickCommitteeButton();
        assertThat("Номер заседания на кнопке не совпадает с номером в статусе", currentMeettingPage.getTextInformationField(), containsString(deleteSpaceBetweenWords(numberCommitteeButton)));

        InformationTablePage informationTablePage = currentMeettingPage.clickOpenInformationTableButton();
        assertEquals("Ой, открыта не та форма", "Информационное табло", informationTablePage.getHeaderText());

        informationTablePage.clickBackToQuestionListButton();
        currentMeettingPage.clickBackOnListSitting();
        assertFalse("/", isElementVisible(planningTabPage.getNameCommittee()));
    }

    @Test
    @Ignore
    public void fullScreenModeInformTable(){
        log.info("Раскрыть иллюстрации на весь экран");
        assessorService = new AssessorService(dataBaseConnection.stmt);
        planningTabPage = assessorSite.getPlanningPage();
        planningTabPage.clickTab(MainPage.ETab.PLANNING);
        String numberCommitteeButton = planningTabPage.getNumberCommitteeLastButtonText();
        String dateCommitteeButton = planningTabPage.getDate();
        log.info(numberCommitteeButton + " " + dateCommitteeButton);
        CurrentMeetingPage currentMeettingPage = planningTabPage.clickCommitteeButton();
        assertThat("Номер заседания на кнопке не совпадает с номером в статусе", currentMeettingPage.getTextInformationField(), containsString(deleteSpaceBetweenWords(numberCommitteeButton)));

        InformationTablePage informationTablePage = currentMeettingPage.clickOpenInformationTableButton();
        assertEquals("Ой, открыта не та форма", "Информационное табло", informationTablePage.getHeaderText());
        String textInformTable = informationTablePage.getTextContent();
        log.info(textInformTable);

        WindowMaximizedInformationTable maximizedInformationTable = informationTablePage.clickFullScreenModeButton();
        assertEquals("ISIDA Assessor. Meeting and Voting Management System",maximizedInformationTable.getHeaderMaximizedWindow());
        String textMaxInformTable = maximizedInformationTable.getMaximizedTextContent();
        log.info(textMaxInformTable);
        assertEquals(textInformTable, textMaxInformTable);
        maximizedInformationTable.clickRestoreWindow();

        informationTablePage.clickBackToQuestionListButton();
        currentMeettingPage.clickBackOnListSitting();
        assertFalse("/", isElementVisible(planningTabPage.getNameCommittee()));
    }

    @Test
    @Ignore
    public void decreaseIncreaseFontSize(){
        log.info("Раскрыть иллюстрации на весь экран");
        assessorService = new AssessorService(dataBaseConnection.stmt);
        planningTabPage = assessorSite.getPlanningPage();
        planningTabPage.clickTab(MainPage.ETab.PLANNING);
        String numberCommitteeButton = planningTabPage.getNumberCommitteeLastButtonText();
        String dateCommitteeButton = planningTabPage.getDate();
        log.info(numberCommitteeButton + " " + dateCommitteeButton);
        CurrentMeetingPage currentMeettingPage = planningTabPage.clickCommitteeButton();
        assertThat("Номер заседания на кнопке не совпадает с номером в статусе", currentMeettingPage.getTextInformationField(), containsString(deleteSpaceBetweenWords(numberCommitteeButton)));

        InformationTablePage informationTablePage = currentMeettingPage.clickOpenInformationTableButton();
        assertEquals("Ой, открыта не та форма", "Информационное табло", informationTablePage.getHeaderText());

       String increaseText = informationTablePage.clickIncreaseFontSizeButton().clickIncreaseFontSizeButton().getTransformTextSize();
       log.info(informationTablePage.getTransformTextSize());

       String decreaseText = informationTablePage.clickDecreaseFontSizeButton().getTransformTextSize();
       log.info(informationTablePage.getTransformTextSize());

       assertNotEquals(decreaseText, increaseText);

        informationTablePage.clickBackToQuestionListButton();
        currentMeettingPage.clickBackOnListSitting();
        assertFalse("/", isElementVisible(planningTabPage.getNameCommittee()));
    }

    @Test
    public void textContentInformTable() {
        log.info("Раскрыть иллюстрации на весь экран");
        assessorService = new AssessorService(dataBaseConnection.stmt);
        planningTabPage = assessorSite.getPlanningPage();
        planningTabPage.clickTab(MainPage.ETab.PLANNING);
        String numberCommitteeButton = planningTabPage.getNumberCommitteeLastButtonText();
        String dateCommitteeButton = planningTabPage.getDate();
        log.info(numberCommitteeButton + " " + dateCommitteeButton);
        CurrentMeetingPage currentMeettingPage = planningTabPage.clickCommitteeButton();
        assertThat("Номер заседания на кнопке не совпадает с номером в статусе", currentMeettingPage.getTextInformationField(), containsString(deleteSpaceBetweenWords(numberCommitteeButton)));
        QuestionList questionList = currentMeettingPage.QuestionList();

        AgendaPage agendaPage = currentMeettingPage.clickAgendaButton();
        assertEquals("Ой, открыта не та форма", "Повестка дня", agendaPage.getHeaderAgenda());
        agendaPage.clickDownloadThisTextButton();
        sleepAnyTime(5000L);//ждем загрузку файла
        String textAgenda = readDocxFile(String.format("ПОВЕСТКА%s_%s.docx", deleteSymbolInPhrase(numberCommitteeButton.trim()), dateCommitteeButton));
        downloadFile(String.format("ПОВЕСТКА%s_%s.docx", deleteSymbolInPhrase(numberCommitteeButton.trim()), dateCommitteeButton));
        agendaPage.clickBackFromQuestionListButton();

        String statusNow = currentMeettingPage.getTextStatusField();
        boolean questionStatusExamine = isElementHaveTitle(questionList.getQuestionStatusExamine());

        String subjectExamineQuestion = questionList.getTextExamineQuestion();//TODO обрезать символы 2.  и  ⚬ ⚬ ⚬(они на новой строке)
        log.info(subjectExamineQuestion);

        InformationTablePage informationTablePage = currentMeettingPage.clickOpenInformationTableButton();
        assertEquals("Ой, открыта не та форма", "Информационное табло", informationTablePage.getHeaderText());
        String textInformTable = informationTablePage.getTextContent();
        log.info(textInformTable);

       if (STATUS != statusNow) {
            if(STATUS_OPEN == statusNow && questionStatusExamine){
                log.info("Заседание открыто и есть вопрос на рассмотрении");
                assertEquals("В Инф. табло отображен другой текст",textInformTable,subjectExamineQuestion);
                //TODO дописать


            } else {
                assertEquals("В Инф.табло отображен другой текст",textAgenda, textInformTable);
            }

        } else {
            assertEquals("В Инф.табло отображен другой текст",textAgenda, textInformTable);
        }

        informationTablePage.clickBackToQuestionListButton();
        currentMeettingPage.clickBackOnListSitting();
        assertFalse("/", isElementVisible(planningTabPage.getNameCommittee()));
    }
}
