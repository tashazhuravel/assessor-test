import log.EventHandler;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.HashMap;

public class SeleniumConfig {
    private WebDriver webDriver;
    private EventFiringWebDriver eventDriver;
    private DriverType driverType = DriverType.CHROME;
    private String CHROME_PATH = "C:/Install/chromedriver/chromedriver.exe";
    private String FIREFOX_PATH = "C:/Install/chromedriver/geckodriver.exe";
    private String IE_PATH = "C:/Install/chromedriver/MicrosoftWebDriver.exe";


    public SeleniumConfig() {
        switch (driverType) {
            case CHROME:
                HashMap<String,Object> chromePrefs = new HashMap<String, Object>();
                chromePrefs.put("profile.default_content_settings.popups", 0);
                chromePrefs.put("download.default_directory", System.getProperty("user.dir"));
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--disable-dev-shm-usage");
                chromeOptions.setExperimentalOption("prefs",chromePrefs);
                System.setProperty("webdriver.chrome.driver", CHROME_PATH);
                webDriver = new ChromeDriver(chromeOptions);
                eventDriver = new EventFiringWebDriver(webDriver);
                eventDriver.register(new EventHandler());
                break;
            case IE:
                InternetExplorerOptions iEOptions = new InternetExplorerOptions();
                System.setProperty("webdriver.MicrosoftWebDriver.driver", IE_PATH);
                webDriver = new InternetExplorerDriver(iEOptions);
                eventDriver = new EventFiringWebDriver(webDriver);
                eventDriver.register(new EventHandler());
                break;
            case FIREFOX:
                FirefoxOptions fFOptions = new FirefoxOptions();
                System.setProperty("webdriver.geckodriver.driver", FIREFOX_PATH);
                webDriver = new FirefoxDriver(fFOptions);
                eventDriver = new EventFiringWebDriver(webDriver);
                eventDriver.register(new EventHandler());
                break;
        }
    }

    public EventFiringWebDriver getEventDriver() {
        return eventDriver;
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }

    public void setWebDriver(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

}
