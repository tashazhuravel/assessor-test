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
}
