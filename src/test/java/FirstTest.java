import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import pages.MainPage;
import pages.WindowAboutSystem;
import pages.WindowMeetingScheduling;

import java.util.Arrays;
import java.util.List;

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
    //проверка модального окна О системе
    public void clickUserAccountButton(){
        MainPage clickButton = assessorSite.getMainPage();
        clickButton.aboutSystem();
        WindowAboutSystem aboutSystem = assessorSite.getWindowAboutSystem();
        assertFalse(aboutSystem.checkWindowAboutSystem());
        aboutSystem.closingWindowAboutSystem();
        clickButton.aboutSystem();
        aboutSystem.closeWindowAboutSystem();
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



