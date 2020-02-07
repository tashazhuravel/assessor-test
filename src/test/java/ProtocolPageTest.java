import dataBase.AssessorService;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import pages.MainPage;
import pages.ProtocolPage;
import pages.sittingPage.CurrentMeetingPage;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProtocolPageTest extends BaseWebDriverTest {

    protected static final String STATUS = "Формируется протокол";

    public ProtocolPageTest (String login, String password, String fioUserAccount, String unallocatedQuestionsStatusField, String sittingPlace){
        this.login = login;
        this.password = password;
        this.fioUserAccount = fioUserAccount;
        this.sittingPlace = sittingPlace;
    }

    @Test
    //@Ignore
    public void authorization(){
        authorizationPage = assessorSite.getAuthorizationPage();
        log.info("Authorization begin");
        authorizationPage.setLogin(login).setPassword(password).clickLoginButton();
        assertEquals("Неверный логин/пароль.", authorizationPage.getElementsFromMainPage().size(), 1);
        log.info("Authorization complete");
    }

    @Test
    //@Ignore
    public void openAndCloseProtocol() {
        log.info("Перейти на форму 'Протокол' и вернуться к текущем заседанию");
        assessorService = new AssessorService(dataBaseConnection.stmt);
        planningTabPage = assessorSite.getPlanningPage();
        planningTabPage.clickTab(MainPage.ETab.PLANNING);
        String numberCommittee = planningTabPage.getNumberCommitteeLastButtonText();
        String dateCommittee = planningTabPage.getDate();
        log.info(numberCommittee + " " + dateCommittee);
        CurrentMeetingPage currentMeettingPage = planningTabPage.clickCommitteeButton();
        assertThat("Номер заседания на кнопке не совпадает с номером в статусе", currentMeettingPage.getTextInformationField(), containsString(deleteSpaceBetweenWords(numberCommittee)));
        ProtocolPage protocolPage = currentMeettingPage.clickOpenProtocol();
        assertEquals("Открыта не та форма", "Протокол", protocolPage.getHeaderProtocolPage());
        protocolPage.clickCloseProtocolButton();
        assertEquals("Не осуществлен переход к форме Текущее заседание", "Список вопросов", currentMeettingPage.getHeaderQuestionListText());
        currentMeettingPage.clickBackOnListSitting();
        assertFalse("/", isElementVisible(planningTabPage.getNameCommittee()));
    }







}
