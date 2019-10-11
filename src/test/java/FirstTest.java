import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import pages.MainPage;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertFalse;

public class FirstTest extends BaseWebDriverTest {
    @Before
    public void setUp() {
        driver.get(seleniumExample.getUrl());
        driver.manage().window().setSize(new Dimension(1600, 1000));
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void authorizationAccept() {
        assertFalse(authorizationPage.checkAuthorization());
    }

    @Test
    @Ignore
    public void createPlanning() {
        planningTabPage = assessorSite.getPlanningPage();
        planningTabPage.clickTab(MainPage.ETab.PLANNING);
        planningTabPage.clickPlanningEventButton();
        planningTabPage.savePlanning();
    }


}
