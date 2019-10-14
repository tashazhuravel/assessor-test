import org.openqa.selenium.WebDriver;

public class SeleniumAssessor {
    private SeleniumConfig config;
    private WebDriver webDriver;
    private String url = "http://assessor-demo.isida.by/assessor_nbrb";

    public SeleniumAssessor() {
        config = new SeleniumConfig();
        config.getWebDriver().get(url);
        webDriver = config.getWebDriver();
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

    public String getUrl() {
        return url;
    }
}
