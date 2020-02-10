import dataBase.AssessorService;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import pages.AgendaPage;
import pages.InformationTablePage;
import pages.MainPage;
import pages.window.WindowMaximizedInformationTable;
import pages.sittingPage.CurrentMeettingPage;
import pages.sittingPage.QuestionList;

import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class InformationTablePageTest extends BaseWebDriverTest {

    protected static final String STATUS = "Повестка дня утверждена";
    protected static final String STATUS_OPEN = "Заседание открыто";

    public InformationTablePageTest(String login, String password, String fioUserAccount, String unallocatedQuestionsStatusField, String sittingPlace) {
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
    @Ignore
    public void openAndCloseInformationTable() {
        log.info("Переход на форму Информационное табло и вовзрат к форме со списком вопросов");
        assessorService = new AssessorService(dataBaseConnection.stmt);
        planningTabPage = assessorSite.getPlanningPage();
        planningTabPage.clickTab(MainPage.ETab.PLANNING);
        String numberCommitteeButton = planningTabPage.getNumberCommitteeLastButtonText();
        String dateCommitteeButton = planningTabPage.getDate();
        log.info(numberCommitteeButton + " " + dateCommitteeButton);
        CurrentMeettingPage currentMeettingPage = planningTabPage.clickCommitteeButton();
        assertThat("Номер заседания на кнопке не совпадает с номером в статусе", currentMeettingPage.getTextInformationField(), containsString(deleteSpaceBetweenWords(numberCommitteeButton)));

        InformationTablePage informationTablePage = currentMeettingPage.clickOpenInformationTableButton();
        assertEquals("Ой, открыта не та форма", "Информационное табло", informationTablePage.getHeaderText());

        informationTablePage.clickBackToQuestionListButton();
        currentMeettingPage.clickBackOnListSitting();
        assertFalse("/", isElementVisible(planningTabPage.getNameCommittee()));
    }

    @Test
    @Ignore
    public void fullScreenModeInformTable() {
        log.info("Раскрыть Инф.табло на весь экран");
        assessorService = new AssessorService(dataBaseConnection.stmt);
        planningTabPage = assessorSite.getPlanningPage();
        planningTabPage.clickTab(MainPage.ETab.PLANNING);
        String numberCommitteeButton = planningTabPage.getNumberCommitteeLastButtonText();
        String dateCommitteeButton = planningTabPage.getDate();
        log.info(numberCommitteeButton + " " + dateCommitteeButton);
        CurrentMeettingPage currentMeettingPage = planningTabPage.clickCommitteeButton();
        assertThat("Номер заседания на кнопке не совпадает с номером в статусе", currentMeettingPage.getTextInformationField(), containsString(deleteSpaceBetweenWords(numberCommitteeButton)));

        InformationTablePage informationTablePage = currentMeettingPage.clickOpenInformationTableButton();
        assertEquals("Ой, открыта не та форма", "Информационное табло", informationTablePage.getHeaderText());
        String textInformTable = informationTablePage.textFromTextContent();
        log.info(textInformTable);

        WindowMaximizedInformationTable maximizedInformationTable = informationTablePage.clickFullScreenModeButton();
        assertEquals("ISIDA Assessor. Meeting and Voting Management System", maximizedInformationTable.getHeaderMaximizedWindow());
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
    public void decreaseIncreaseFontSize() {
        log.info("Увеличить/Уменьшить текст на Инф.табло");
        assessorService = new AssessorService(dataBaseConnection.stmt);
        planningTabPage = assessorSite.getPlanningPage();
        planningTabPage.clickTab(MainPage.ETab.PLANNING);
        String numberCommitteeButton = planningTabPage.getNumberCommitteeLastButtonText();
        String dateCommitteeButton = planningTabPage.getDate();
        log.info(numberCommitteeButton + " " + dateCommitteeButton);
        CurrentMeettingPage currentMeettingPage = planningTabPage.clickCommitteeButton();
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
    //@Ignore
    public void textContentInformTable() {
        log.info("Проверка содержимого Инф.табло.");
        assessorService = new AssessorService(dataBaseConnection.stmt);
        planningTabPage = assessorSite.getPlanningPage();
        planningTabPage.clickTab(MainPage.ETab.PLANNING);
        String numberCommitteeButton = planningTabPage.getNumberCommitteeLastButtonText();
        String dateCommitteeButton = planningTabPage.getDate();
        log.info(numberCommitteeButton + " " + dateCommitteeButton);
        CurrentMeettingPage currentMeettingPage = planningTabPage.clickCommitteeButton();
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

        List<String> subjectExamineQuestion = assessorService.getSubjectQuestion();//TODO запрос в БД getSubjectQuestion() на получение темы рассматриваемого вопроса.
        log.info(subjectExamineQuestion);

        InformationTablePage informationTablePage = currentMeettingPage.clickOpenInformationTableButton();
        assertEquals("Ой, открыта не та форма", "Информационное табло", informationTablePage.getHeaderText());
        String textQuestionInformTable = informationTablePage.getSubjectQuestion();
        log.info(textQuestionInformTable);

        String textInformTable = deleteSymbolInTextContent(informationTablePage.getTextContent().iterator().next().getText());

        log.info(textInformTable);

        if (!STATUS.equals(statusNow)) {
            if (STATUS_OPEN.equals(statusNow) && questionStatusExamine) {
                log.info("Заседание открыто и есть вопрос на рассмотрении");
                boolean questionFind = false;
                for (String question : subjectExamineQuestion) {
                    if (question.equals(textQuestionInformTable)) {
                        questionFind = true;
                        break;
                    }
                }
                assertTrue("В Инф. табло отображен другой текст", questionFind);
            } else {
                log.info("Заседание открыто и вопросы в рабочем порядке или Статус заседания Закрыто");
                assertEquals("В Инф.табло отображен другой текст", textAgenda, textInformTable);
            }

        } else {
            assertEquals("В Инф.табло отображен другой текст", textAgenda, textInformTable);
        }

        informationTablePage.clickBackToQuestionListButton();
        currentMeettingPage.clickBackOnListSitting();
        assertFalse("/", isElementVisible(planningTabPage.getNameCommittee()));
    }
}
