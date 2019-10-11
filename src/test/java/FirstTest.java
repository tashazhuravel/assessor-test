import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pages.AssessorSite;
import pages.AuthorizationPage;
import pages.MainPage;
import pages.PlanningTabPage;

public class FirstTest {
    private static SeleniumExample seleniumExample;
    private static AssessorSite assessorSite;
    private PlanningTabPage planningTabPage;
    private static AuthorizationPage authorizationPage;
    private final static String LOGIN = "krug";
    private final static String PASSWORD = "krug";
    private static WebDriver webDriver;

    @BeforeClass
    public static void authorization() {
        seleniumExample = new SeleniumExample();
        webDriver = seleniumExample.getWebDriver();
        assessorSite = PageFactory.initElements(webDriver, AssessorSite.class);
        authorizationPage = assessorSite.getAuthorizationPage();
        System.out.println("Step 1: Authorization");
        authorizationPage.setLogin(LOGIN);
        authorizationPage.setPassword(PASSWORD);
        authorizationPage.clickLoginButton();
    }

    @Before
    public void setUp() {
        webDriver.get(seleniumExample.getUrl());
    }

    @Test
    public void authorizationAccept() {
        Assert.assertFalse(authorizationPage.checkAuthorization());
    }

    @Test
    @Ignore
    public void createPlanning() {
        planningTabPage = assessorSite.getPlanningPage();
        planningTabPage.clickTab(MainPage.ETab.PLANNING);
        planningTabPage.clickPlanningEventButton();
        planningTabPage.savePlanning();
    }

    @AfterClass
    public static void tearDown() {
        assessorSite.getMainPage().logOut();
        seleniumExample.closeWindow();
    }
}
