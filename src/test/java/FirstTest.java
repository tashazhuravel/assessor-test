import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import pages.MainPage;

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
    @Ignore
    public void createPlanning() {
        planningTabPage = assessorSite.getPlanningPage();
        planningTabPage.clickTab(MainPage.ETab.PLANNING);
        planningTabPage.clickPlanningEventButton();
        planningTabPage.savePlanning();
    }

    @Test
    public void checkSelectPlanningPlace() {
        planningTabPage = assessorSite.getPlanningPage();
        planningTabPage.clickTab(MainPage.ETab.PLANNING);
        planningTabPage.clickPlanningEventButton();
        List<String> select = Arrays.asList("", "Small meeting room", "Большой кабинет",
                "Зал для совещаний Главного корпуса", "Зал заседаний", "Кабинет", "Комната для заседаний",
                "Переговорная", "переговорная 1", "Переговорная комната");
        verifyAutocompleteOptions(planningTabPage.getSelectPlanningPlace(), select);
    }


}
