package pages;

import org.openqa.selenium.WebDriver;

public class AssessorSite {
    WebDriver webDriver;

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

    public CurrentMeetingPage geyCurrentMeetingPage() {
        return new CurrentMeetingPage(webDriver);
    }

    public WindowUserAccount getWindowUserAccount() {
        return new WindowUserAccount(webDriver);
    }

    public WindowAboutSystem getWindowAboutSystem() {
        return new WindowAboutSystem(webDriver);
    }

    public WindowMeetingScheduling getWindowMeetingScheduling() {
        return new WindowMeetingScheduling(webDriver);
    }


}
