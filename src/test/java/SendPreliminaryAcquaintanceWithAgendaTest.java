import dataBase.AssessorService;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import pages.AgendaPage;
import pages.MainPage;
import pages.messageWindow.MessageType;
import pages.window.WindowPreliminaryAcquaintanceWithAgenda;
import pages.sittingPage.CurrentMeettingPage;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SendPreliminaryAcquaintanceWithAgendaTest extends BaseWebDriverTest {

    public SendPreliminaryAcquaintanceWithAgendaTest(String login, String password, String fioUserAccount, String unallocatedQuestionsStatusField, String sittingPlace) {
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
    public void sendPreliminaryAcquaintance() {
        log.info("Тест-кейс.Повестка дня. Отправка писем 'Предварительное ознакомление'");
        assessorService = new AssessorService(dataBaseConnection.stmt);
        planningTabPage = assessorSite.getPlanningPage();
        planningTabPage.clickTab(MainPage.ETab.PLANNING);

        String stateCommitteeButton = planningTabPage.getState().getText();

        if (stateCommitteeButton.equals("Готовится")) {
            String numberCommitteeButton = planningTabPage.getNumberCommitteeLastButtonText();
            log.info(numberCommitteeButton);
            CurrentMeettingPage currentMeettingPage = planningTabPage.clickCommitteeButton();
            assertThat("Номер заседания на кнопке не совпадает с номером в поле Инф.табло", currentMeettingPage.getTextInformationField(), containsString(deleteSpaceBetweenWords(numberCommitteeButton)));
            AgendaPage agendaPage = currentMeettingPage.clickAgendaButton();
            assertEquals("Заголовок 'Повестка дня' не найден или открыта не та форма", "Повестка дня", agendaPage.getHeaderAgenda());
            WindowPreliminaryAcquaintanceWithAgenda windowPreliminaryAcquaintanceWithAgenda = agendaPage.clickSendAgendaButton();
            String fioRecipient = assessorService.getFIOAllParticipant().get(0);
            log.info(fioRecipient);
            List<String> fioRecipientList = Stream.of(fioRecipient.split(",")).collect(Collectors.toList());
            Collections.swap(fioRecipientList, 1, 3);
            Collections.swap(fioRecipientList, 2, 4);
            Collections.swap(fioRecipientList, 0, 2);
            verifyAutocompleteOptionsText(changeWordPressSymbol(windowPreliminaryAcquaintanceWithAgenda.getListFIOParticipants()), fioRecipientList);
            if (isAllCheckboxSelected(windowPreliminaryAcquaintanceWithAgenda.getCheckboxFIOParticipants())) {
                windowPreliminaryAcquaintanceWithAgenda.clickSendButton();
                log.info("Письмо отправлено");
            } else {
                isButtonDisabled(windowPreliminaryAcquaintanceWithAgenda.getSendButton());
                log.info("Ни один из получателей не выбран");
            }

            messageWindow = assessorSite.getMessageWindow();
            assertEquals(MessageType.MAILING_HAS_BEEN_SUCCESSFULLY_RESIEVED.getLabel(), messageWindow.getMessage());
            messageWindow.clickMessageOkButton();
        } else {
            log.info("Заседание со статусом 'Подготовлено', 'Идет' или 'Закрыто'.");
        }
    }
}






