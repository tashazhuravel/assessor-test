import dataBase.AssessorService;
import dataBase.DataBaseConnection;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import pages.AgendaPage;
import pages.MainPage;
import pages.window.WindowPreliminaryAcquaintanceWithAgenda;
import sittingPage.CurrentMeetingPage;

import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SendPreliminaryAcquaintanceWithAgendaTest extends BaseWebDriverTest{

    public SendPreliminaryAcquaintanceWithAgendaTest(String login, String password, String fioUserAccount, String unallocatedQuestionsStatusField, String sittingPlace){
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
    public void sendPreliminaryAcquaintance(){
        log.info("Повестка дня. Отправка писем 'Предварительное ознакомление'");
        assessorService = new AssessorService(dataBaseConnection.stmt);
        planningTabPage = assessorSite.getPlanningPage();
        planningTabPage.clickTab(MainPage.ETab.PLANNING);
        String numberCommitteeButton = planningTabPage.getNumberCommitteeLastButtonText();
        log.info(numberCommitteeButton);
        CurrentMeetingPage currentMeettingPage = planningTabPage.clickCommitteeButton();
        assertThat("Номер заседания на кнопке не совпадает с номером в статусе", currentMeettingPage.getTextStatusField(), containsString(deleteSpaceBetweenWords(numberCommitteeButton)));
        AgendaPage agendaPage = currentMeettingPage.clickAgendaButton();
        assertEquals("Ой, открыта не та форма", "Повестка дня", agendaPage.getHeaderAgenda());
        WindowPreliminaryAcquaintanceWithAgenda windowPreliminaryAcquaintanceWithAgenda = agendaPage.clickSendAgendaButton();
        List<String> fioRecipient = assessorService.getFIOParticipantSitting();
        log.info(fioRecipient);
        verifyAutocompleteOptions(windowPreliminaryAcquaintanceWithAgenda.getListFIOParticipants(),fioRecipient);
    }





}
