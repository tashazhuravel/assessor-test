package pages;

import org.openqa.selenium.WebDriver;

public class AssessorSite {
    WebDriver webDriver;

    public AssessorSite(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public AuthorizationPage getAutorizationPage() {
        return new AuthorizationPage(webDriver);
    }
}
