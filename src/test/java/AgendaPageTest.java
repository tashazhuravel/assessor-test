import dataBase.AssessorService;
import org.hamcrest.Matcher;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import pages.AgendaPage;
import pages.MainPage;
import pages.attentionWindow.AttentionType;
import pages.attentionWindow.AttentionWindow;
import pages.messageWindow.MessageType;
import pages.window.WindowUploadFile;
import sittingPage.CurrentMeetingPage;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AgendaPageTest extends BaseWebDriverTest {

    public static final String STATUS = "Формируется повестка дня";


    public AgendaPageTest(String login, String password, String fioUserAccount, String unallocatedQuestionsStatusField, String sittingPlace) {
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
    public void openAndCloseAgenda() {
        log.info("Перейти на форму 'Повестка дня' и вернуться к текущем заседанию");
        assessorService = new AssessorService(dataBaseConnection.stmt);
        planningTabPage = assessorSite.getPlanningPage();
        planningTabPage.clickTab(MainPage.ETab.PLANNING);
        String numberCommitteeButton = planningTabPage.getNumberCommitteeLastButtonText();
        log.info(numberCommitteeButton);
        CurrentMeetingPage currentMeettingPage = planningTabPage.clickCommitteeButton();
        assertThat("Номер заседания на кнопке не совпадает с номером в статусе", currentMeettingPage.getTextInformationField(), containsString(deleteSpaceBetweenWords(numberCommitteeButton)));
        AgendaPage agendaPage = currentMeettingPage.clickAgendaButton();
        assertEquals("Ой, открыта не та форма", "Повестка дня", agendaPage.getHeaderAgenda());
        agendaPage.clickBackFromQuestionListButton();
        assertEquals("Не осуществлен переход к форме Текущее заседание", "Список вопросов", currentMeettingPage.getHeaderQuestionListText());
        currentMeettingPage.clickBackOnListSitting();
        assertFalse("/", isElementVisible(planningTabPage.getNameCommittee()));

    }

    @Test
    @Ignore
    public void downloadFile() {
        log.info("Повестка дня, проверка загрузки файла по кнопке 'Скачать данный текст'");
        assessorService = new AssessorService(dataBaseConnection.stmt);
        planningTabPage = assessorSite.getPlanningPage();
        planningTabPage.clickTab(MainPage.ETab.PLANNING);
        String numberCommitteeButton = planningTabPage.getNumberCommitteeLastButtonText();
        String dateCommitteeButton = planningTabPage.getDate();
        log.info(numberCommitteeButton + " " + dateCommitteeButton);
        CurrentMeetingPage currentMeettingPage = planningTabPage.clickCommitteeButton();
        assertThat("Номер заседания на кнопке не совпадает с номером в статусе", currentMeettingPage.getTextInformationField(), containsString(deleteSpaceBetweenWords(numberCommitteeButton)));
        AgendaPage agendaPage = currentMeettingPage.clickAgendaButton();
        assertEquals("Ой, открыта не та форма", "Повестка дня", agendaPage.getHeaderAgenda());
        agendaPage.clickDownloadThisTextButton();
        sleepAnyTime(5000L);// ожидаем загрузки файла
        downloadFile(String.format("ПОВЕСТКА%s_%s.docx", deleteSymbolInPhrase(numberCommitteeButton.trim()), dateCommitteeButton));
        agendaPage.clickBackFromQuestionListButton();
        currentMeettingPage.clickBackOnListSitting();
        assertFalse("/", isElementVisible(planningTabPage.getNameCommittee()));


    }

    @Test
    @Ignore
    public void uploadFile() {
        log.info("Повестка дня. Проверка помещения файла в систему по кнопке 'Поместить измененный текст' ");
        assessorService = new AssessorService(dataBaseConnection.stmt);
        planningTabPage = assessorSite.getPlanningPage();
        planningTabPage.clickTab(MainPage.ETab.PLANNING);
        String numberCommitteeButton = planningTabPage.getNumberCommitteeLastButtonText();
        String dateCommitteeButton = planningTabPage.getDate();
        log.info(numberCommitteeButton + " " + dateCommitteeButton);
        CurrentMeetingPage currentMeettingPage = planningTabPage.clickCommitteeButton();
        Matcher<String> numberSitting = containsString(deleteSpaceBetweenWords(numberCommitteeButton));

        assertThat("Номер заседания на кнопке не совпадает с номером в статусе", currentMeettingPage.getTextInformationField(), numberSitting);
        AgendaPage agendaPage = currentMeettingPage.clickAgendaButton();
        assertEquals("Ой, открыта не та форма", "Повестка дня", agendaPage.getHeaderAgenda());

        WindowUploadFile windowUploadFile = agendaPage.clickUploadEditedTextButton();
        windowUploadFile.setInputFile(PATH_UPLOAD_FILE);
        windowUploadFile.clickUploadFileButton();
        sleepAnyTime(5000L); //долгая загрузка файла и перезагрузка страницы
        agendaPage.clickDownloadThisTextButton();
        sleepAnyTime(5000L); //долгая загрузка файла
        String textFileBeforeUpload = readDocxFile("Testauto.docx");
        String textAfterUpload = readDocxFile(String.format("ПОВЕСТКА%s_%s.docx", deleteSymbolInPhrase(numberCommitteeButton.trim()), dateCommitteeButton));
        assertEquals("Файл не загружен", textFileBeforeUpload, textAfterUpload);

        agendaPage.clickBackFromQuestionListButton();
        currentMeettingPage.clickBackOnListSitting();
        assertFalse("/", isElementVisible(planningTabPage.getNameCommittee()));
    }

    @Test
    @Ignore
    public void setStatusAgendaApproval() {
        log.info("Уставновить статус 'Повестка дня согласована'");
        assessorService = new AssessorService(dataBaseConnection.stmt);
        planningTabPage = assessorSite.getPlanningPage();
        planningTabPage.clickTab(MainPage.ETab.PLANNING);
        String numberCommitteeButton = planningTabPage.getNumberCommitteeLastButtonText();
        log.info(numberCommitteeButton);
        CurrentMeetingPage currentMeettingPage = planningTabPage.clickCommitteeButton();
        assertThat("Номер заседания на кнопке не совпадает с номером в статусе", currentMeettingPage.getTextInformationField(), containsString(deleteSpaceBetweenWords(numberCommitteeButton)));
        AgendaPage agendaPage = currentMeettingPage.clickAgendaButton();
        assertEquals("Ой, открыта не та форма", "Повестка дня", agendaPage.getHeaderAgenda());

        if (STATUS.equals(currentMeettingPage.getTextStatusField())) {

            assertEquals("Статус не установлен", STATUS, currentMeettingPage.getTextStatusField());
            agendaPage.clickSetMeetingStatusAgendaUnderApprovalButton();
            waitToTextChanged(currentMeettingPage.getStatusField());
            assertEquals("Статус не установлен", "Повестка дня проходит согласование", currentMeettingPage.getTextStatusField());

            messageWindow = assessorSite.getMessageWindow();
            assertEquals("", MessageType.MEETING_STATUS_AGENDA_UNDER_APPROVAL_HAS_BEEN_SUCCESSFULLY_SET.getLabel(), messageWindow.getMessage());
            messageWindow.clickMessageOkButton();
            log.info(currentMeettingPage.getTextStatusField());

            waitWhileElementPresent(currentMeettingPage.getStatusField());
            assertEquals("Статус не установлен", "Повестка дня проходит согласование", currentMeettingPage.getTextStatusField());
            log.info(currentMeettingPage.getStatusField());

            agendaPage.clickSetMeetingStatusAgendaApprovedButton();
            attentionWindow = assessorSite.getAttentionWindow();
            assertEquals("", AttentionType.SET_MEETING_STATUS_AGENDA_APPROVED.getLabel(), attentionWindow.getTextAttention());

            attentionWindow.clickNoAttentionButton();
            assertEquals("Статус не установлен", "Повестка дня проходит согласование", currentMeettingPage.getTextStatusField());

            agendaPage.clickSetMeetingStatusAgendaApprovedButton();
            attentionWindow.clickAttentionCloseByXButton();
            assertEquals("Статус не установлен", "Повестка дня проходит согласование", currentMeettingPage.getTextStatusField());

            agendaPage.clickSetMeetingStatusAgendaApprovedButton();
            attentionWindow.clickYesAttentionButton();

            waitWhileElementPresent(messageWindow.getTextMessage());
            assertEquals("Статус 'Повестка дня утверждена' не установлен", MessageType.MEETING_STATUS_AGENDA_APPROVED_HAS_BEEN_SET.getLabel(), messageWindow.getMessage());
            messageWindow.clickMessageOkButton();


            waitToTextChanged(currentMeettingPage.getStatusField());
            assertEquals("Статус не установлен", "Повестка дня утверждена", currentMeettingPage.getTextStatusField());
            log.info(currentMeettingPage.getStatusField());

        } else {
            agendaPage.clickSetMeetingStatusAgendaApprovedButton();
            attentionWindow = assessorSite.getAttentionWindow();

            assertEquals("", AttentionType.SET_MEETING_STATUS_AGENDA_APPROVED.getLabel(), attentionWindow.getTextAttention());
            attentionWindow.clickYesAttentionButton();

            waitWhileElementPresent(currentMeettingPage.getStatusField());
            assertEquals("", MessageType.MEETING_STATUS_AGENDA_APPROVED_HAS_BEEN_SET.getLabel(), messageWindow.getMessage());
            messageWindow.clickMessageOkButton();

            waitToTextChanged(currentMeettingPage.getStatusField());
            assertEquals("Статус не установлен", "Повестка дня утверждена", currentMeettingPage.getTextStatusField());
            log.info(currentMeettingPage.getStatusField());
        }

        agendaPage.clickBackFromQuestionListButton();
        currentMeettingPage.clickBackOnListSitting();
        assertFalse("/", isElementVisible(planningTabPage.getNameCommittee()));
    }

    @Test
    public void reformatAgenda(){

        log.info("'Повестка дня', переформировать повестки дня");
        assessorService = new AssessorService(dataBaseConnection.stmt);
        planningTabPage = assessorSite.getPlanningPage();
        planningTabPage.clickTab(MainPage.ETab.PLANNING);
        String numberCommitteeButton = planningTabPage.getNumberCommitteeLastButtonText();
        String dateCommitteeButton = planningTabPage.getDate();
        log.info(numberCommitteeButton + " " + dateCommitteeButton);
        CurrentMeetingPage currentMeettingPage = planningTabPage.clickCommitteeButton();

        assertThat("Номер заседания на кнопке не совпадает с номером в статусе", currentMeettingPage.getTextInformationField(), containsString(deleteSpaceBetweenWords(numberCommitteeButton)));
        AgendaPage agendaPage = currentMeettingPage.clickAgendaButton();
        assertEquals("Ой, открыта не та форма", "Повестка дня", agendaPage.getHeaderAgenda());

        agendaPage.clickDownloadThisTextButton();
        sleepAnyTime(5000L);//ждем загрузку файла
        String textBeforeUpload = readDocxFile(String.format("ПОВЕСТКА%s_%s.docx", deleteSymbolInPhrase(numberCommitteeButton.trim()), dateCommitteeButton));

        WindowUploadFile windowUploadFile = agendaPage.clickUploadEditedTextButton();
        windowUploadFile.setInputFile(PATH_UPLOAD_FILE);
        windowUploadFile.clickUploadFileButton();
        sleepAnyTime(5000L);//ждем помещение файла в систему и перезагрузку страницы
        agendaPage.clickDownloadThisTextButton();
        sleepAnyTime(5000L);//ждем загрузку файла
        String textAfterUpload = readDocxFile(String.format("ПОВЕСТКА%s_%s.docx", deleteSymbolInPhrase(numberCommitteeButton.trim()), dateCommitteeButton));

        assertEquals("Файл не был загружен в систему",textBeforeUpload, textAfterUpload);

        agendaPage.clickReformAgendaButton();
        attentionWindow = assessorSite.getAttentionWindow();
        assertEquals(AttentionType.REFORMAT_AGENDA_TEXT.getLabel(),attentionWindow.getTextAttention());
        attentionWindow.clickNoAttentionButton();
        sleepAnyTime(5000L);// ждем переформирование текста и обновление странцы
        agendaPage.clickDownloadThisTextButton();
        sleepAnyTime(5000L);//ждем загрузку файла
        String textBeforeReformat = readDocxFile(String.format("ПОВЕСТКА%s_%s.docx", deleteSymbolInPhrase(numberCommitteeButton.trim()), dateCommitteeButton));
        assertEquals("Переформирован Текст повестки ", textAfterUpload, textBeforeReformat);

        agendaPage.clickReformAgendaButton();
        attentionWindow = assessorSite.getAttentionWindow();
        assertEquals(AttentionType.REFORMAT_AGENDA_TEXT.getLabel(),attentionWindow.getTextAttention());
        attentionWindow.clickYesButton();
        sleepAnyTime(5000L);// ждем переформирование текста и обновление странцы
        agendaPage.clickDownloadThisTextButton();
        sleepAnyTime(5000L);//ждем загрузку файла
        String textAfterReformat = readDocxFile(String.format("ПОВЕСТКА%s_%s.docx", deleteSymbolInPhrase(numberCommitteeButton.trim()), dateCommitteeButton));
        assertNotSame("Текст повестки не переформирован", textBeforeReformat, textAfterReformat);

    }

}
