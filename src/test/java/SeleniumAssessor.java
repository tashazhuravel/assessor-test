import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class SeleniumAssessor {
    private SeleniumConfig config;
    private WebDriver webDriver;
    private EventFiringWebDriver eventDriver;
    private String url = "http://assessor-demo.isida.by/assessor_nbrb";

    public SeleniumAssessor() {
        config = new SeleniumConfig();
        config.getWebDriver().get(url);
        webDriver = config.getWebDriver();
        eventDriver = config.getEventDriver();
    }

    public void closeWindow() {
        this.config.getWebDriver().close();
    }

    public String getTitle() {
        return this.config.getWebDriver().getTitle();
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }

    public EventFiringWebDriver getEventDriver() {
        return eventDriver;
    }

    public String getUrl() {
        return url;
    }
}
