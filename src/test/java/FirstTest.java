import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import pages.MainPage;
import pages.WindowAboutSystem;
import pages.WindowMeetingScheduling;
import pages.WindowUserAccount;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class FirstTest extends BaseWebDriverTest {
    @Before
    public void setUp() {
    }

    @Test
    public void authorizationAccept() {
        assertFalse(authorizationPage.checkAuthorization());
        takeScreenshot("authorization");
    }

    @Test
    //проверка модального окна "О системе"
    public void clickAboutSystemButton() {
        MainPage clickButton = assessorSite.getMainPage();
        clickButton.aboutSystem();
        WindowAboutSystem aboutSystem = assessorSite.getWindowAboutSystem();
        assertFalse(aboutSystem.checkWindowAboutSystem());
        aboutSystem.closingWindowAboutSystem();
        clickButton.aboutSystem();
        aboutSystem.closeWindowAboutSystem();
    }

    @Test
    //проверка модального окна "Учетная запись пользователя"
    public void checkWindowUserAccount() {
        MainPage clickButton = assessorSite.getMainPage();
        clickButton.userAccount();
        WindowUserAccount userAccount = assessorSite.getWindowUserAccount();
        assertFalse(userAccount.checkWindowUserAccount());
        assertFalse(userAccount.emptyUserFIOFieldText());
        String getFieldFIO = userAccount.getUserFIOFieldText();
        assertEquals(FIO_USER_ACCOUNT, getFieldFIO);
        userAccount.saveUserAccount();
        clickButton.userAccount();
        userAccount.closeUserAccount();
        clickButton.userAccount();
        //userAccount.closingWindowUserAccount();
    }


    @Test
    @Ignore
    public void createPlanning() {
        planningTabPage = assessorSite.getPlanningPage();
        planningTabPage.clickTab(MainPage.ETab.PLANNING);
        WindowMeetingScheduling windowMeetingScheduling = planningTabPage.clickPlanningEventButton();


    }

  /*  @Test
    @Ignore
    public void checkSelectPlanningPlace() {
        planningTabPage = assessorSite.getPlanningPage();
        planningTabPage.clickTab(MainPage.ETab.PLANNING);
        planningTabPage.clickPlanningEventButton();
        List<String> select = Arrays.asList("", "Small meeting room", "Большой кабинет",
                "Зал для совещаний Главного корпуса", "Зал заседаний", "Кабинет", "Комната для заседаний",
                "Переговорная", "переговорная 1", "Переговорная комната");
       // verifyAutocompleteOptions(planningTabPage.getSelectPlanningPlace(), select);
       }*/
}



