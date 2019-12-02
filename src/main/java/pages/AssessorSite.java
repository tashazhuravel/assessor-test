package pages;

import org.openqa.selenium.WebDriver;
import pages.mainPageTab.ArchiveTabPage;
import pages.mainPageTab.ManageTabPage;
import pages.mainPageTab.PlanningTabPage;
import pages.unallocatedQuestionPage.UnallocatedQuestions;
import pages.window.WindowAboutSystem;
import pages.window.WindowSittingPlanning;
import pages.window.WindowNotification;
import pages.window.WindowUserAccount;
import sittingPage.CurrentMeetingPage;

public class AssessorSite {

    private WebDriver webDriver;

    public AssessorSite(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public AuthorizationPage getAuthorizationPage() {
        return new AuthorizationPage(webDriver);
    }

    public MainPage getMainPage() {
        return new MainPage(webDriver);
    }

    public PlanningTabPage getPlanningPage() {
        return new PlanningTabPage(webDriver);
    }

    public ArchiveTabPage getArchiveTabPage() {
        return new ArchiveTabPage(webDriver);
    }

    public ManageTabPage getManageTabPage() {
        return new ManageTabPage(webDriver);
    }

    public CurrentMeetingPage getCurrentMeetingPage() {
        return new CurrentMeetingPage(webDriver);
    }

    public WindowUserAccount getWindowUserAccount() {
        return new WindowUserAccount(webDriver);
    }

    public WindowAboutSystem getWindowAboutSystem() {
        return new WindowAboutSystem(webDriver);
    }

    public WindowSittingPlanning getWindowMeetingScheduling() {
        return new WindowSittingPlanning(webDriver);
    }

    public WindowNotification getWindowNotification(){ return new WindowNotification(webDriver);}

    public UnallocatedQuestions getUnllocatedQuestions() {
        return new UnallocatedQuestions(webDriver);
    }

}
