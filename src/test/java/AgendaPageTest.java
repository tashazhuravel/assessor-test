import dataBase.AssessorService;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import pages.AgendaPage;
import pages.MainPage;
import pages.attentionWindow.AttentionType;
import pages.messageWindow.MessageType;
import pages.window.WindowUploadFile;
import sittingPage.CurrentMeetingPage;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AgendaPageTest extends BaseWebDriverTest {

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
        downloadFile(String.format("ПОВЕСТКА %s_%s.docx", deleteSymbolInPhrase(numberCommitteeButton), dateCommitteeButton));


    }

    @Test
    @Ignore
    public void uploadFile() {
        log.info("Повестка дня. Проверка помещения файла в систему по кнопке 'Поместить измененный текст' ");
        assessorService = new AssessorService(dataBaseConnection.stmt);
        planningTabPage = assessorSite.getPlanningPage();
        planningTabPage.clickTab(MainPage.ETab.PLANNING);
        String numberCommitteeButton = planningTabPage.getNumberCommitteeLastButtonText();
        log.info(numberCommitteeButton);
        CurrentMeetingPage currentMeettingPage = planningTabPage.clickCommitteeButton();
        assertThat("Номер заседания на кнопке не совпадает с номером в статусе", currentMeettingPage.getTextInformationField(), containsString(deleteSpaceBetweenWords(numberCommitteeButton)));
        AgendaPage agendaPage = currentMeettingPage.clickAgendaButton();
        assertEquals("Ой, открыта не та форма", "Повестка дня", agendaPage.getHeaderAgenda());
        WindowUploadFile windowUploadFile = agendaPage.clickUploadEditedTextButton();
        windowUploadFile.setInputFile("C:\\Users\\iluyshn\\Downloads\\Testauto.docx");
        windowUploadFile.clickUploadFileButton();

    }

    @Test
    // @Ignore
    public void setStatusAgendaApproval() {
        log.info("Уставновить статус 'Повестка дня проходит согласование'");
        assessorService = new AssessorService(dataBaseConnection.stmt);
        planningTabPage = assessorSite.getPlanningPage();
        planningTabPage.clickTab(MainPage.ETab.PLANNING);
        String numberCommitteeButton = planningTabPage.getNumberCommitteeLastButtonText();
        log.info(numberCommitteeButton);
        CurrentMeetingPage currentMeettingPage = planningTabPage.clickCommitteeButton();
        assertThat("Номер заседания на кнопке не совпадает с номером в статусе", currentMeettingPage.getTextInformationField(), containsString(deleteSpaceBetweenWords(numberCommitteeButton)));
        AgendaPage agendaPage = currentMeettingPage.clickAgendaButton();
        assertEquals("Ой, открыта не та форма", "Повестка дня", agendaPage.getHeaderAgenda());

        String status = currentMeettingPage.getTextStatusField();
        log.info(currentMeettingPage.getStatusField());
        if(isTextPresent(status,currentMeettingPage.getStatusField())) {

            assertEquals("Статус не установлен", "Формируется повестка дня", currentMeettingPage.getTextStatusField());
            agendaPage.clickSetMeetingStatusAgendaUnderApprovalButton();
            waitToTextChanged(currentMeettingPage.getStatusField());
            assertEquals("Статус не установлен", "Повестка дня проходит согласование", currentMeettingPage.getTextStatusField());

            messageWindow = assessorSite.getMessageWindow();
            assertEquals("", MessageType.MEETING_STATUS_AGENDA_UNDER_APPROVAL_HAS_BEEN_SUCCESSFULLY_SET.getLabel(),messageWindow.getTextMessage());
            //TODO дописать проверку сообщения Enum
            messageWindow.clickMessageOkButton();
            waitToTextChanged(currentMeettingPage.getStatusField());
            assertEquals("Статус не установлен", "Повестка дня проходит согласование", currentMeettingPage.getTextStatusField());
            log.info(currentMeettingPage.getStatusField());

        }else if (isTextPresent(status,currentMeettingPage.getStatusField())) {

                agendaPage.clickSetMeetingStatusAgendaApprovedButton();
                attentionWindow = assessorSite.getAttentionWindow();
                assertEquals("", AttentionType.SET_MEETING_STATUS_AGENDA_APPROVED.getLabel(), attentionWindow.getTextAttention());
                //TODO дописать проверку сообщения Enum
                attentionWindow.clickNoAttentionButton();
                assertEquals("Статус не установлен", "Повестка дня проходит согласование", currentMeettingPage.getTextStatusField());

                agendaPage.clickSetMeetingStatusAgendaApprovedButton();
                attentionWindow.clickAttentionCloseByXButton();
                assertEquals("Статус не установлен", "Повестка дня проходит согласование", currentMeettingPage.getTextStatusField());

                agendaPage.clickSetMeetingStatusAgendaApprovedButton();
                attentionWindow.clickYesAttentionButton();

                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                messageWindow.clickMessageOkButton();
                assertEquals("", MessageType.MEETING_STATUS_AGENDA_APPROVED_HAS_BEEN_SET.getLabel(),messageWindow.getTextMessage());

                waitToTextChanged(currentMeettingPage.getStatusField());

                assertEquals("Статус не установлен", "Повестка дня утверждена", currentMeettingPage.getTextStatusField());
                log.info(currentMeettingPage.getStatusField());
            }
        }

}
