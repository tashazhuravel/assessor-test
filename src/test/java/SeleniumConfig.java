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


    public SeleniumConfig() {
        switch (driverType) {
            case CHROME:
                HashMap<String,Object> chromePrefs = new HashMap<String, Object>();
                chromePrefs.put("profile.default_content_settings.popups", 0);
                chromePrefs.put("download.default_directory", BaseWebDriverTest.obj.getProperty("PATH_DOWNLOAD_FILE"));
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--disable-dev-shm-usage");
                chromeOptions.setExperimentalOption("prefs",chromePrefs);
                System.setProperty("webdriver.chrome.driver", BaseWebDriverTest.obj.getProperty("CHROME_PATH"));
                webDriver = new ChromeDriver(chromeOptions);
                eventDriver = new EventFiringWebDriver(webDriver);
                eventDriver.register(new EventHandler());
                break;
            case IE:
                InternetExplorerOptions iEOptions = new InternetExplorerOptions();
                System.setProperty("webdriver.MicrosoftWebDriver.driver",BaseWebDriverTest.obj.getProperty("IE_PATH"));
                webDriver = new InternetExplorerDriver(iEOptions);
                eventDriver = new EventFiringWebDriver(webDriver);
                eventDriver.register(new EventHandler());
                break;
            case FIREFOX:
                FirefoxOptions fFOptions = new FirefoxOptions();
                System.setProperty("webdriver.geckodriver.driver", BaseWebDriverTest.obj.getProperty("FIREFOX_PATH"));
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
