package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.AssessorSite;
import pages.AuthorizationPage;
import pages.MainPage;
import pages.PlanningTabPage;

import java.util.concurrent.TimeUnit;

public class FirstTest {

    WebDriver webDriver;
    AuthorizationPage authorizationPage;
    PlanningTabPage planningTabPage;

    private static final String LOGIN = "krug";
    private static final String PASSWORD = "krug";
    private boolean authorization;

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        System.setProperty("webdriver.chrome.driver", "C:/Install/chromedriver/chromedriver.exe");
        webDriver = new ChromeDriver(options);
        webDriver.get("http://assessor-demo.isida.by/assessor_nbrb");
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("Step 1: Authorization");

        AssessorSite assessorSite = new AssessorSite(webDriver);
        planningTabPage = assessorSite.getPlanningPage();
        authorizationPage = assessorSite.getAuthorizationPage();
        authorizationPage.setLogin(LOGIN);
        authorizationPage.setPassword(PASSWORD);
        authorizationPage.clickLoginButton();
    }

    @Test
    public void testUrl() throws Exception {
        authorization = webDriver.getCurrentUrl().equals("http://assessor-demo.isida.by/assessor_nbrb/index.php?title=%D0%97%D0%B0%D0%B3%D0%BB%D0%B0%D0%B2%D0%BD%D0%B0%D1%8F_%D1%81%D1%82%D1%80%D0%B0%D0%BD%D0%B8%D1%86%D0%B0#tab-planning");
        Assert.assertTrue(authorization);
        if (!authorization) {
            throw new Exception();
        }
    }

    @Test
    public void clickTab() {
        planningTabPage.clickPlanningEventButton();
    }

    @After
    public void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }

    }
}
