import dataBase.AssessorService;
import org.apache.commons.lang3.StringUtils;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import pages.AgendaPage;
import pages.MainPage;
import pages.messageWindow.MessageType;
import pages.window.WindowMailingNotificationInvitations;
import sittingPage.CurrentMeetingPage;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SendMailingNotificationInvitationsTest extends BaseWebDriverTest {

    public SendMailingNotificationInvitationsTest(String login, String password, String fioUserAccount, String unallocatedQuestionsStatusField, String sittingPlace) {
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
    public void sendNotificationInvitations() {
        log.info("Повестка дня. Отправка писем 'Рассылка приглашения-уведомления с текстом повестки'");
        assessorService = new AssessorService(dataBaseConnection.stmt);
        planningTabPage = assessorSite.getPlanningPage();
        planningTabPage.clickTab(MainPage.ETab.PLANNING);
        String numberCommitteeButton = planningTabPage.getNumberCommitteeLastButtonText();
        log.info(numberCommitteeButton);
        CurrentMeetingPage currentMeettingPage = planningTabPage.clickCommitteeButton();
        assertThat("Номер заседания на кнопке не совпадает с номером в статусе", currentMeettingPage.getTextInformationField(), containsString(deleteSpaceBetweenWords(numberCommitteeButton)));
        AgendaPage agendaPage = currentMeettingPage.clickAgendaButton();
        assertEquals("Ой, открыта не та форма", "Повестка дня", agendaPage.getHeaderAgenda());

        WindowMailingNotificationInvitations windowMailingNotificationInvitations = agendaPage.clickSendInvitationButton();
        assertEquals("Ой, открыта не то окно", "Рассылка приглашений-уведомлений о проведении заседания",windowMailingNotificationInvitations.getHeaderNotificationInvitations());

        String fioRecipient = assessorService.getFIOAllParticipant().get(0);
        log.info(fioRecipient);
        List<String> fioRecipientList = Stream.of(fioRecipient.split(",")).collect(Collectors.toList());
        Collections.swap(fioRecipientList, 0, 2);
        verifyAutocompleteOptionsText(changeWordPressSymbol(windowMailingNotificationInvitations.getFIOParticipantText()), fioRecipientList);

        if(isAllCheckboxSelected(windowMailingNotificationInvitations.getCheckboxAllFIOParticipant())){
            assertEquals("Добавлены материалы заседания", StringUtils.EMPTY, windowMailingNotificationInvitations.getTextListMaterials());
            //assertEquals("Поле комментария содержит текст. По умолчанию д.б. пусто", StringUtils.EMPTY,windowMailingNotificationInvitations.getTextCommentField());
            if(windowMailingNotificationInvitations.getListMaterials().isEmpty()) {
                windowMailingNotificationInvitations.typeCommentField("Отправка письма автотестом");
                log.info(windowMailingNotificationInvitations.getTextCommentField());
                windowMailingNotificationInvitations.clickSendButton();
                messageWindow = assessorSite.getMessageWindow();
                assertEquals(MessageType.MAILING_HAS_BEEN_SUCCESSFULLY_RESIEVED.getLabel(),messageWindow.getMessage());
                messageWindow.clickMessageOkButton();
            }else{
                //TODO дописать проверку добавленных материалов
                isAllCheckboxDisabled(windowMailingNotificationInvitations.getCheckboxMaterials());
                windowMailingNotificationInvitations.clickCheckboxSelectAll();
                isAllCheckboxSelected(windowMailingNotificationInvitations.getCheckboxMaterials());
                windowMailingNotificationInvitations.clickCheckboxUnselectAll();
                isAllCheckboxDisabled(windowMailingNotificationInvitations.getCheckboxMaterials());
            }
        }else{
            //TODO проверка когда никто не выбран, задизейблена кнопка Разослать
        }






    }

}
